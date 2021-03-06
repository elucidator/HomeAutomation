package nl.elucidator.homeautomation.elastic.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import javax.inject.Inject;
import java.util.Map;

/**
 * Abstract base for Elastic client
 */
public abstract class AbstractElasticClient {
    private static final Logger LOGGER = LogManager.getLogger(AbstractElasticClient.class);
    private static final Logger GSON_LOGGER = LogManager.getLogger("GsonLogger");

    @Inject
    private Client client;


    public abstract String getIndex();

    public abstract String getType();

    public String add(long id, final Map<String, Object> data) {
        IndexResponse indexResponse = client.prepareIndex(getIndex(), getType(), Long.toString(id)).setSource(data).execute().actionGet();
        return indexResponse.getId();
    }

    public String add(final String data) {
        client.prepareIndex(getIndex(), getType()).setSource(data).execute(new ClientActionListener());
        GSON_LOGGER.info(data);
        return "Ok";
    }

    public Client getClient() {
        return client;
    }

    private class ClientActionListener implements ActionListener<IndexResponse> {

        @Override
        public void onResponse(IndexResponse indexResponse) {
            LOGGER.info("Added: " + indexResponse.getId());
        }

        @Override
        public void onFailure(Throwable e) {
            LOGGER.error("Failure: " + e.toString());
        }
    }
}
