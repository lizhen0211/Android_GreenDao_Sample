package com.example.lz.android_greendao_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lz.android_greendao_sample.dao.CustomerEntityDao;
import com.example.lz.android_greendao_sample.dao.DaoSession;
import com.example.lz.android_greendao_sample.dao.OrderEntityDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Random;

public class RelationsActivity extends BaseActivity {

    private OrderEntityDao orderEntityDao;

    private CustomerEntityDao customerEntityDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relations);
        DaoSession daoSession = getDaoSession();
        orderEntityDao = daoSession.getOrderEntityDao();
        customerEntityDao = daoSession.getCustomerEntityDao();
    }

    public void insertData(View view) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerID(1L);
        customerEntity.setCustomerName("张三");
        for (int i = 0; i < 3; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setAmount(new Random(10).nextFloat());
            orderEntity.setCustomerID(customerEntity.getCustomerID());
            orderEntityDao.insertOrReplace(orderEntity);
        }

        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setCustomerID(2L);
        customerEntity1.setCustomerName("李四");

        for (int i = 3; i < 7; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setAmount(new Random(10).nextFloat());
            orderEntity.setCustomerID(customerEntity1.getCustomerID());
            orderEntityDao.insertOrReplace(orderEntity);
        }

        CustomerEntity customerEntity2 = new CustomerEntity();
        customerEntity2.setCustomerID(3L);
        customerEntity2.setCustomerName("王五");

        for (int i = 7; i < 10; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setAmount(new Random(10).nextFloat());
            orderEntity.setCustomerID(customerEntity2.getCustomerID());
            orderEntityDao.insertOrReplace(orderEntity);
        }

        customerEntityDao.insertOrReplace(customerEntity);
        customerEntityDao.insertOrReplace(customerEntity1);
        customerEntityDao.insertOrReplace(customerEntity2);
    }

    public void queryOneToOne(View view) {

    }

    public void queryOneToMany(View view) {
        QueryBuilder<OrderEntity> orderQueryBuilder = orderEntityDao.queryBuilder();
        //orderQueryBuilder.where(OrderEntityDao.Properties.Amount.gt(10.0));
        List<OrderEntity> orderEntities = orderQueryBuilder.build().list();
        for (OrderEntity order : orderEntities) {
            Float amount = order.getAmount();
            Long orderID = order.getOrderID();
            Long customerID = order.getCustomerID();
            CustomerEntity customerEntity = order.getCustomerEntity();
            Log.v("order info", "orderID:" + orderID + " " + "amount:" + amount + " " + "customerID:" + customerID + " " + "customerEntity:" + customerEntity + "\\n");
        }

        QueryBuilder<CustomerEntity> customerEntityQueryBuilder = customerEntityDao.queryBuilder();
        List<CustomerEntity> customerEntities = customerEntityQueryBuilder.build().list();
        for (CustomerEntity customerEntity : customerEntities) {
            Long customerID = customerEntity.getCustomerID();
            String customerName = customerEntity.getCustomerName();
            List<OrderEntity> orders = customerEntity.getOrders();
            Log.v("customer info", "customerID:" + customerID + " " + "customerName:" + customerName + " " + "orders:" + orders + "\\n");
        }
    }

    public void queryManyToMany(View view) {

    }
}
