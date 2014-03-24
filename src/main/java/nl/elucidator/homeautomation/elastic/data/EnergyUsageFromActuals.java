package nl.elucidator.homeautomation.elastic.data;

import nl.elucidator.homeautomation.elastic.DataConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.Facets;
import org.elasticsearch.search.facet.datehistogram.DateHistogramFacet;
import org.elasticsearch.search.facet.datehistogram.DateHistogramFacetBuilder;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by pieter on 3/23/14.
 */
public class EnergyUsageFromActuals {

    private static final Logger LOGGER = LogManager.getLogger(EnergyUsageFromActuals.class);
    public static final String FACET_NAME = "EnergyDateHistogramFacet";

    Client client;


    public List<? extends DateHistogramFacet.Entry> getActualPowerHistogram(DateTime start) {
        return this.getActualPowerHistogram(start, DateTime.now());
    }


    public List<? extends DateHistogramFacet.Entry> getActualPowerHistogram(DateTime start, DateTime end) {
        return getHistogramEntries(DataConstants.INDEX_SMARTMETER, start, end, DataConstants.ELECTRICITY_DATA_ACTUAL_POWER, DataConstants.INTERVAL_ONE_HOUR);
    }

    private List<? extends DateHistogramFacet.Entry> getHistogramEntries(final String index, final DateTime start, final DateTime end, final String valueField, final String interval) {

        DateHistogramFacetBuilder facet = FacetBuilders.dateHistogramFacet(FACET_NAME).keyField(DataConstants.TIME_STAMP).valueField(valueField).interval(interval);

        BoolFilterBuilder topLevelFilterBuilder = FilterBuilders.boolFilter();

        RangeFilterBuilder rangeFilterBuilder = new RangeFilterBuilder(DataConstants.TIME_STAMP).from(start.getMillis()).to(end.getMillis());
        FilterBuilder matchAllQueryBuilder = FilterBuilders.matchAllFilter();

        topLevelFilterBuilder.must(matchAllQueryBuilder, rangeFilterBuilder);

        FilteredQueryBuilder filteredQueryBuilder = new FilteredQueryBuilder(QueryBuilders.queryString("*"), topLevelFilterBuilder);

        QueryFilterBuilder queryFilterBuilder = new QueryFilterBuilder(filteredQueryBuilder);

        facet.facetFilter(queryFilterBuilder);

        SearchRequestBuilder facetSearch = client.prepareSearch(index).addFacet(facet);

        LOGGER.trace("Composed query: {}", facetSearch.toString());

        SearchResponse searchResponse = facetSearch.execute().actionGet();

        Facets facets = searchResponse.getFacets();

        DateHistogramFacet resultFacet = facets.facet(FACET_NAME);

        return resultFacet.getEntries();
    }

}
