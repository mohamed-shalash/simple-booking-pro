package com.example.trainbooking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;

public class train_main_act extends AppCompatActivity {
RecyclerView rv;
interfaceclick icl;
    adbter_t at;
    train_db_helper db;

    public static final String car_key ="car_key";
    public static int add_new_card=14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_main_act);
        Toolbar toolbar = findViewById(R.id.train_main_toolbar);
        setSupportActionBar(toolbar);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},10);
        }
        rv=findViewById(R.id.recycler_view_train_act);
        db=train_db_helper.set_pointer(this);
        db.open();
        ArrayList<train> tr=db.show_all_trains();
        db.open();

        at =new adbter_t(tr, new interfaceclick() {
            @Override
            public void onclickthat(int id) {
                Intent intent =new Intent(getBaseContext(),train_edit.class);
                intent.putExtra(car_key,id);
                startActivityForResult(intent,add_new_card);
            }
        });
        rv.setAdapter(at);
        RecyclerView.LayoutManager lm =new GridLayoutManager(this,2);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);



        FloatingActionButton fab = findViewById(R.id.train_main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),train_edit.class);
                startActivityForResult(i,add_new_card);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.train_main_menu, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.serching_men_item).getActionView();
        sv.setSubmitButtonEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                db.open();
                at.setPersonArrayList(db.show_some_trains(query));
                at.notifyDataSetChanged();
                db.close();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                db.open();
                at.setPersonArrayList(db.show_some_trains(newText));
                at.notifyDataSetChanged();
                db.close();
                return false;
            }
        });
        sv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                db.open();
                at.setPersonArrayList(db.show_all_trains());
                at.notifyDataSetChanged();
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
            at.setPersonArrayList(db.show_all_trains());
            at.notifyDataSetChanged();
            db.close();

    }
}
