package nl.elucidator.homeautomation.weather.openweather.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.elucidator.homeautomation.weather.openweather.model.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import javax.ejb.Singleton;

/**
 * Created by pieter on 1/29/14.
 */
@Singleton
public class OpenWeatherGsonService {
    private static final Logger LOGGER = LogManager.getLogger(OpenWeatherGsonService.class);
    private final Gson gson;


    public OpenWeatherGsonService() {
        LOGGER.info("Loading GSON for " + Weather.class.getName());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new OpenWeatherDateTimeConverter());
        gson = gsonBuilder.create();
    }

    public <T> T  fromJson(final String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    public String toJson(final Object object ) {
        return gson.toJson(object);
    }

    public String toJsonTimeStamped(final Object object, final String src, final String destination) {
        String json = gson.toJson(object);
        return json.replace(src, destination);
    }
}
