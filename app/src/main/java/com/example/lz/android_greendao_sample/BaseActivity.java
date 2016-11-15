package com.example.lz.android_greendao_sample;

import android.app.Activity;

import com.example.lz.android_greendao_sample.dao.DaoSession;

/**
 * Created by lz on 2016/7/14.
 */
public class BaseActivity extends Activity {

    public DaoSession getDaoSession() {
        return ((BaseApplication) getApplication()).getDaoSession();
    }

    public com.example.lz.android_greendao_sample.external_dao.DaoSession getStorageDaoSession(){
        return ((BaseApplication) getApplication()).getExternalDaoSession();
    }
}
