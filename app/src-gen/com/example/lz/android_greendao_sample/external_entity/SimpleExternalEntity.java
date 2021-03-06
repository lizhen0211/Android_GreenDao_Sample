package com.example.lz.android_greendao_sample.external_entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "SIMPLE_EXTERNAL_ENTITY".
 */
@Entity
public class SimpleExternalEntity {

    @Id(autoincrement = true)
    private Long id;
    private String simpleString;

    @Generated
    public SimpleExternalEntity() {
    }

    public SimpleExternalEntity(Long id) {
        this.id = id;
    }

    @Generated
    public SimpleExternalEntity(Long id, String simpleString) {
        this.id = id;
        this.simpleString = simpleString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSimpleString() {
        return simpleString;
    }

    public void setSimpleString(String simpleString) {
        this.simpleString = simpleString;
    }

}
