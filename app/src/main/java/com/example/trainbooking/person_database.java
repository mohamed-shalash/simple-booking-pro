package com.example.trainbooking;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class person_database extends SQLiteAssetHelper {

    public static final String db_name="train.db";
    private static final int version=1;
    public static String table="per";
    public static String col1="num";
    public static String col2="name";
    public static String col3="seat_num";
    public static String col4="train_num";
    public static String col5="address";

    public person_database(Context context) {
        super(context, db_name, null, version);
    }
}
