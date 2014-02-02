package nl.elucidator.homeautomation.energy.powermeter.elastic;

import nl.elucidator.homeautomation.elastic.AbstractElasticClient;

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