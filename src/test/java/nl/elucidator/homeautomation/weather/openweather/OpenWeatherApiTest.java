package nl.elucidator.homeautomation.weather.openweather;

import nl.elucidator.homeautomation.weather.openweather.model.Weather;
import org.junit.Test;

/**
 * Created by pieter on 1/28/14.
 */
public class OpenWeatherApiTest {
    private static final OpenWeatherService OPEN_WEATHER = new OpenWeatherService();

    @Test
    public void testGetWeather() throws Exception {
        Weather weather = OPEN_WEATHER.getWeather(OpenWeatherService.AMSTERDAM_ZO);
        System.out.println("weather = " + weather);
    }
}
