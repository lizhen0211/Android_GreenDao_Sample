package com.example.lz.android_greendao_sample;

import android.app.Application;
import android.os.Environment;

import com.example.lz.android_greendao_sample.dao.DaoMaster;
import com.example.lz.android_greendao_sample.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

import java.io.File;

/**
 * Created by lz on 2016/7/14.
 */
public class BaseApplication extends Application {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = true;

    /**
     * 内部存储数据库session
     */
    private DaoSession daoSession;

    /**
     * 外部存储数据库session 用于存放大数据缓存
     */
    private com.example.lz.android_greendao_sample.external_dao.DaoSession externalDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String externalDbPath = externalStorageDirectory.getPath() + "/" + "greendao_cache";
        com.example.lz.android_greendao_sample.external_dao.DaoMaster.DevOpenHelper externalHelper = new com.example.lz.android_greendao_sample.external_dao.DaoMaster.DevOpenHelper(this, ENCRYPTED ? externalDbPath + "/notes-db-encrypted" : externalDbPath + "/notes-db");
        Database externalDb = ENCRYPTED ? externalHelper.getEncryptedWritableDb("super-secret") : externalHelper.getWritableDb();
        externalDaoSession = new com.example.lz.android_greendao_sample.external_dao.DaoMaster(externalDb).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public com.example.lz.android_greendao_sample.external_dao.DaoSession getExternalDaoSession() {
        return externalDaoSession;
    }
}
