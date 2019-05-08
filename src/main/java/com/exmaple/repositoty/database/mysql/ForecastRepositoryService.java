package com.exmaple.repositoty.database.mysql;

import com.exmaple.repositoty.database.mysql.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ForecastRepositoryService {
    @Autowired
    private ForecastRepository forecastRepository;

    public CompletableFuture<List<Forecast>> getForecastByCityId(String cityId) {
        return CompletableFuture.completedFuture(forecastRepository.findAllByCityId(Long.parseLong(cityId)));
    }
}
