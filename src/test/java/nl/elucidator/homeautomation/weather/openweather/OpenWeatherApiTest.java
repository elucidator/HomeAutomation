package nl.elucidator.homeautomation.weather.openweather;

import nl.elucidator.homeautomation.weather.openweather.gson.OpenWeatherGsonService;
import nl.elucidator.homeautomation.weather.openweather.model.Weather;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 Test cases
 */
public class OpenWeatherApiTest {
    private static final OpenWeatherService OPEN_WEATHER = new OpenWeatherService();

    @Before
    public void before() {
        OPEN_WEATHER.gsonService = new OpenWeatherGsonService();
    }

    @Test
    public void testGetWeather() throws Exception {
        Weather weather = OPEN_WEATHER.getWeather(OpenWeatherService.AMSTERDAM_ZO);
        assertThat(weather, IsNull.notNullValue());
    }
}
