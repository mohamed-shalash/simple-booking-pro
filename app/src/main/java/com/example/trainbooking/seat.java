package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class seat extends AppCompatActivity {
FloatingActionButton fab;
RecyclerView rc;
seat_adabter adabter;
seat_database_helper db;
    public static final String car_key ="car_key";
    public static int add_new_card=14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);
        Toolbar toolbar =findViewById(R.id.seat_toolbar);
        setSupportActionBar(toolbar);
        fab=findViewById(R.id.seat_main_fab);
        rc=findViewById(R.id.recycler_view_seat_act);

        db=seat_database_helper.getpointer(this);
        db.open();
        ArrayList<seat_> tr=db.show_all_seats();
        db.open();

        adabter =new seat_adabter(tr, new interfaceclick() {
            @Override
            public void onclickthat(int id) {
                Intent i =new Intent(getBaseContext(),seat_editer.class);
                i.putExtra(car_key,id);
                startActivityForResult(i,add_new_card);
            }
        });
        rc.setAdapter(adabter);
        RecyclerView.LayoutManager lm =new GridLayoutManager(this,2);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(lm);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),seat_editer.class);
                startActivityForResult(i,add_new_card);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.seat_main_menu,menu);
        SearchView sv= (SearchView) menu.findItem(R.id.serching_seat_item).getActionView();
        sv.setSubmitButtonEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                db.open();
                ArrayList<seat_> tr=db.show_some_seats_by_position(query);
                db.open();
                adabter.setseatArrayList(tr);
                adabter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                db.open();
                ArrayList<seat_> tr=db.show_some_seats_by_position(newText);
                db.open();
                adabter.setseatArrayList(tr);
                adabter.notifyDataSetChanged();
                return false;
            }
        });
        sv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                db.open();
                ArrayList<seat_> tr=db.show_all_seats();
                db.open();
                adabter.setseatArrayList(tr);
                adabter.notifyDataSetChanged();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==add_new_card){
            db.open();
            ArrayList<seat_> tr=db.show_all_seats();
            db.open();
            adabter.setseatArrayList(tr);
            adabter.notifyDataSetChanged();
        }
    }
}
