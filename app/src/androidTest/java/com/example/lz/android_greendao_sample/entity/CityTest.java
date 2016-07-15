package com.example.lz.android_greendao_sample.entity;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.example.lz.android_greendao_sample.City;
import com.example.lz.android_greendao_sample.dao.CityDao;

public class CityTest extends AbstractDaoTestLongPk<CityDao, City> {

    public CityTest() {
        super(CityDao.class);
    }

    @Override
    protected City createEntity(Long key) {
        City entity = new City();
        entity.setId(key);
        return entity;
    }

}
