package nl.elucidator.homeautomation.elastic;

import nl.elucidator.homeautomation.configuration.NamedProperty;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by pieter on 3/5/14.
 */
public class ElasticClientProducder {

    @Inject
    @NamedProperty(key = "elastic.host")
    private String host;
    @Inject
    @NamedProperty(key = "elastic.port")
    private int port;

    @Produces
    public Client createElasticClient() {
        return new TransportClient().addTransportAddress(new InetSocketTransportAddress(host, port));
    }
}
