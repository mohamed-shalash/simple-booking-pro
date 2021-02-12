package com.example.trainbooking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class database_train extends SQLiteAssetHelper {

    public static final String db_name="train.db";
    private static final int version=1;
    public static String table="train";
    public static String col1="num";
    public static String col2="hour";
    public static String col3="pos";
    public static String col4="image";


    public database_train(Context context) {
        super(context, db_name, null, version);
    }
}
