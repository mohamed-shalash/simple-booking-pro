package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class person_act extends AppCompatActivity {
TextInputEditText num,name,address;
Spinner seats,trains;
person_db_helper db;
train_db_helper tdb;
seat_database_helper sdb;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_act);

        num =findViewById(R.id.per_num);
        name=findViewById(R.id.per_name);
        address=findViewById(R.id.per_address);
        seats=findViewById(R.id.seat_sp);
        trains=findViewById(R.id.train_sp);

        Intent intent=getIntent();
        x= intent.getIntExtra(person_main_act.car_key,-1);

        db=person_db_helper.person_getpointers(this);
        tdb=train_db_helper.set_pointer(this);
        sdb=seat_database_helper.getpointer(this);
        db.open();
        List<String> lst=db.show_all_seats();
        ArrayAdapter<String> arrad=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,lst);
        arrad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        db.close();
        seats.setAdapter(arrad);

        db.open();
        List<String> lstt=db.show_all_trains();
        ArrayAdapter<String> arradd=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,lstt);
        arradd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        db.close();
        trains.setAdapter(arradd);

        if(x==-1){
            isenabled();
            num.setEnabled(true);
        }else{
            isdisabled();
            db.open();
           remaking_per pp=db.show_some_by_id(x);
            db.close();
            num.setText(x+"");
            name.setText(pp.getName());
            address.setText(pp.getAddress());

            int sid =lst.indexOf(pp.getSeat());
            seats.setSelection(sid);

            int tid =lstt.indexOf(pp.getHour());
            trains.setSelection(tid);

        }
    }
    public void isenabled(){
        num.setEnabled(false);
        name.setEnabled(true);
        address.setEnabled(true);
        seats.setEnabled(true);
        trains.setEnabled(true);
    }
    public void isdisabled(){
        num.setEnabled(false);
        name.setEnabled(false);
        address.setEnabled(false);
        seats.setEnabled(false);
        trains.setEnabled(false);
    }
Menu men;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.person_act_menu,menu);
        men=menu;
        MenuItem save =men.findItem(R.id.save_3);
        MenuItem edit =men.findItem(R.id.edit_3);
        MenuItem delete =men.findItem(R.id.delete_3);
        if (x== -1){
            save.setVisible(true);
            edit.setVisible(false);
            delete.setVisible(false);
        }
        else{
            save.setVisible(false);
            edit.setVisible(true);
            delete.setVisible(true);
        }
        return true;
    }
    boolean adding=true;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MenuItem save =men.findItem(R.id.save_3);
        MenuItem edit =men.findItem(R.id.edit_3);
        MenuItem delete =men.findItem(R.id.delete_3);
        switch (item.getItemId()){
            case R.id.save_3:
                ////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////
                String str_seat=seats.getSelectedItem().toString();
                String str_train=trains.getSelectedItem().toString();
                tdb.open();
                int tid =tdb.show_id(str_train);
                tdb.close();
                Toast.makeText(getBaseContext(),tid+"",Toast.LENGTH_SHORT).show();
                ////////////////////////////////////
                sdb.open();
                int sid =sdb.show_id_seat_by_position(str_seat);
                sdb.close();
                Toast.makeText(getBaseContext(),sid+"",Toast.LENGTH_SHORT).show();
                ////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////
                if (adding){
                    person per =new person(Integer.parseInt(num.getText().toString()),name.getText().toString(),sid,
                            tid,address.getText().toString());
                    db.open();
                        boolean y=db.insert(per);
                    if (y)Toast.makeText(getBaseContext(),"Added",Toast.LENGTH_SHORT).show();
                    db.close();
                    finish();
                }
                else{
                    person perr =new person(Integer.parseInt(num.getText().toString()),name.getText().toString(),sid,
                            tid,address.getText().toString());
                    db.open();
                    boolean y=db.update(perr);
                    if (y)Toast.makeText(getBaseContext(),"Edit",Toast.LENGTH_SHORT).show();
                    db.close();
                    finish();
                }
                return true;
            case R.id.edit_3:
                save.setVisible(true);
                isenabled();
                edit.setVisible(false);
                delete.setVisible(false);
                adding=false;
                return true;
            case R.id.delete_3:
                db.open();
                db.delete(x);
                db.close();
                finish();
                return true;
        }
        return false;
    }
}
