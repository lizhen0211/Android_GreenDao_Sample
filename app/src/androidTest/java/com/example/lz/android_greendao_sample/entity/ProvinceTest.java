package com.example.lz.android_greendao_sample.entity;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.example.lz.android_greendao_sample.Province;
import com.example.lz.android_greendao_sample.dao.ProvinceDao;

public class ProvinceTest extends AbstractDaoTestLongPk<ProvinceDao, Province> {

    public ProvinceTest() {
        super(ProvinceDao.class);
    }

    @Override
    protected Province createEntity(Long key) {
        Province entity = new Province();
        entity.setId(key);
        return entity;
    }

}
