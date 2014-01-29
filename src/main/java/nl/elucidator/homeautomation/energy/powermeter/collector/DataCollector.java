package nl.elucidator.homeautomation.energy.powermeter.collector;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Collector for data recieved by the Rest interface.
 */
@ApplicationScoped
public class DataCollector {
    private List<String> dataRows = new Stack<>();

    /**
     * Add data to the collection.
     *
     * @param dataRow Data
     * @return true when input equals "!"
     */
    public boolean add(String dataRow) {
        dataRows.add(dataRow);
        return dataRow.equals("!");
    }

    public List<String> getData() {
        List<String> result = new ArrayList<>(dataRows);
        dataRows.clear();
        return result;
    }
}
