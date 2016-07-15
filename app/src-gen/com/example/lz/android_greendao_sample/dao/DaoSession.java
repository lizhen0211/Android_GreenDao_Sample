package com.example.lz.android_greendao_sample.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.lz.android_greendao_sample.SimpleEntity;
import com.example.lz.android_greendao_sample.Province;
import com.example.lz.android_greendao_sample.City;
import com.example.lz.android_greendao_sample.CustomTypeEntity;

import com.example.lz.android_greendao_sample.dao.SimpleEntityDao;
import com.example.lz.android_greendao_sample.dao.ProvinceDao;
import com.example.lz.android_greendao_sample.dao.CityDao;
import com.example.lz.android_greendao_sample.dao.CustomTypeEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig simpleEntityDaoConfig;
    private final DaoConfig provinceDaoConfig;
    private final DaoConfig cityDaoConfig;
    private final DaoConfig customTypeEntityDaoConfig;

    private final SimpleEntityDao simpleEntityDao;
    private final ProvinceDao provinceDao;
    private final CityDao cityDao;
    private final CustomTypeEntityDao customTypeEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        simpleEntityDaoConfig = daoConfigMap.get(SimpleEntityDao.class).clone();
        simpleEntityDaoConfig.initIdentityScope(type);

        provinceDaoConfig = daoConfigMap.get(ProvinceDao.class).clone();
        provinceDaoConfig.initIdentityScope(type);

        cityDaoConfig = daoConfigMap.get(CityDao.class).clone();
        cityDaoConfig.initIdentityScope(type);

        customTypeEntityDaoConfig = daoConfigMap.get(CustomTypeEntityDao.class).clone();
        customTypeEntityDaoConfig.initIdentityScope(type);

        simpleEntityDao = new SimpleEntityDao(simpleEntityDaoConfig, this);
        provinceDao = new ProvinceDao(provinceDaoConfig, this);
        cityDao = new CityDao(cityDaoConfig, this);
        customTypeEntityDao = new CustomTypeEntityDao(customTypeEntityDaoConfig, this);

        registerDao(SimpleEntity.class, simpleEntityDao);
        registerDao(Province.class, provinceDao);
        registerDao(City.class, cityDao);
        registerDao(CustomTypeEntity.class, customTypeEntityDao);
    }
    
    public void clear() {
        simpleEntityDaoConfig.getIdentityScope().clear();
        provinceDaoConfig.getIdentityScope().clear();
        cityDaoConfig.getIdentityScope().clear();
        customTypeEntityDaoConfig.getIdentityScope().clear();
    }

    public SimpleEntityDao getSimpleEntityDao() {
        return simpleEntityDao;
    }

    public ProvinceDao getProvinceDao() {
        return provinceDao;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public CustomTypeEntityDao getCustomTypeEntityDao() {
        return customTypeEntityDao;
    }

}
