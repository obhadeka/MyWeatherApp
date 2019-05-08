package com.exmaple.repositoty.database.mysql.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String region;

    @OneToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "city_forecast",
        joinColumns = { @JoinColumn(name = "city_id") },
        inverseJoinColumns = {@JoinColumn(name = "forecast_id")
    })
    private List<City> cities = new ArrayList<>();

    public City() {
    }

    public City(String name, String region) {
        this.name = name;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private List<City> getCities() {
        return cities;
    }

    private void setCities() {
        this.cities = cities;
    }
}
