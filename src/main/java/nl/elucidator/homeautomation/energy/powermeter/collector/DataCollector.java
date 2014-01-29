package nl.elucidator.homeautomation.energy.powermeter.collector;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by pieter on 1/9/14.
 */
@ApplicationScoped
public class DataCollector {
    private List<String> dataRows = new Stack<String>();

    public boolean add(String dataRow) {
        dataRows.add(dataRow);
        return dataRow.equals("!");
    }

    public void reset() {
        dataRows.clear();
    }

    public List<String> getData() {
        List<String> result= new ArrayList<>(dataRows);
        dataRows.clear();
        return result;
    }
}
