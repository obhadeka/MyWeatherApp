package com.exmaple.service;

import com.exmaple.constants.Errors;
import com.exmaple.exception.ServiceException;
import com.exmaple.repositoty.database.mysql.CityRepositoryService;
import com.exmaple.repositoty.database.mysql.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CityService {
    @Autowired
    private CityRepositoryService cityRepositoryService;

    public CompletableFuture<City> getCityByName(final String cityName) {
        CompletableFuture<City> response = new CompletableFuture<>();

        try {
            cityRepositoryService.getCityByName(cityName)
                    .thenAccept(city -> {
                        if (city == null) {
                            //TODO: Change the error message
                            throw new ServiceException(Errors.GENERIC_ERROR);
                        }
                        response.complete(city);
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
