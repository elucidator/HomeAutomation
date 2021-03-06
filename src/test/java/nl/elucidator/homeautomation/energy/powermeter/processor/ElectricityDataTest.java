package nl.elucidator.homeautomation.energy.powermeter.processor;

import nl.elucidator.homeautomation.energy.powermeter.data.DMSRData;
import nl.elucidator.homeautomation.energy.powermeter.data.ElectricityData;
import nl.elucidator.homeautomation.energy.powermeter.data.SwitchPosition;
import nl.elucidator.homeautomation.energy.powermeter.data.Tariff;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pieter on 1/22/14.
 */
public class ElectricityDataTest {
    private static final String RAW_DATA_1 = "ISk5-2MT382-1003, 0-0:96.1.1(5A424556303035313036383434393132), 1-0:1.8.1(01552.438*kWh), 1-0:1.8.2(01849.327*kWh), 1-0:2.8.1(00000.000*kWh), 1-0:2.8.2(00000.005*kWh), 0-0:96.14.0(0002), 1-0:1.7.0(0001.04*kW), 1-0:2.7.0(0000.00*kW), 0-0:17.0.0(0999.00*kW), 0-0:96.3.10(1), 0-0:96.13.1(), 0-0:96.13.0(), 0-1:24.1.0(3), 0-1:96.1.0(3238303131303038323036313435383132), 0-1:24.3.0(140115170000)(00)(60)(1)(0-1:24.2.1)(m3), (00733.261), 0-1:24.4.0(1), 0-2:24.1.0(3), 0-2:96.1.0(3238303131303038333036343239303133), 0-2:24.3.0(140115170000)(00)(60)(1)(0-2:24.2.1)(m3), (01107.089), 0-2:24.4.0(1), !";
    private static final DMSRDataProcessor PROCESSOR = new DMSRDataProcessor();
    private ElectricityData electricityData;
    private DMSRData data;

    @Before
    public void before() {
        data = PROCESSOR.process(Arrays.asList(RAW_DATA_1.split(",")));
        electricityData = data.getElectricityData();
    }

    @Test
    public void equipmentId() {
        assertThat(data.getElectricityData().getEquipmentId(), is("5A424556303035313036383434393132"));
    }


    @Test
    public void lowTarrifToReading() {
        assertThat(electricityData.getLowTarrifToReading(), is(1552438));
    }


    @Test
    public void normalTarrifToReading() {
        assertThat(electricityData.getNormalTarrifToReading(), is(1849327));
    }


    @Test
    public void lowTarrifByReading() {
        assertThat(electricityData.getLowTarrifByReading(), is(0));
    }


    @Test
    public void normalTarrifByReading() {
        assertThat(electricityData.getNormalTarrifByReading(), is(5));
    }


    @Test
    public void tarrifIndicator() {
        assertThat(electricityData.getTariffIndicator(), is(Tariff.HIGH));
    }


    @Test
    public void actualPower() {
        assertThat(electricityData.getActualPower(), is(1040));
    }


    @Test
    public void actualPowerRecieved() {
        assertThat(electricityData.getActualPowerRecieved(), is(0));
    }

    ;

    @Test
    public void actualTreshHold() {
        assertThat(electricityData.getActualTreshHold(), is(999000));
    }

    ;

    @Test
    public void switchPosition() {
        assertThat(electricityData.getSwitchPosition(), is(SwitchPosition.CLOSED));
    }

    ;


}
