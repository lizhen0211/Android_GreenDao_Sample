package com.example.lz.android_greendao_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lz.android_greendao_sample.dao.SimpleEntityDao;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class SimpleEntityActivity extends BaseActivity {

    private SimpleEntityDao simpleEntityDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_entity);
        simpleEntityDao = getDaoSession().getSimpleEntityDao();
    }

    /**
     * 插入简单实体
     *
     * @param simpleEntityDao
     */
    private void insertSimple(SimpleEntityDao simpleEntityDao) {
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setId(new Random().nextLong());
        simpleEntity.setSimpleBoolean(true);
        simpleEntity.setSimpleByte("b".getBytes()[0]);
        simpleEntity.setSimpleByteArray("bb".getBytes());
        simpleEntity.setSimpleDouble(1d);
        simpleEntity.setSimpleFloat(1f);
        simpleEntity.setSimpleInt(1);
        simpleEntity.setSimpleLong(1l);
        simpleEntity.setSimpleShort((short) 1);
        simpleEntity.setSimpleString("string");
        simpleEntityDao.insert(simpleEntity);
        Log.e("insertSimple", "entity, ID: " + simpleEntity.getId());
    }

    private void updateBySimple(SimpleEntity simpleEntity) {
        simpleEntity.setSimpleString("I've been updated" + new Date().toString());
        simpleEntityDao.update(simpleEntity);
        //simpleEntityDao.insertOrReplace();
        Log.e("updateBySimple", "entity ID: " + simpleEntity.getId());
    }

    private void deleteByKeySimple(long key) {
        simpleEntityDao.deleteByKey(key);
//        simpleEntityDao.deleteAll();
        Log.e("deleteByKeySimple", "entity ID: " + key);
    }

    private void queryEntities() {
        List<SimpleEntity> simpleEntities = simpleEntityDao.queryBuilder().where(SimpleEntityDao.Properties.Id.
                ge(0)).orderAsc(SimpleEntityDao.Properties.Id).list();
        for (SimpleEntity simpleEntity : simpleEntities) {
            Log.e("queryEntities", "query entity, ID: " + simpleEntity.getId() + "  SimpleString: " + simpleEntity.getSimpleString());
        }
    }

    public void onInsertClick(View view) {
        insertSimple(getDaoSession().getSimpleEntityDao());
    }

    public void onUpdateClick(View view) {
        List<SimpleEntity> list = simpleEntityDao.queryBuilder().
                orderAsc(SimpleEntityDao.Properties.Id).list();
        if (list.size() > 0) {
            //此处未了演示，批处理方法还需调研。
            for (SimpleEntity simpleEntity : list) {
                updateBySimple(simpleEntity);
            }
        }
    }

    public void onDeleteClick(View view) {
        List<SimpleEntity> list = simpleEntityDao.queryBuilder().
                orderAsc(SimpleEntityDao.Properties.Id).list();
        if (list.size() > 0) {
            deleteByKeySimple(list.get(0).getId());
        }
    }

    public void onQueryClick(View view) {
        queryEntities();
    }
}
