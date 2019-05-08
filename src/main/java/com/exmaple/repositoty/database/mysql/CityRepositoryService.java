package com.exmaple.repositoty.database.mysql;

import com.exmaple.repositoty.database.mysql.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class CityRepositoryService {
    @Autowired
    private CityRepository cityRepository;

    public CompletableFuture<City> getCityByName(String cityName) {
        return CompletableFuture.completedFuture(cityRepository.findByName(cityName));
    }
}
