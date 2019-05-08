package com.exmaple.repositoty.database.mysql;

import com.exmaple.repositoty.database.mysql.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends CrudRepository<City, Integer> {
    City findByName(@Param("name") String name);
}