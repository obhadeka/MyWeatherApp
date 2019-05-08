package com.exmaple.repositoty.database.mysql;

import com.exmaple.repositoty.database.mysql.model.Forecast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ForecastRepository extends CrudRepository<Forecast, Integer> {
    List<Forecast> findAllByCityId(@Param("city_id") Long city_id);
}
