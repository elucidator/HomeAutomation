package nl.elucidator.homeautomation.elastic.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.datehistogram.DateHistogramFacetBuilder;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Created by pieter on 3/7/14.
 */
public class SampleSearches {
    private TransportClient client;
    private static final Logger LOGGER = LogManager.getLogger(SampleSearches.class);

    @Before
    public void before() {
        client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("192.168.1.142", 9300));
    }

    @Test
    public void dateRange() {
        LOGGER.info("Starting");
        DateTime now = DateTime.now();
        DateTime minusHalfHour = now.minusHours(1);
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("smartmeter").setQuery(
                QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("timeStamp").from(minusHalfHour.toInstant().getMillis()).to("now"))
        ).setTypes("weather");


        System.out.println("searchRequestBuilder.toString() = " + searchRequestBuilder.toString());
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        System.out.println("searchResponse = " + searchResponse);
        SearchHits searchHits = searchResponse.getHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println("searchHit.type() = " + searchHit.type());
            System.out.println(searchHit.sourceAsString());
        }
    }

    @Test
    public void testenergyPerHour() throws ExecutionException, InterruptedException {


        DateTime now = DateTime.now();
        DateTime minusHalfHour = now.minusDays(1);


        DateHistogramFacetBuilder facet = FacetBuilders.dateHistogramFacet("999").keyField("timeStamp").valueField("electricityData.actualPower").interval("1h");
        System.out.println("facet = " + facet);


        BoolFilterBuilder topLevelFilterBuilder = FilterBuilders.boolFilter();

        RangeFilterBuilder rangeFilterBuilder = new RangeFilterBuilder("timeStamp").from(minusHalfHour.toInstant().getMillis()).to("now");
        //BoolFilterBuilder boolMatchAllFilter = FilterBuilders.boolFilter().must(FilterBuilders.matchAllFilter());
        FilterBuilder matchAllQueryBuilder = FilterBuilders.matchAllFilter();

        topLevelFilterBuilder.must(matchAllQueryBuilder, rangeFilterBuilder/*, boolMatchAllFilter*/);

        FilteredQueryBuilder filteredQueryBuilder = new FilteredQueryBuilder(QueryBuilders.queryString("*"), topLevelFilterBuilder);

        QueryFilterBuilder queryFilterBuilder = new QueryFilterBuilder(filteredQueryBuilder);


        facet.facetFilter(queryFilterBuilder);

        SearchRequestBuilder facetSearch = client.prepareSearch("smartMeter").addFacet(facet).setSize(0);

        System.out.println("facetSearch = " + facetSearch);

        //SearchResponse searchResponse = facetSearch.execute().get();
        //System.out.println("searchResponse = " + searchResponse);
        //searchResponse.getFacets();
        //System.out.println("searchResponse.getFacets() = " + searchResponse.getFacets());
    }

}
