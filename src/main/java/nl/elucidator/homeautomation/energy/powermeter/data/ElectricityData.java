package nl.elucidator.homeautomation.energy.powermeter.data;

import java.math.BigDecimal;

/**
 * Created by pieter on 1/22/14.
 */
public class ElectricityData {
    private static final int KWH = 1000;
    private String equipmentId;
    private int lowTarrifToReading;
    private int normalTarrifToReading;
    private int lowTarrifByReading;
    private int normalTarrifByReading;
    private Tariff tariffIndicator;
    private int actualPower;
    private int actualPowerRecieved;
    private int actualTreshHold;
    private SwitchPosition switchPosition;

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setLowTarrifToReading(String lowTarrifToReading) {
        this.lowTarrifToReading = toWattHour(lowTarrifToReading);
    }

    public int getLowTarrifToReading() {
        return lowTarrifToReading;
    }

    public void setNormalTarrifToReading(String normalTarrifToReading) {
        this.normalTarrifToReading = toWattHour(normalTarrifToReading);
    }

    public int getNormalTarrifToReading() {
        return normalTarrifToReading;
    }

    public void setLowTarrifByReading(String lowTarrifByReading) {
        this.lowTarrifByReading = toWattHour(lowTarrifByReading);
    }

    public int getLowTarrifByReading() {
        return lowTarrifByReading;
    }

    public void setNormalTarrifByReading(String normalTarrifByReading) {
        this.normalTarrifByReading = toWattHour(normalTarrifByReading);
    }

    public int getNormalTarrifByReading() {
        return normalTarrifByReading;
    }

    public void setTariffIndicator(String tariffIndicator) {
        this.tariffIndicator = "0002".equals(tariffIndicator) ? Tariff.HIGH : Tariff.LOW;
    }

    public Tariff getTariffIndicator() {
        return tariffIndicator;
    }

    public void setActualPower(String actualPower) {
        this.actualPower = toWattHour(actualPower);
    }

    public int getActualPower() {
        return actualPower;
    }

    public void setActualPowerRecieved(String actualPowerRecieved) {
        this.actualPowerRecieved = toWattHour(actualPowerRecieved);
    }

    public int getActualPowerRecieved() {
        return actualPowerRecieved;
    }

    public void setActualTreshHold(String actualTreshHold) {
        this.actualTreshHold = toWattHour(actualTreshHold);
    }

    public int getActualTreshHold() {
        return actualTreshHold;
    }

    public void setSwitchPosition(String switchPosition) {
        this.switchPosition = "1".equals(switchPosition) ? SwitchPosition.CLOSED : SwitchPosition.OPEN;
    }

    public SwitchPosition getSwitchPosition() {
        return switchPosition;
    }

    private int toWattHour(final String kwh) {
        String value = kwh.substring(0, kwh.indexOf("*"));
        Double valKwh = new Double(value);

        return (int)(valKwh * new Double(KWH));
    }
}
