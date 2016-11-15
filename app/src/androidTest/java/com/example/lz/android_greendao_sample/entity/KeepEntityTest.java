package com.example.lz.android_greendao_sample.entity;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.example.lz.android_greendao_sample.KeepEntity;
import com.example.lz.android_greendao_sample.dao.KeepEntityDao;

public class KeepEntityTest extends AbstractDaoTestLongPk<KeepEntityDao, KeepEntity> {

    public KeepEntityTest() {
        super(KeepEntityDao.class);
    }

    @Override
    protected KeepEntity createEntity(Long key) {
        KeepEntity entity = new KeepEntity();
        entity.setId(key);
        return entity;
    }

}
