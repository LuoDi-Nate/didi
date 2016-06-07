package com.diwa.euclid.model;

import java.sql.Timestamp;

public class Euclid {
    private Integer id;

    private Timestamp date;

    private Integer timePiece;

    private Integer weather;

    private Integer pm;

    private Integer temperature;

    private String poiClass;

    private Integer tjLevel;

    private Double euclidValue;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Euclid{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", timePiece=").append(timePiece);
        sb.append(", weather=").append(weather);
        sb.append(", pm=").append(pm);
        sb.append(", temperature=").append(temperature);
        sb.append(", poiClass='").append(poiClass).append('\'');
        sb.append(", tjLevel=").append(tjLevel);
        sb.append(", euclidValue=").append(euclidValue);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getTimePiece() {
        return timePiece;
    }

    public void setTimePiece(Integer timePiece) {
        this.timePiece = timePiece;
    }

    public Integer getWeather() {
        return weather;
    }

    public void setWeather(Integer weather) {
        this.weather = weather;
    }

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getPoiClass() {
        return poiClass;
    }

    public void setPoiClass(String poiClass) {
        this.poiClass = poiClass;
    }

    public Integer getTjLevel() {
        return tjLevel;
    }

    public void setTjLevel(Integer tjLevel) {
        this.tjLevel = tjLevel;
    }

    public Double getEuclidValue() {
        return euclidValue;
    }

    public void setEuclidValue(Double euclidValue) {
        this.euclidValue = euclidValue;
    }
}