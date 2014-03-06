
package nl.elucidator.homeautomation.weather.openweather.model;

import com.google.gson.annotations.Expose;

public class Main {

    @Expose
    private Double temp;
    @Expose
    private Double pressure;
    @Expose
    private Double temp_min;
    @Expose
    private Double temp_max;
    @Expose
    private Double humidity;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Main withTemp(Double temp) {
        this.temp = temp;
        return this;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Main withPressure(Double pressure) {
        this.pressure = pressure;
        return this;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Main withTemp_min(Double temp_min) {
        this.temp_min = temp_min;
        return this;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Main withTemp_max(Double temp_max) {
        this.temp_max = temp_max;
        return this;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Main withHumidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }

}
