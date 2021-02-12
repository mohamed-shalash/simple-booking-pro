package com.example.trainbooking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class person_main_act extends AppCompatActivity {
    RecyclerView rc;
    person_adabter adabter;
    person_db_helper db;
    FloatingActionButton fab;
    public static final String car_key ="car_key";
    public static int add_new_card=14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_main_act);
        fab=findViewById(R.id.person_main_fab);
        Toolbar toolbar =findViewById(R.id.person_main_tool);
        setSupportActionBar(toolbar);
        rc=findViewById(R.id.recycler_view_person_act);

        ArrayList<remaking_per>p;

        db.person_getpointers(this);
        db.open();
        p =db.show_all();
        db.close();

        adabter=new person_adabter(p, new interfaceclick() {
            @Override
            public void onclickthat(int id) {
                Intent i=new Intent(getBaseContext(),person_act.class);
                i.putExtra(car_key,id);
                startActivityForResult(i,add_new_card);
            }
        });
        rc.setAdapter(adabter);
        rc.setHasFixedSize(true);
        RecyclerView.LayoutManager lm =new GridLayoutManager(this,1);
        rc.setLayoutManager(lm);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),person_act.class);
                startActivityForResult(i,add_new_card);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.per_main_menu, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.serching_person_item).getActionView();
        sv.setSubmitButtonEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                db.open();
                ArrayList<remaking_per> pp =db.show_some_by_name(newText);
                adabter.setPer(pp);
                adabter.notifyDataSetChanged();
                db.close();
                return false;
            }
        });

        sv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                db.open();
                ArrayList<remaking_per> pp =db.show_all();
                adabter.setPer(pp);
                adabter.notifyDataSetChanged();
                db.close();
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        db.open();
        ArrayList<remaking_per> pp =db.show_all();
        adabter.setPer(pp);
        adabter.notifyDataSetChanged();
        db.close();

    }
}
