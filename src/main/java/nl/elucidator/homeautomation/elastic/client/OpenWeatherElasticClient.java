package nl.elucidator.homeautomation.elastic.client;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
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