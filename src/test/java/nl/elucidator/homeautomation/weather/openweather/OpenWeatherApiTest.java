package nl.elucidator.homeautomation.weather.openweather;

import nl.elucidator.homeautomation.weather.openweather.model.Weather;
import org.junit.Ignore;
import org.junit.Test;

import javax.ejb.EJB;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test cases
 */
//@RunWith(Arquillian.class)
public class OpenWeatherApiTest {
    public String weatherLocation = "6544881";

//    @Deployment
//    public static WebArchive createDeployment() {
//        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies()
//                .resolve().withTransitivity().asFile();
//
//        WebArchive javaArchive = ShrinkWrap.create(WebArchive.class)
//                .addPackages(true, ConfigurationFactory.class.getPackage())
//                .addPackages(true, ElasticClientProducder.class.getPackage())
//                .addPackages(true, OpenWeatherService.class.getPackage())
//                .addAsResource("application.properties", "application.properties")
//                 .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
//                .addAsLibraries(libs);
//        System.out.println("javaArchive = " + javaArchive.toString(true));
//        return javaArchive;
//    }


    @EJB
    private OpenWeatherService openWeatherService;


    @Ignore
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
