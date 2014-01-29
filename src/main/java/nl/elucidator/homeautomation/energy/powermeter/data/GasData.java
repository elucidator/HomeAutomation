package nl.elucidator.homeautomation.energy.powermeter.data;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by pieter on 1/22/14.
 */
public class GasData {

    private String type;
    private String equipmentId;
    private ValvePosition valve;
    private DateTime timeStamp;
    private BigDecimal reading;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setValve(String valve) {
        this.valve = "1".equals(valve) ? ValvePosition.OPEN : ValvePosition.CLOSED;
    }

    public ValvePosition getValve() {
        return valve;
    }

    public void setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public void setReading(String reading) {
        this.reading = new BigDecimal(reading);
    }

    public BigDecimal getReading() {
        return reading;
    }
}
