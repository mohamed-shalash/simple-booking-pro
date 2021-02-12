package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class seat_editer extends AppCompatActivity {
int seat_id;
    seat_database_helper db;
    TextView num,cos,pos;
    ImageView im;
    Uri imge_uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_editer);
        Intent i =getIntent();
        seat_id=i.getIntExtra(seat.car_key,-1);

        db=seat_database_helper.getpointer(this);

        num =findViewById(R.id.seat_edit_num);
        cos =findViewById(R.id.seat_edit_cos);
        pos =findViewById(R.id.seat_edit_pos);
        im =findViewById(R.id.im_seat_edit);
        if (seat_id==-1)
        {
            enaable();
        }
        else
            {
                seat_ s=null;
                disable();
                db.open();
                s = db.show_some_seats_by_num(seat_id);
                db.close();
                num.setText(String.valueOf(s.getNum()));
                cos.setText(String.valueOf(s.getCos()));
                pos.setText(s.getPos());
                if (s.getIm() !=null )
                im.setImageURI(Uri.parse(s.getIm()));
            }
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,122);
            }
        });
    }
    private void disable(){
        num.setEnabled(false);
        pos.setEnabled(false);
        cos.setEnabled(false);
        im.setEnabled(false);
    }
    private void enaable(){
        num.setEnabled(true);
        pos.setEnabled(true);
        cos.setEnabled(true);
        im.setEnabled(true);
    }

    Menu m;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seat_editer_menu,menu);
        m=menu;
        MenuItem save =m.findItem(R.id.save_2);
        MenuItem edit =m.findItem(R.id.edit_2);
        MenuItem delete =m.findItem(R.id.delete_2);
        if (seat_id== -1){
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
        MenuItem save=m.findItem(R.id.save_2);
        MenuItem edit=m.findItem(R.id.edit_2);
        MenuItem delete=m.findItem(R.id.delete_2);
        switch (item.getItemId()){
            case R.id.save_2:
                if (adding){
                    seat_ s =new seat_(Integer.parseInt(num.getText().toString()),Float.parseFloat(cos.getText().toString()),pos.getText().toString(),String.valueOf(imge_uri));
                    db.open();
                    db.add_seat(s);
                    db.close();
                    finish();
                }
                else{
                    seat_ s =new seat_(Integer.parseInt(num.getText().toString()),Float.parseFloat(cos.getText().toString()),pos.getText().toString(),String.valueOf(imge_uri));
                    db.open();
                    db.update_seat(s);
                    db.close();
                    finish();
                }
                return true;
            case R.id.edit_2:
                save.setVisible(true);
                enaable();
                edit.setVisible(false);
                delete.setVisible(false);
                adding=false;
                return true;
            case R.id.delete_2:
                db.open();
                db.delete_seat(Integer.parseInt(num.getText().toString()));
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
        }
    }
}
