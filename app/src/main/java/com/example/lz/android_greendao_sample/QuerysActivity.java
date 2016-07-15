package com.example.lz.android_greendao_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lz.android_greendao_sample.customtype.MyTimestamp;
import com.example.lz.android_greendao_sample.dao.CityDao;
import com.example.lz.android_greendao_sample.dao.CustomTypeEntityDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

public class QuerysActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querys);
    }

    public void onQueryBuilderClick(View view) {
        CityDao cityDao = getDaoSession().getCityDao();
//        List<City> list = cityDao.queryBuilder()
//                .where(CityDao.Properties.PopulationQuantity.gt(1000 * 1000 * 10))
//                .orderAsc(CityDao.Properties.Name)
//                .list();

        QueryBuilder<City> cityQueryBuilder = cityDao.queryBuilder();
        cityQueryBuilder.where(CityDao.Properties.IsTerritory.eq(true),
                cityQueryBuilder.or(CityDao.Properties.IsCapital.eq(true), CityDao.Properties.PopulationQuantity.gt(1000 * 1000 * 10)));
        List<City> cities = cityQueryBuilder.list();

        for (City city : cities) {
            Log.e("City", "ID: " + city.getId()
                    + " NAME: " + city.getName()
                    + " ISTERRITORY: " + city.getIsTerritory()
                    + " ISCAPITAL: " + city.getIsCapital()
                    + " POPULATIONQUANTITY: " + city.getPopulationQuantity()
                    + " provinceID: " + city.getProvinceID()
            );
        }
    }

    /**
     * 插入自定义类型
     *
     * @param view
     */
    public void OnCustom_Types_as_ParametersClick(View view) {
        CustomTypeEntityDao customTypeEntityDao = getDaoSession().getCustomTypeEntityDao();
        CustomTypeEntity customTypeEntity = new CustomTypeEntity();
        MyTimestamp myTimestamp = new MyTimestamp();
        myTimestamp.timestamp = new Date().getTime();
        customTypeEntity.setMyCustomTimestamp(myTimestamp);
        customTypeEntityDao.insert(customTypeEntity);
        Log.e("CustomTypesAsParameters", "entity Timestamp: " + customTypeEntity.getMyCustomTimestamp());
    }

    public void OnQuery_and_LazyListClick(View view) {

    }

    public void OnExecuting_Queries_multiple_timesClick(View view) {

    }

    public void OnExecuting_queries_in_multiple_threadsClick(View view) {

    }

    public void OnRaw_queriesClick(View view) {

    }

    public void OnDelete_queriesClick(View view) {

    }

    public void OnTroubleshooting_queriesClick(View view) {

    }


}
