package com.example.trainbooking;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adbter_t extends RecyclerView.Adapter<adbter_t.holder>{
    private ArrayList<train> personArrayList;
    interfaceclick ic ;

    public ArrayList<train> getPersonArrayList() {
        return personArrayList;
    }

    public void setPersonArrayList(ArrayList<train> personArrayList) {
        this.personArrayList = personArrayList;
    }

    public adbter_t(ArrayList<train> personArrayList,interfaceclick ic) {
        this.personArrayList = personArrayList;
        this.ic=ic;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_view_train_act,null,false);
        holder h =new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        train p =personArrayList.get(position);
        if (p.getImage() !=null )
            holder.iv.setImageURI(Uri.parse(p.getImage()));
        holder.hor.setText(p.getHour().toString());
        holder.dis.setText(p.getDistin().toString());
        holder.hor.setTag(p.getNum());
    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    class holder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView dis,hor;
        public holder(@NonNull View itemView) {
            super(itemView);
            iv =itemView.findViewById(R.id.im_per);
            dis =itemView.findViewById(R.id.distination);
            hor =itemView.findViewById(R.id.hour);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x=(int)hor.getTag();
                    ic.onclickthat(x);
                }
            });
        }

    }
}
