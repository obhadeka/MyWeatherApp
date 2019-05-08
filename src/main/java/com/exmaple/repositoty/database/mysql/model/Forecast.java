package com.exmaple.repositoty.database.mysql.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "forecasts")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @NotNull
    private String tempHigh;

    @NotNull
    private String tempLow;

    @NotNull
    private String currentCondition;

    @NotNull
    private String unit;

    @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JsonBackReference
    private City city;

    public Forecast() {
    }

    public Forecast(Date date, String tempHigh, String tempLow, String currentCondition, String unit) {
        this.date = date;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
        this.currentCondition = currentCondition;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(String tempHigh) {
        this.tempHigh = tempHigh;
    }

    public String getTempLow() {
        return tempLow;
    }

    public void setTempLow(String tempLow) {
        this.tempLow = tempLow;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
