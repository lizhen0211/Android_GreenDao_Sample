package com.example.lz.android_greendao_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lz.android_greendao_sample.dao.KeepEntityDao;

public class KeepSectionsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_sections);
    }

    public void onUseKeepMethodClick(View view) {
        KeepEntityDao keepEntityDao = getDaoSession().getKeepEntityDao();
        KeepEntity entity = new KeepEntity();
        entity.setKeepField("test");
        entity.setName("name");
        entity.setType("type");
        keepEntityDao.insert(entity);
    }
}
