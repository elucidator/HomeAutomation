package nl.elucidator.homeautomation.energy.powermeter.rest;

import nl.elucidator.homeautomation.energy.powermeter.collector.DataCollector;
import nl.elucidator.homeautomation.energy.powermeter.data.DMSRData;
import nl.elucidator.homeautomation.energy.powermeter.elastic.ElasticClient;
import nl.elucidator.homeautomation.energy.powermeter.processor.DMSRDataProcessor;
import nl.elucidator.homeautomation.weather.openweather.timed.TimedWeatherInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

/**
 * Created by pieter on 1/8/14.
 */
@Path("/powermeter")
public class PowerMeter {

    private static final Logger LOGGER = LogManager.getLogger(PowerMeter.class);
    private static final Logger GSON_LOGGER = LogManager.getLogger("GsonLogger");

    @Inject
    private DataCollector collector;
    @Inject
    private ElasticClient elasticClient;
    @Inject
    private DMSRDataProcessor processor = new DMSRDataProcessor();
    @Inject
    private TimedWeatherInfo timedWeatherInfo;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/event/{data}")
    public String partialData(@Context HttpServletRequest request, @PathParam("data") @Encoded String data) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Received data: " + data);
        }

        byte[] encodedData = DatatypeConverter.parseBase64Binary(data);
        String encodedString = new String(encodedData);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Encoded data: " + encodedString);
        }
        if (collector.add(encodedString)) {
            DMSRData dmsrData = processor.process(collector.getData());
            String gsonData = dmsrData.gson();
            elasticClient.add(gsonData);
            GSON_LOGGER.info(gsonData);

        }
        return data;
    }
}
