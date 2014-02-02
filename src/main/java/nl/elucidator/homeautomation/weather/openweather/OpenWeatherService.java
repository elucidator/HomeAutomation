package nl.elucidator.homeautomation.weather.openweather;

import nl.elucidator.homeautomation.weather.openweather.gson.OpenWeatherGsonService;
import nl.elucidator.homeautomation.weather.openweather.model.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Stateless interface for retrieving Weather data.
 */
@Stateless
public class OpenWeatherService {

    /**
     * Location ID of amsterdam ZO, NL
     */
    public static final String AMSTERDAM_ZO = "6544881";

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String LOCATION_ID = "id";
    private static final String APP_ID = "12df61dae56f7f403f954209817eae3f";
    private static final String PARAM_APP_ID = "APPID";
    private static final String UNITS_METRIC = "metric";
    private static final String PARAM_UNITS = "units";
    private static final Logger LOGGER = LogManager.getLogger(OpenWeatherService.class);

    @Inject
    OpenWeatherGsonService gsonService;

    public Weather getWeather(final String locationId) {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget base = client.target(BASE_URL).queryParam(LOCATION_ID, locationId)./*queryParam(PARAM_UNITS, UNITS_METRIC).*/queryParam(PARAM_APP_ID, APP_ID);
            String response = base.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
            Weather weather = gsonService.fromJson(response, Weather.class);
            LOGGER.info("Retrieved Weather, temperature: " + weather.getMain().getTemp());
            return weather;
        } catch (ResponseProcessingException e) {
            LOGGER.error(e);
        } catch (ProcessingException e) {
            LOGGER.error(e);
        } catch (WebApplicationException e) {
            LOGGER.error(e);
        } catch (Exception e) {
            LOGGER.fatal("Something is really wrong here??", e);
        }

        return null;
    }

}
