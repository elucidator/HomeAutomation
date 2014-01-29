package nl.elucidator.homeautomation.energy.powermeter.elastic;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class ElasticClient {

    private final Client client;
    private final String index;
    private final String type;

    public ElasticClient() {
        this("smartmeter", "record");
    }

    public ElasticClient(final String index, final String type) {
        this.index = index;
        this.type = type;
        client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("192.168.1.142", 9300));
    }

    public String add(long id, final Map<String, Object> data) {
        IndexResponse indexResponse = client.prepareIndex(index, type, Long.toString(id)).setSource(data).execute().actionGet();
        return indexResponse.getId();
    }

    public String add(final String tweet) {
        IndexResponse indexResponse = client.prepareIndex(index, type).setSource(tweet).execute().actionGet();
        return indexResponse.getId();
    }
}