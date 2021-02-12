package com.example.trainbooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class person_adabter extends RecyclerView.Adapter<person_adabter.holder> {
    ArrayList<remaking_per> per;
    interfaceclick inf;

    public ArrayList<remaking_per> getPer() {
        return per;
    }

    public void setPer(ArrayList<remaking_per> per) {
        this.per = per;
    }

    public person_adabter(ArrayList<remaking_per> per, interfaceclick inf) {
        this.per = per;
        this.inf=inf;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_person_card_view,null,false);
        holder h =new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        remaking_per p=per.get(position);
        holder.name.setText(p.getName());
        holder.seat.setText(String.valueOf(p.getSeat()));
        holder.train.setText(String.valueOf(p.getHour()));
        holder.name.setTag(p.getNum());
    }

    @Override
    public int getItemCount() {
        return per.size();
    }

    class holder extends RecyclerView.ViewHolder{
        MaterialTextView name,seat,train;
        public holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.card_person_name);
            seat=itemView.findViewById(R.id.card_person_seat);
            train=itemView.findViewById(R.id.card_person_train);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x=(int)name.getTag();
                    inf.onclickthat(x);
                }
            });
        }
    }
}
