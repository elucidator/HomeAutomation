package nl.elucidator.homeautomation.weather.openweather.timed;

import nl.elucidator.homeautomation.configuration.NamedProperty;
import nl.elucidator.homeautomation.weather.openweather.OpenWeatherService;
import nl.elucidator.homeautomation.weather.openweather.elastic.OpenWeatherElasticClient;
import nl.elucidator.homeautomation.weather.openweather.gson.OpenWeatherGsonService;
import nl.elucidator.homeautomation.weather.openweather.model.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


/**
 * Created by pieter on 1/28/14.
 */
@Singleton
public class TimedWeatherInfo {

    @Inject
    @NamedProperty(key = "wheather.location", mandatory = true, defaultValue = "6544881")
    private String weatherLocation;
    @EJB
    private OpenWeatherService weatherService;
    @Inject
    private OpenWeatherGsonService gsonService;
    @Inject
    private OpenWeatherElasticClient elasticClient;
    @Resource
    private ManagedScheduledExecutorService defaultScheduledExecutorService;

    private static final Logger LOGGER = LogManager.getLogger(TimedWeatherInfo.class);
    private static Weather lastWeather;

    @Timeout
    public void timeout() {
        LOGGER.error("Timeout completing weather request.");
    }


    @Schedule(minute = "*/10", hour = "*", persistent = true)
    public void scheduleFuture() {

        defaultScheduledExecutorService.schedule(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    Weather weather = weatherService.getWeather(weatherLocation);

                    if (weather != null) {
                        weather.setTimeStamp(DateTime.now());
                        elasticClient.add(gsonService.toJsonTimeStamped(weather, "dt", "timeStamp"));
                        lastWeather = weather;
                    } else {
                        if (lastWeather == null) {
                            LOGGER.error("No data available from weather service.");
                        } else {
                            lastWeather.setTimeStamp(DateTime.now());
                            elasticClient.add(gsonService.toJsonTimeStamped(lastWeather, "dt", "timeStamp"));
                            LOGGER.warn("Inserted old weather data, temperature: " + lastWeather.getMain().getTemp() + " timeStamp: " + weather.getTimeStamp());
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("Error during processing of the Scheduled task.", e);
                } finally {
                    return null;
                }

            }
        }, 0, TimeUnit.SECONDS);
    }
}
