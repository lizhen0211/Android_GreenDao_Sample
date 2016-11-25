package com.example.lz.android_greendao_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lz.android_greendao_sample.dao.DaoSession;
import com.example.lz.android_greendao_sample.dao.SimpleEntityDao;
import com.example.lz.android_greendao_sample.external_dao.SimpleExternalEntityDao;

public class MainActivity extends Activity {

    SimpleEntityDao simpleEntityDao;

    SimpleExternalEntityDao simpleExternalEntityDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseApplication application = (BaseApplication) getApplication();
        DaoSession daoSession = application.getDaoSession();

        simpleEntityDao = daoSession.getSimpleEntityDao();

        com.example.lz.android_greendao_sample.external_dao.DaoSession externalDaoSession = application.getExternalDaoSession();
        simpleExternalEntityDao = externalDaoSession.getSimpleExternalEntityDao();
    }

    public void onClickSimpleEntity(View view) {
        Intent intent = new Intent(this, SimpleEntityActivity.class);
        startActivity(intent);
    }

    public void onClickQueries(View view) {
        Intent intent = new Intent(this, QuerysActivity.class);
        startActivity(intent);
    }

    public void onJoinsClcik(View view) {
        Intent intent = new Intent(this, JoinsActivity.class);
        startActivity(intent);
    }

    public void onKeepSectionsClcik(View view) {
        Intent intent = new Intent(this, KeepSectionsActivity.class);
        startActivity(intent);
    }

    public void onRelationsClick(View view){
        Intent intent = new Intent(this,RelationsActivity.class);
        startActivity(intent);
    }
}
