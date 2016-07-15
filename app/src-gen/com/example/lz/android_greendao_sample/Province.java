package com.example.lz.android_greendao_sample;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * This entity is used by internal tests of greenDAO.
 * (This JavaDoc is defined in the generator project.)
 */
// This is another test comment, you could also apply annotations like this
@Entity
public class Province {

    /**
     * JavaDoc test field
     */
    @Id
    private Long id;
    private String name;
    private String alias;
    private Integer cityQuantity;
    private Long populationQuantity;
    private Integer Ranking;

    @Generated
    public Province() {
    }

    public Province(Long id) {
        this.id = id;
    }

    @Generated
    public Province(Long id, String name, String alias, Integer cityQuantity, Long populationQuantity, Integer Ranking) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.cityQuantity = cityQuantity;
        this.populationQuantity = populationQuantity;
        this.Ranking = Ranking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * province name
     */
    public String getName() {
        return name;
    }

    /**
     * province name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getCityQuantity() {
        return cityQuantity;
    }

    public void setCityQuantity(Integer cityQuantity) {
        this.cityQuantity = cityQuantity;
    }

    public Long getPopulationQuantity() {
        return populationQuantity;
    }

    public void setPopulationQuantity(Long populationQuantity) {
        this.populationQuantity = populationQuantity;
    }

    public Integer getRanking() {
        return Ranking;
    }

    public void setRanking(Integer Ranking) {
        this.Ranking = Ranking;
    }

}