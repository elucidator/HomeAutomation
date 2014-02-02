package nl.elucidator.homeautomation.elastic;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.util.Map;

/**
 * Abstract base for Elastic client
 */
public abstract class AbstractElasticClient {
    private final Client client;
    private static final String ELASTIC_HOST = "192.168.1.142";
    private static final int ELASTIC_PORT = 9300;

    public abstract String getIndex();

    public abstract String getType();


    public AbstractElasticClient() {
        client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(ELASTIC_HOST, ELASTIC_PORT));
    }

    public String add(long id, final Map<String, Object> data) {
        IndexResponse indexResponse = client.prepareIndex(getIndex(), getType(), Long.toString(id)).setSource(data).execute().actionGet();
        return indexResponse.getId();
    }

    public String add(final String data) {
        IndexResponse indexResponse = client.prepareIndex(getIndex(), getType()).setSource(data).execute().actionGet();
        return indexResponse.getId();
    }

    public Client getClient() {
        return client;
    }
}
