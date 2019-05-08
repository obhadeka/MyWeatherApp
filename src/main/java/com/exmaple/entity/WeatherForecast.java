package com.exmaple.entity;

import com.exmaple.repositoty.database.mysql.model.City;

import java.util.ArrayList;
import java.util.List;

public class WeatherForecast {
    private City city;
    private List<ForecastView> forecasts;

    public WeatherForecast() {
    }

    public WeatherForecast(City city, List<ForecastView> forecasts) {
        this.city = city;
        this.forecasts = forecasts;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<ForecastView> getForecasts() {
        if (forecasts == null) {
            forecasts = new ArrayList<>();
        }
        return this.forecasts;
    }

    public void setForecasts(List<ForecastView> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherForecast)) return false;

        WeatherForecast that = (WeatherForecast) o;

        if (!getCity().equals(that.getCity())) return false;
        return getForecasts().equals(that.getForecasts());
    }

    @Override
    public int hashCode() {
        int result = getCity().hashCode();
        result = 31 * result + getForecasts().hashCode();
        return result;
    }
}
