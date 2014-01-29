package nl.elucidator.homeautomation.weather.openweather.timed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.elucidator.homeautomation.weather.openweather.OpenWeatherService;
import nl.elucidator.homeautomation.weather.openweather.elastic.OpenWeatherElasticClient;
import nl.elucidator.homeautomation.weather.openweather.gson.OpenWeatherDateTimeConverter;
import nl.elucidator.homeautomation.weather.openweather.gson.OpenWeatherGsonService;
import nl.elucidator.homeautomation.weather.openweather.model.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;


/**
 * Created by pieter on 1/28/14.
 */
@Singleton
public class TimedWeatherInfo {

    @EJB
    private OpenWeatherService weatherService;
    @Inject
    private OpenWeatherGsonService gsonService;
    @Inject
    private OpenWeatherElasticClient elasticClient;

    private static final Logger LOGGER = LogManager.getLogger(TimedWeatherInfo.class);


    @Schedule(minute = "*/10", hour = "*", persistent = false)
    public void doWork() {
        Weather weather = weatherService.getWeather(OpenWeatherService.AMSTERDAM_ZO);
        elasticClient.add(gsonService.toJsonTimeStamped(weather, "dt", "timeStamp"));
    }
}
