package nl.elucidator.homeautomation.energy.powermeter.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.elucidator.homeautomation.energy.powermeter.gson.DateTimeConverter;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pieter on 1/15/14.
 */
public class DMSRData {

    private ElectricityData electricityData;
    private List<GasData> gasDataList;

    private String header;
    private List<GasData> gasData;
    private DateTime timeStamp;
    private BigDecimal gasUsage;
    private int gasUsage2;

    public DMSRData() {
        gasUsage = new BigDecimal(0);

    }

    public void setElectricityData(ElectricityData electricityData) {
        this.electricityData = electricityData;
    }

    public ElectricityData getElectricityData() {
        return electricityData;
    }

    public List<GasData> getGasDataList() {
        return gasDataList;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setGasData(List<GasData> gasData) {
        this.gasData = gasData;
    }

    public List<GasData> getGasData() {
        return gasData;
    }

    public String gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        Gson gson = gsonBuilder.create();
      return gson.toJson(this);
    }

    public void setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public void setGasUsage(BigDecimal gasUsage) {
        this.gasUsage = gasUsage;
        gasUsage2 = (gasUsage.multiply(new BigDecimal("1000"))).intValue();
    }

    public BigDecimal getGasUsage() {
        return gasUsage;
    }

}
