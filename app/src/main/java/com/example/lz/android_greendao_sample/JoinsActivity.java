package com.example.lz.android_greendao_sample;

import android.os.Bundle;
import android.view.View;

import com.example.lz.android_greendao_sample.dao.CityDao;
import com.example.lz.android_greendao_sample.dao.DaoSession;
import com.example.lz.android_greendao_sample.dao.ProvinceDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class JoinsActivity extends BaseActivity {

    DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joins);
        daoSession = getDaoSession();
    }

    public void onSimpleJoinsClick(View view) {
        ProvinceDao provinceDao = daoSession.getProvinceDao();
        QueryBuilder<Province> provinceQueryBuilder = provinceDao.queryBuilder();
        provinceQueryBuilder.join(City.class, CityDao.Properties.ProvinceID);
        provinceQueryBuilder.where(CityDao.Properties.Name.eq("鞍山"));
        List<Province> list = provinceQueryBuilder.list();
        System.out.print(list);
    }

    public void onChainedJoinsClick(View view) {

    }

    public void onSelfJoinsClick(View view) {

    }
}
