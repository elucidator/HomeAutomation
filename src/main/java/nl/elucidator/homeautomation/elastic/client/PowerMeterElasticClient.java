package nl.elucidator.homeautomation.elastic.client;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PowerMeterElasticClient extends AbstractElasticClient {

    private static final String INDEX = "smartmeter";
    private static final String TYPE = "record";


    @Override
    public String getIndex() {
        return INDEX;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}