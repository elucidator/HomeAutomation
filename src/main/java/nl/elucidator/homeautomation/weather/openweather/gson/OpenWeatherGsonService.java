package nl.elucidator.homeautomation.weather.openweather.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;

import javax.ejb.Singleton;

/**
 * GSon conversion service for the OpenWeather data.
 */
@Singleton
public class OpenWeatherGsonService {
    private final Gson gson;


    public OpenWeatherGsonService() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new OpenWeatherDateTimeConverter());
        gson = gsonBuilder.create();
    }

    public <T> T fromJson(final String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    public String toJsonTimeStamped(final Object object, final String src, final String destination) {
        String json = gson.toJson(object);
        return json.replace(src, destination);
    }
}
