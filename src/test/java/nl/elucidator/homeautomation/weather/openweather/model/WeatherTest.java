package nl.elucidator.homeautomation.weather.openweather.model;

import nl.elucidator.homeautomation.weather.openweather.gson.OpenWeatherGsonService;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * Created by pieter on 3/6/14.
 */
public class WeatherTest {
    private static final DateTime TIME_STAMP_A = DateTime.now();
    private static final DateTime TIME_STAMP_B = DateTime.now().plusMinutes(10);
    private OpenWeatherGsonService gsonService = new OpenWeatherGsonService();

    @Test
    public void updatedTimeStamp() {
        Weather weather = new Weather();
        weather.setTimeStamp(TIME_STAMP_A);
        String a = gsonService.toJsonTimeStamped(weather, "dt", "timeStamp");

        weather.setTimeStamp(TIME_STAMP_B);
        String b = gsonService.toJsonTimeStamped(weather, "dt", "timeStamp");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
