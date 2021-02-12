package com.example.trainbooking;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class train_db_helper {
    private static SQLiteDatabase sqLiteDatabase;
    private static SQLiteOpenHelper sqLiteOpenHelper;
    private static train_db_helper train_db_helper;

    private train_db_helper(Context context){sqLiteOpenHelper=new database_train(context);}
    public static train_db_helper set_pointer(Context context){
        if (train_db_helper == null)
            train_db_helper=new train_db_helper(context);
        return train_db_helper;
    }
    public void open(){this.sqLiteDatabase=sqLiteOpenHelper.getWritableDatabase();}
    public void close(){if (this.sqLiteDatabase !=null){this.sqLiteDatabase.close();}}

    public ArrayList<train> show_all_trains(){
        ArrayList<train> trains=new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("SELECT * FROM "+database_train.table,null);

        if (c.moveToFirst()) {
            do {
                train tr =new train(c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
                trains.add(tr);
            }while (c.moveToNext());
        }
        return trains;
    }

    public ArrayList<train> show_some_trains(String str){
        ArrayList<train> trains=new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("SELECT * FROM "+database_train.table+" where "+database_train.col3+" = ?",new String[]{str});
        if (c.moveToFirst()) {
            do {
                train tr =new train(c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
                trains.add(tr);
            }while (c.moveToNext());
        }
        return trains;
    }
    public train show_some_by_id(int id){
        train trains = null;
        Cursor c =sqLiteDatabase.rawQuery("SELECT * FROM "+database_train.table+" where "+database_train.col1+" = "+id,null);

        if (c.moveToFirst()) {
            do {
                trains =new train(c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
            }while (c.moveToNext());
        }
        return trains;
    }

    public boolean add_train(train traine){
        ContentValues cv=new ContentValues();
        cv.put(database_train.col2,traine.getHour());
        cv.put(database_train.col3,traine.getDistin());
        cv.put(database_train.col4,traine.getImage());
        long x=sqLiteDatabase.insert(database_train.table,null,cv);
        return x>-1;
    }
    public boolean delete_train(int num){
        int x=sqLiteDatabase.delete(database_train.table,database_train.col1+" = "+num,null);
        return x>-1;
    }
    public boolean update_train(train traine){
        ContentValues cv=new ContentValues();
        cv.put(database_train.col1,traine.getNum());
        cv.put(database_train.col2,traine.getHour());
        cv.put(database_train.col3,traine.getDistin());
        cv.put(database_train.col4,traine.getImage());
        int x=sqLiteDatabase.update(database_train.table,cv,database_train.col1+" = "+traine.getNum(),null);
        return x>-1;
    }
    public int show_id(String str){
        Cursor c =sqLiteDatabase.rawQuery("SELECT * FROM "+database_train.table+" where "+database_train.col2+" = ?",new String[]{str});
        if (c.moveToFirst()) {
            do {
                train tr =new train(c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
               return tr.getNum();
            }while (c.moveToNext());
        }
        return -1;
    }
}

