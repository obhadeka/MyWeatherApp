package com.exmaple.entity;

public class ForecastView {
    private String id;
    private String date;
    private String day;
    private String tempHigh;
    private String tempLow;
    private String currentCondition;
    private String unit;

    public ForecastView() {
    }

    public ForecastView(String id, String date, String day, String tempHigh, String tempLow, String currentCondition, String unit) {
        this.id = id;
        this.date = date;
        this.day = day;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
        this.currentCondition = currentCondition;
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForecastView)) return false;

        ForecastView that = (ForecastView) o;

        if (!getId().equals(that.getId())) return false;
        if (!getDate().equals(that.getDate())) return false;
        if (!getDay().equals(that.getDay())) return false;
        if (!getTempHigh().equals(that.getTempHigh())) return false;
        if (!getTempLow().equals(that.getTempLow())) return false;
        if (!getCurrentCondition().equals(that.getCurrentCondition())) return false;
        return getUnit().equals(that.getUnit());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getDay().hashCode();
        result = 31 * result + getTempHigh().hashCode();
        result = 31 * result + getTempLow().hashCode();
        result = 31 * result + getCurrentCondition().hashCode();
        result = 31 * result + getUnit().hashCode();
        return result;
    }
}
