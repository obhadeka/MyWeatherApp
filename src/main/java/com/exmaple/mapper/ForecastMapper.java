package com.exmaple.mapper;

import com.exmaple.entity.ForecastView;
import com.exmaple.repositoty.database.mysql.model.Forecast;
import com.exmaple.util.CommonUtil;
import com.exmaple.util.DateConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ForecastMapper {
    private static ForecastView convertForecastToForecastView(Forecast forecast) {
        if (forecast == null) {
            return null;
        } else {
            ForecastView forecastView = new ForecastView();

            forecastView.setId(String.valueOf(forecast.getId()));
            forecastView.setTempHigh(forecast.getTempHigh());
            forecastView.setTempLow(forecast.getTempLow());
            forecastView.setCurrentCondition(forecast.getCurrentCondition());
            forecastView.setUnit(forecast.getUnit());
            if (forecast.getDate() != null) {
                forecastView.setDate(DateConverter.convertToDateString(forecast.getDate()));
                forecastView.setDay(CommonUtil.getDayOfWeek(DateConverter.convertToDayOfWeek(forecast.getDate())));
            }
            return forecastView;
        }
    }

    public static List<ForecastView> convertForecastsToForecastViews(List<Forecast> forecasts) {
        if (forecasts == null || CollectionUtils.isEmpty(forecasts)) {
            return null;
        } else {
            List<ForecastView> forecastViews = new ArrayList<>();
            forecasts.stream().forEach(forecast -> {
                forecastViews.add(convertForecastToForecastView(forecast));
            });
            return forecastViews;
        }
    }
}
