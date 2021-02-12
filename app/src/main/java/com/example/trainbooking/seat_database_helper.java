package com.example.trainbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class seat_database_helper {
    static seat_database_helper sdbh;
    private static SQLiteDatabase sqLiteDatabase;
    private static SQLiteOpenHelper sqLiteOpenHelper;

    private seat_database_helper(Context context){
        sqLiteOpenHelper =new seat_database(context);
    }

    public static seat_database_helper getpointer(Context context){
        if (sdbh==null)
            sdbh=new seat_database_helper(context);
        return sdbh;
    }

    public void open(){
        this.sqLiteDatabase =sqLiteOpenHelper.getWritableDatabase();
    }
    public void close(){if (sqLiteDatabase !=null) sqLiteDatabase.close();}

    public ArrayList<seat_> show_all_seats(){
        ArrayList<seat_> seate=new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("SELECT * FROM "+seat_database.table,null);

        if (c.moveToFirst()) {
            do {
                seat_ tr =new seat_(c.getInt(0),c.getFloat(1),c.getString(2),c.getString(3));
                seate.add(tr);
            }while (c.moveToNext());
        }
        return seate;
    }
    public ArrayList<seat_> show_some_seats_by_position(String pos){
        ArrayList<seat_> seate=new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("SELECT * FROM "+seat_database.table+" where "+seat_database.col3+"  LIKE '%"+pos+"%'",null);
        if (c.moveToFirst()) {
            do {
                seat_ tr =new seat_(c.getInt(0),c.getFloat(1),c.getString(2),c.getString(3));
                seate.add(tr);
            }while (c.moveToNext());
        }
        return seate;
    }
    public seat_ show_some_seats_by_num(int id){
       seat_ seate=null;
        Cursor c =sqLiteDatabase.rawQuery("SELECT * FROM "+seat_database.table+" where "+seat_database.col1+" = "+id,null);
        if (c.moveToFirst()) {
            do {
                seate =new seat_(c.getInt(0),c.getFloat(1),c.getString(2),c.getString(3));
            }while (c.moveToNext());
        }
        return seate;
    }

    public  boolean add_seat(seat_ seat){
        ContentValues cv =new ContentValues();
        cv.put(seat_database.col1,seat.getNum());
        cv.put(seat_database.col2,seat.getCos());
        cv.put(seat_database.col3,seat.getPos());
        cv.put(seat_database.col4,seat.getIm());
        long x=sqLiteDatabase.insert(seat_database.table,null,cv);
        return x>-1;
    }
    public  boolean update_seat(seat_ seat){
        ContentValues cv =new ContentValues();
        cv.put(seat_database.col2,seat.getCos());
        cv.put(seat_database.col3,seat.getPos());
        cv.put(seat_database.col4,seat.getIm());
        int x=sqLiteDatabase.update(seat_database.table,cv,seat_database.col1+"="+seat.getNum(),null);
        return x>-1;
    }
    public  boolean delete_seat(int id){
        int x=sqLiteDatabase.delete(seat_database.table,seat_database.col1+" = "+id,null);
        return x>-1;
    }
    public int show_id_seat_by_position(String pos){
        Cursor c =sqLiteDatabase.rawQuery("SELECT * FROM "+seat_database.table+" where "+seat_database.col3+"  LIKE '%"+pos+"%'",null);
        if (c.moveToFirst()) {
            do {
                seat_ tr =new seat_(c.getInt(0),c.getFloat(1),c.getString(2),c.getString(3));
                return  tr.getNum();
            }while (c.moveToNext());
        }
        return -1;
    }

}
