package com.example.lz.android_greendao_sample.external_entity;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.example.lz.android_greendao_sample.external_dao.SimpleExternalEntityDao;

public class SimpleExternalEntityTest extends AbstractDaoTestLongPk<SimpleExternalEntityDao, SimpleExternalEntity> {

    public SimpleExternalEntityTest() {
        super(SimpleExternalEntityDao.class);
    }

    @Override
    protected SimpleExternalEntity createEntity(Long key) {
        SimpleExternalEntity entity = new SimpleExternalEntity();
        entity.setId(key);
        return entity;
    }

}
