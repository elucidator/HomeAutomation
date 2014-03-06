
package nl.elucidator.homeautomation.weather.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class Weather {

    @Expose()
    @Valid
    private Coord coord;
    @Expose
    @Valid
    private Sys sys;
    @Expose
    @Valid
    private List<Weather_> weather = new ArrayList<Weather_>();
    @Expose
    private String base;
    @Expose
    @Valid
    private Main main;
    @Expose
    @Valid
    private Wind wind;
    @Expose
    @Valid
    private Clouds clouds;
    @Expose
    @SerializedName("dt")
    private DateTime timeStamp;
    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private Integer cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Weather withCoord(Coord coord) {
        this.coord = coord;
        return this;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Weather withSys(Sys sys) {
        this.sys = sys;
        return this;
    }

    public List<Weather_> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather_> weather) {
        this.weather = weather;
    }

    public Weather withWeather(List<Weather_> weather) {
        this.weather = weather;
        return this;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Weather withBase(String base) {
        this.base = base;
        return this;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather withMain(Main main) {
        this.main = main;
        return this;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Weather withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Weather withClouds(Clouds clouds) {
        this.clouds = clouds;
        return this;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Weather withDt(DateTime dt) {
        this.timeStamp = dt;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Weather withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weather withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Weather withCod(Integer cod) {
        this.cod = cod;
        return this;
    }

}
