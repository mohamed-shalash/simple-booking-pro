package com.example.trainbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class person_db_helper {
    static SQLiteOpenHelper sqLiteOpenHelper;
    static SQLiteDatabase sqLiteDatabase;
    static person_db_helper db_helper;

    private person_db_helper(Context con){
        sqLiteOpenHelper=new person_database(con);
    }

     public static person_db_helper person_getpointers(Context con){
         if (db_helper == null)
             db_helper=new person_db_helper(con);
         return db_helper;
    }

    public  static void open(){
        sqLiteDatabase =sqLiteOpenHelper.getWritableDatabase();
    }
    public static void close(){if (sqLiteDatabase !=null) sqLiteDatabase.close();}


    public static ArrayList<remaking_per> show_all(){
        //SELECT per.num,per.name,per.address,seat.pos,train.hour from per,seat,train where per.seat_num =seat.num and per.train_num=train.num
        ArrayList<remaking_per> per=new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("SELECT per.num,per.name,seat.pos,train.hour,per.address from "+person_database.table+","+database_train.table+","+seat_database.table+" where  per.seat_num =seat.num and per.train_num=train.num",null);
        if (c.moveToFirst()) {
            do {
                per.add(new remaking_per(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }while (c.moveToNext());
        }
        return per;
    }
    public static ArrayList<remaking_per> show_some_by_name(String name){
        //SELECT per.num,per.name,per.address,seat.pos,train.hour from per,seat,train where per.seat_num =seat.num and per.train_num=train.num
        ArrayList<remaking_per> per=new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("SELECT per.num,per.name,seat.pos,train.hour,per.address from "+person_database.table+","+database_train.table+","+seat_database.table+" where  per.seat_num =seat.num and per.train_num=train.num and "+person_database.col2+"  LIKE '%"+name+"%'",null);
        if (c.moveToFirst()) {
            do {
                per.add(new remaking_per(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }while (c.moveToNext());
        }
        return per;
    }
    public remaking_per show_some_by_id(int id){
        //SELECT per.num,per.name,per.address,seat.pos,train.hour from per,seat,train where per.seat_num =seat.num and per.train_num=train.num
       remaking_per per=null;
        Cursor c =sqLiteDatabase.rawQuery("SELECT per.num,per.name,seat.pos,train.hour,per.address from "+person_database.table+","+database_train.table+","+seat_database.table+" where  per.seat_num =seat.num and per.train_num=train.num and per.num = "+id,null);
        if (c.moveToFirst()) {
            do {
                per=new remaking_per(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                return per;
            }while (c.moveToNext());
        }
        return per;
    }

    public boolean insert(person p){
        ContentValues cv =new ContentValues();
        //cv.put(person_database.col1,p.getNum());
        cv.put(person_database.col2,p.getName());
        cv.put(person_database.col3,p.getSeat_num());
        cv.put(person_database.col4,p.getTrain_num());
        cv.put(person_database.col5,p.getAddress());
        Long x=sqLiteDatabase.insert(person_database.table,null,cv);
        return x>-1;
    }
    public boolean update(person p){
        ContentValues cv =new ContentValues();
        cv.put(person_database.col2,p.getName());
        cv.put(person_database.col3,p.getSeat_num());
        cv.put(person_database.col4,p.getTrain_num());
        cv.put(person_database.col5,p.getAddress());
        int x=sqLiteDatabase.update(person_database.table,cv,"num = "+p.getNum(),null);
        return x>-1;
    }
    public boolean delete(int id){
        int x=sqLiteDatabase.delete(person_database.table,"num ="+id,null);
        return x>-1;
    }

    public static ArrayList<String> show_all_seats(){
        //SELECT seat.pos from per,seat,train where per.num =seat.num and per.train_num=train.num
        ArrayList<String> per=new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("SELECT seat.pos from "+seat_database.table,null);
        if (c.moveToFirst()) {
            do {
                per.add(c.getString(0));
            }while (c.moveToNext());
        }
        return per;
    }
    public static ArrayList<String> show_all_trains(){
        ArrayList<String> per=new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("SELECT train.hour from "+database_train.table+" ",null);
        if (c.moveToFirst()) {
            do {
                per.add(c.getString(0));
            }while (c.moveToNext());
        }
        return per;
    }

}
