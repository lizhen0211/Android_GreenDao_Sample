package com.example.lz.android_greendao_sample;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CITY".
 */
@Entity
public class City {

    @Id
    private Long id;
    private String name;
    private Boolean isTerritory;
    private Boolean isCapital;
    private Long populationQuantity;
    private Long provinceID;

    @Generated
    public City() {
    }

    public City(Long id) {
        this.id = id;
    }

    @Generated
    public City(Long id, String name, Boolean isTerritory, Boolean isCapital, Long populationQuantity, Long provinceID) {
        this.id = id;
        this.name = name;
        this.isTerritory = isTerritory;
        this.isCapital = isCapital;
        this.populationQuantity = populationQuantity;
        this.provinceID = provinceID;
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

    public Boolean getIsTerritory() {
        return isTerritory;
    }

    public void setIsTerritory(Boolean isTerritory) {
        this.isTerritory = isTerritory;
    }

    public Boolean getIsCapital() {
        return isCapital;
    }

    public void setIsCapital(Boolean isCapital) {
        this.isCapital = isCapital;
    }

    public Long getPopulationQuantity() {
        return populationQuantity;
    }

    public void setPopulationQuantity(Long populationQuantity) {
        this.populationQuantity = populationQuantity;
    }

    public Long getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Long provinceID) {
        this.provinceID = provinceID;
    }

}
