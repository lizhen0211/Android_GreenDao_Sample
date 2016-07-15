package com.example.lz.android_greendao_sample.entity;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.example.lz.android_greendao_sample.CustomTypeEntity;
import com.example.lz.android_greendao_sample.dao.CustomTypeEntityDao;

public class CustomTypeEntityTest extends AbstractDaoTestLongPk<CustomTypeEntityDao, CustomTypeEntity> {

    public CustomTypeEntityTest() {
        super(CustomTypeEntityDao.class);
    }

    @Override
    protected CustomTypeEntity createEntity(Long key) {
        CustomTypeEntity entity = new CustomTypeEntity();
        entity.setId(key);
        return entity;
    }

}
