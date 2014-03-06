package nl.elucidator.homeautomation.weather.openweather;

import nl.elucidator.homeautomation.configuration.Configuration;
import nl.elucidator.homeautomation.elastic.ElasticClientProducder;
import nl.elucidator.homeautomation.weather.openweather.model.Weather;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test cases
 */
@RunWith(Arquillian.class)
public class OpenWeatherApiTest {
    public String weatherLocation = "6544881";

    @Deployment
    public static WebArchive createDeployment() {


        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies()
                .resolve().withTransitivity().asFile();

        WebArchive javaArchive = ShrinkWrap.create(WebArchive.class)
                .addPackages(true, Configuration.class.getPackage())
                .addPackages(true, ElasticClientProducder.class.getPackage())
                .addPackages(true, OpenWeatherService.class.getPackage())
                        //.addClass(OpenWeatherGsonService.class)
                .addAsResource("application.properties", "application.properties")
                        //.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(libs);
        System.out.println("javaArchive = " + javaArchive.toString(true));
        return javaArchive;
    }


    @Inject
    private OpenWeatherService openWeatherService;


    @Test
    public void testGetWeather() throws Exception {
        assertThat(openWeatherService, notNullValue());
        System.out.println("openWeatherService.gsonService = " + openWeatherService.gsonService);
        System.out.println("openWeatherService = " + openWeatherService.baseUrl);
        assertThat(openWeatherService.appId, notNullValue());
        assertThat(openWeatherService.gsonService, notNullValue());

        Weather weather = openWeatherService.getWeather(weatherLocation);
        assertThat(weather, notNullValue());
    }
}
