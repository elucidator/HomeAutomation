package nl.elucidator.homeautomation.weather.openweather.timed;

import nl.elucidator.homeautomation.weather.openweather.OpenWeatherService;
import nl.elucidator.homeautomation.weather.openweather.elastic.OpenWeatherElasticClient;
import nl.elucidator.homeautomation.weather.openweather.gson.OpenWeatherGsonService;
import nl.elucidator.homeautomation.weather.openweather.model.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
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

    @Timeout
    public void timeout() {
        LOGGER.error("Timeout completing weather request.");
    }


    @Schedule(minute = "*/10", hour = "*", persistent = false)
    public void doWork() {

        try {
            Weather weather = weatherService.getWeather(OpenWeatherService.AMSTERDAM_ZO);

            if (weather != null) {
                elasticClient.add(gsonService.toJsonTimeStamped(weather, "dt", "timeStamp"));
            } else {
                LOGGER.error("No data received from weather service.");
            }
        } catch (Exception e) {
            LOGGER.error("Error during processing of the Scheduled task.", e);
        }
    }
}
