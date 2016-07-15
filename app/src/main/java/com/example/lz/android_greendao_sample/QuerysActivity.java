package com.example.lz.android_greendao_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lz.android_greendao_sample.customtype.MyTimestamp;
import com.example.lz.android_greendao_sample.dao.CityDao;
import com.example.lz.android_greendao_sample.dao.CustomTypeEntityDao;
import com.example.lz.android_greendao_sample.dao.ProvinceDao;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.CloseableListIterator;
import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

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

        //SELECT * FROM CITY T  WHERE T.IS_TERRITORY=1 AND (T.IS_CAPITAL=1 OR T.POPULATION_QUANTITY>5000000);
//        QueryBuilder<City> cityQueryBuilder = cityDao.queryBuilder();
//        cityQueryBuilder.where(CityDao.Properties.IsTerritory.eq(true),
//                cityQueryBuilder.or(CityDao.Properties.IsCapital.eq(true), CityDao.Properties.PopulationQuantity.gt(5000000)));
//        List<City> cities = cityQueryBuilder.list();

        //SELECT T."_id",T."NAME",T."IS_TERRITORY",T."IS_CAPITAL",T."POPULATION_QUANTITY",T."PROVINCE_ID" FROM "CITY" T  WHERE ((T."POPULATION_QUANTITY">? AND T."IS_CAPITAL"=?) OR T."IS_TERRITORY"=?)
        QueryBuilder<City> cityQueryBuilder = cityDao.queryBuilder();
        cityQueryBuilder.where(cityQueryBuilder.or(cityQueryBuilder.and(CityDao.Properties.PopulationQuantity.gt(5000000),
                CityDao.Properties.IsCapital.eq(true)), CityDao.Properties.IsTerritory.eq(true)));

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

        //list() All entities are loaded into memory.
        //The result is typically an ArrayList with no magic involved.
        //Easiest to use.

        /*********************list() begin*********************/
        List<City> citiesList = getDaoSession().getCityDao().queryBuilder().list();
        Log.e("citiesList", citiesList.toString());
        /*********************list() end*********************/

        //listLazy() Entities are loaded into memory on-demand.
        // Once an element in the list is accessed for the first time,
        // it is loaded and cached for future use. Must be closed
        /*********************listLazy() begin*********************/
        Query<City> buildLazy = getDaoSession().getCityDao().queryBuilder().build();
        LazyList<City> cityListLazy = buildLazy.listLazy();
        Log.e("cityListLazy", cityListLazy.toString());

        // Closing again should not harm
        cityListLazy.close();
        cityListLazy.close();

        /*********************listLazy() end*********************/


        //listLazyUncached() A “virtual” list of entities:
        // any access to a list element results in loading its data from the database.
        // Must be closed
        Query<City> buildLazyUncached = getDaoSession().getCityDao().queryBuilder().build();
        LazyList<City> cityListLazyUncached = buildLazyUncached.listLazyUncached();
        CloseableListIterator<City> iterator = cityListLazyUncached.listIteratorAutoClose();
        while (iterator.hasNext()) {
            Log.e("cityListLazyUncached", String.valueOf(cityListLazyUncached.isClosed()));
            iterator.next();
        }
        Log.e("cityListLazyUncached", String.valueOf(cityListLazyUncached.isClosed()));


        //listIterator() Let’s you iterate through results by loading the data on-demand (lazily).
        // Data is not cached. Must be closed.
        Query<City> buildlistIterator = getDaoSession().getCityDao().queryBuilder().build();
        CloseableListIterator<City> cityCloseableListIterator = buildlistIterator.listIterator();
        while (cityCloseableListIterator.hasNext()) {
            City next = iterator.next();
            Log.e("citiesListIterator", next.toString());
        }
    }

    public void OnExecuting_Queries_multiple_timesClick(View view) {
        ProvinceDao provinceDao = getDaoSession().getProvinceDao();
        QueryBuilder<Province> provinceQueryBuilder = provinceDao.queryBuilder();
        provinceQueryBuilder.where(ProvinceDao.Properties.Name.like("?"));
        Query<Province> query = provinceQueryBuilder.build();
        query.setParameter(0, "%辽%");
        List<Province> provinces1 = query.list();
        for (Province province : provinces1) {
            Log.e("province", " name: " + province.getName() + " alias: " + province.getAlias() + " populationQuantity :" + province.getPopulationQuantity());
        }
        query.setParameter(0, "%浙%");
        List<Province> provinces2 = query.list();
        for (Province province : provinces2) {
            Log.e("province", " name: " + province.getName() + " alias: " + province.getAlias() + " populationQuantity :" + province.getPopulationQuantity());
        }
    }

    private Query<City> queryFromOtherThread;

    public void OnExecuting_queries_in_multiple_threadsClick(View view) {
        createQueryFromOtherThread();

        //List<City> list1 = queryFromOtherThread.list();

        Query<City> cityQuery = queryFromOtherThread.forCurrentThread();
        List<City> list = cityQuery.list();
        for (City city : list) {
            Log.e("list", city.getName());
        }
    }

    private void createQueryFromOtherThread() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                CityDao cityDao = getDaoSession().getCityDao();
                QueryBuilder<City> cityQueryBuilder = cityDao.queryBuilder();
                cityQueryBuilder.limit(2).offset(1);
                queryFromOtherThread = cityQueryBuilder.build();
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void OnRaw_queriesClick(View view) {
        CityDao cityDao = getDaoSession().getCityDao();
        Query<City> query = cityDao.queryBuilder().where(
                new WhereCondition.StringCondition(CityDao.Properties.ProvinceID.columnName + " IN " +
                        "(SELECT " + ProvinceDao.Properties.Id.columnName + " FROM PROVINCE)")).build();
        List<City> list = query.list();
        for (City city : list) {
            Log.e("list", city.getName());
        }
    }

    public void OnDelete_queriesClick(View view) {

    }

    public void OnTroubleshooting_queriesClick(View view) {
        // It has prevents in the BaseApplication
        //QueryBuilder.LOG_SQL = true;
        //QueryBuilder.LOG_VALUES = true;
    }


}
