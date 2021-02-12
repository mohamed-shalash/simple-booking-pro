package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class train_edit extends AppCompatActivity {
    int cn;
    Menu men;
    TextView num,hour,distin;
    ImageView im;
    train_db_helper db;
    private Uri imge_uri =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_edit);
        num =findViewById(R.id.train_edit_NUM);
        hour =findViewById(R.id.train_edit_HOUR);
        distin =findViewById(R.id.train_edit_DISTINATION);
        im =findViewById(R.id.im_v_train_edit);
        db=train_db_helper.set_pointer(getBaseContext());

        Intent i =getIntent();
        cn =i.getIntExtra(train_main_act.car_key,-1);
        if (cn == -1){enaable();
        }
        else{disable();
           train trains=null;
            db.open();
            trains =db.show_some_by_id(cn);
            db.close();
            num.setText(String.valueOf(trains.getNum()));
            hour.setText(trains.getHour());
            distin.setText(trains.getDistin());
            if (trains.getImage() !=null &&!trains.getImage().isEmpty())
                im.setImageURI(Uri.parse(trains.getImage()));
        }
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent i =new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,122);
            }
        });
    }

    private void disable(){
        num.setEnabled(false);
        hour.setEnabled(false);
        distin.setEnabled(false);
        im.setEnabled(false);
    }
    private void enaable(){
        num.setEnabled(true);
        hour.setEnabled(true);
        distin.setEnabled(true);
        im.setEnabled(true);
    }
    boolean adding=true;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.train_edit_menu,menu);
        men =menu;
        MenuItem save =men.findItem(R.id.save);
        MenuItem edit =men.findItem(R.id.edit);
        MenuItem delete =men.findItem(R.id.delete);
        if (cn== -1){
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MenuItem save =men.findItem(R.id.save);
        MenuItem edit =men.findItem(R.id.edit);
        MenuItem delete =men.findItem(R.id.delete);
        switch (item.getItemId()){
            case R.id.save:
                if (adding){
                    db.open();
                    train t =new train(Integer.parseInt(num.getText().toString()),hour.getText().toString(),distin.getText().toString(),
                            imge_uri.toString());
                        db.add_train(t);
                    db.close();
                    finish();
                }
                else{
                    db.open();
                    train t =new train(Integer.parseInt(num.getText().toString()),hour.getText().toString(),distin.getText().toString(),
                            String.valueOf(imge_uri));
                    db.update_train(t);
                    db.close();
                    finish();
                }
                return true;
            case R.id.edit:
                save.setVisible(true);
                enaable();
                edit.setVisible(false);
                delete.setVisible(false);
                adding=false;
                return true;
            case R.id.delete:
                db.open();
                db.delete_train(cn);
                db.close();
                finish();
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 122){
            imge_uri =data.getData();
            im.setImageURI(imge_uri);
            Toast.makeText(getBaseContext(),""+imge_uri,Toast.LENGTH_SHORT).show();
        }
    }
}
