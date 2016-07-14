package com.example.lz.android_greendao_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lz.android_greendao_sample.dao.DaoSession;
import com.example.lz.android_greendao_sample.dao.SimpleEntityDao;

public class MainActivity extends Activity {

    SimpleEntityDao simpleEntityDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseApplication application = (BaseApplication) getApplication();
        DaoSession daoSession = application.getDaoSession();

        simpleEntityDao = daoSession.getSimpleEntityDao();


    }

    public void onClickSimpleEntity(View view) {
        Intent intent = new Intent(this, SimpleEntityActivity.class);
        startActivity(intent);
    }
}
