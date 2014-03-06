
package nl.elucidator.homeautomation.weather.openweather.model;

import com.google.gson.annotations.Expose;

public class Clouds {

    @Expose
    private Double all;

    public Double getAll() {
        return all;
    }

    public void setAll(Double all) {
        this.all = all;
    }

    public Clouds withAll(Double all) {
        this.all = all;
        return this;
    }

}
