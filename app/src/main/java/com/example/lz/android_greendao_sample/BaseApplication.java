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

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
