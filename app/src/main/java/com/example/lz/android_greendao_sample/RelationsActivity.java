package com.example.lz.android_greendao_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lz.android_greendao_sample.dao.CourseDao;
import com.example.lz.android_greendao_sample.dao.CustomerEntityDao;
import com.example.lz.android_greendao_sample.dao.DaoSession;
import com.example.lz.android_greendao_sample.dao.OrderEntityDao;
import com.example.lz.android_greendao_sample.dao.PersonDao;
import com.example.lz.android_greendao_sample.dao.PersonInfoDao;
import com.example.lz.android_greendao_sample.dao.StudentCourseRelationDao;
import com.example.lz.android_greendao_sample.dao.StudentDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Random;

public class RelationsActivity extends BaseActivity {

    private OrderEntityDao orderEntityDao;

    private CustomerEntityDao customerEntityDao;

    private PersonDao personDao;

    private PersonInfoDao personInfoDao;

    private CourseDao courseDao;

    private StudentDao studentDao;

    private StudentCourseRelationDao studentCourseRelationDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relations);
        DaoSession daoSession = getDaoSession();
        orderEntityDao = daoSession.getOrderEntityDao();
        customerEntityDao = daoSession.getCustomerEntityDao();
        personDao = daoSession.getPersonDao();
        personInfoDao = daoSession.getPersonInfoDao();
        courseDao = daoSession.getCourseDao();
        studentDao = daoSession.getStudentDao();
        studentCourseRelationDao = daoSession.getStudentCourseRelationDao();
    }

    public void insertData(View view) {
        //一对多关系
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

        //一对一关系
        PersonInfo personInfo = new PersonInfo();
        personInfo.setAddress("沈阳");
        personInfo.setMajor("程序员");
        personInfo.setInfoID(1L);

        Person person = new Person();
        person.setName("张三");
        person.setSex(1);
        person.setPersonID(1L);

        person.setPersonInfo(personInfo);
        personInfo.setPerson(person);
        personInfoDao.insertOrReplace(personInfo);
        personDao.insertOrReplace(person);

        //多对多关系
        Course course = new Course();
        course.setCourseId(1L);
        course.setCourseName("数据结构");

        Student student = new Student();
        student.setStudentId(1000L);
        student.setName("李四");

        Course course1 = new Course();
        course1.setCourseId(2L);
        course1.setCourseName("操作系统");

        Student student1 = new Student();
        student1.setStudentId(2000L);
        student1.setName("王五");

        StudentCourseRelation relation = new StudentCourseRelation();
        relation.setStudent(student);
        relation.setCourse(course);

        StudentCourseRelation relation1 = new StudentCourseRelation();
        relation1.setStudent(student1);
        relation1.setCourse(course);

        StudentCourseRelation relation2 = new StudentCourseRelation();
        relation2.setStudent(student);
        relation2.setCourse(course1);

        StudentCourseRelation relation3 = new StudentCourseRelation();
        relation3.setStudent(student1);
        relation3.setCourse(course);

        studentDao.insertOrReplace(student);
        studentDao.insertOrReplace(student1);
        courseDao.insertOrReplace(course);
        courseDao.insertOrReplace(course1);

        studentCourseRelationDao.insertOrReplace(relation);
        studentCourseRelationDao.insertOrReplace(relation1);
        studentCourseRelationDao.insertOrReplace(relation2);
        studentCourseRelationDao.insertOrReplace(relation3);
    }

    public void queryOneToOne(View view) {
        List<Person> list = personDao.queryBuilder().where(PersonDao.Properties.Name.eq("张三")).list();
        for (Person person : list) {
            PersonInfo personInfo = person.getPersonInfo();
            Log.v("person", "name:" + person.getName() + " " + "sex:" + person.getSex() + " " + "major:" + personInfo.getMajor() + " " + "address:" + personInfo.getAddress());
        }

        List<PersonInfo> personInfos = personInfoDao.queryBuilder().where(PersonInfoDao.Properties.Address.eq("沈阳")).list();
        for (PersonInfo info : personInfos) {
            Log.v("personInfo", "address:" + info.getAddress() + " " + "name:" + info.getPerson().getName());
        }

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
        List<StudentCourseRelation> list = studentCourseRelationDao.queryBuilder().list();
        for (StudentCourseRelation relation : list) {
            Course course = relation.getCourse();
            Student student = relation.getStudent();
            Log.v("course", course.getCourseName());
            Log.v("student", student.getName());
        }
    }
}
