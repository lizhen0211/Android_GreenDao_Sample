package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DataBaseDaoGenerator {

    private final Schema schema;

    public DataBaseDaoGenerator() {
        //Schema Entities belong to a schema. A schema is the first object you define. Call the constructor with the schema version and the default Java package:
        schema = new Schema(1, "com.example.lz.android_greendao_sample");
        //The default Java package is used when greenDAO generates entities, DAOs, and JUnit tests. If those defaults are fine for your project, you are done with the first step.
        //If you want the DAO and test classes to go into separate packages, can refine your schema like this:
        schema.setDefaultJavaPackageDao("com.example.lz.android_greendao_sample.dao");
        schema.setDefaultJavaPackageTest("com.example.lz.android_greendao_sample.entity");

        //The schema also has two default flags for entities, which can be overridden. The flags tell if entities are active, and if keep sections should be used. Those features are not yet documented; have a look at the test project in the source code distribution.
        //schema.enableKeepSectionsByDefault();
        //schema.enableActiveEntitiesByDefault();
        createSimple();
    }

    public void generate() throws Exception {
        DaoGenerator daoGenerator = new DaoGenerator();
        daoGenerator.generateAll(schema, "app/src-gen", "app/src-gen", "app/src/androidTest/java");
    }

    /**
     * 创建简单实体类
     */
    protected void createSimple() {
        Entity simple = schema.addEntity("SimpleEntity");
        simple.addIdProperty().autoincrement();
        simple.addBooleanProperty("simpleBoolean");
        simple.addByteProperty("simpleByte");
        simple.addShortProperty("simpleShort");
        simple.addIntProperty("simpleInt");
        simple.addLongProperty("simpleLong");
        simple.addFloatProperty("simpleFloat");
        simple.addDoubleProperty("simpleDouble");
        simple.addStringProperty("simpleString");
        simple.addByteArrayProperty("simpleByteArray");

        simple.addContentProvider().readOnly();
    }

    public static void main(String[] args) throws Exception {
        DataBaseDaoGenerator daoGenerator = new DataBaseDaoGenerator();
        daoGenerator.generate();
    }
}
