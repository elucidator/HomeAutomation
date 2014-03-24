package nl.elucidator.homeautomation.elastic.data;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.facet.datehistogram.DateHistogramFacet;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pieter on 3/23/14.
 */
public class EnergyUsageFromActualsTest {
    EnergyUsageFromActuals energyUsageFromActuals;

    @Before
    public void before() {
        energyUsageFromActuals = new EnergyUsageFromActuals();
        energyUsageFromActuals.client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("192.168.1.142", 9300));
    }

    @Ignore
    @Test
    public void startFrom() {
        DateTime start = DateTime.now().minusDays(1);
        List<? extends DateHistogramFacet.Entry> powerHistogram = energyUsageFromActuals.getActualPowerHistogram(start);
        assertThat(powerHistogram.size(), is(either(is(23)).or(is(24))));
    }
}
