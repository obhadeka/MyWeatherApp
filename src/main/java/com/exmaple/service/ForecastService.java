package com.exmaple.service;

import com.exmaple.constants.Errors;
import com.exmaple.exception.ServiceException;
import com.exmaple.repositoty.database.mysql.ForecastRepositoryService;
import com.exmaple.repositoty.database.mysql.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ForecastService {
    @Autowired
    private ForecastRepositoryService forecastRepositoryService;

    public CompletableFuture<List<Forecast>> getAllForecastsByCityId(final String cityId) {
        CompletableFuture<List<Forecast>> response = new CompletableFuture<>();

        try {
            forecastRepositoryService.getForecastByCityId(cityId)
                    .thenAccept(forecasts -> {
                        if (forecasts == null || CollectionUtils.isEmpty(forecasts)) {
                            //TODO: Change the error message
                            throw new ServiceException(Errors.GENERIC_ERROR);
                        }
                        response.complete(forecasts);
                    })
                    .exceptionally(throwable -> {
                        response.completeExceptionally(throwable);
                        return null;
                    });
        } catch (Exception e) {
            response.completeExceptionally(e);
        }

        return response;
    }
}
