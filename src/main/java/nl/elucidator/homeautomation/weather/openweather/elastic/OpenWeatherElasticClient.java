package nl.elucidator.homeautomation.weather.openweather.elastic;

import nl.elucidator.homeautomation.elastic.AbstractElasticClient;

import javax.ejb.Stateless;

@Stateless
public class OpenWeatherElasticClient extends AbstractElasticClient {

    private static final String INDEX = "smartmeter";
    private static final String TYPE = "weather";


    @Override
    public String getIndex() {
        return INDEX;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}