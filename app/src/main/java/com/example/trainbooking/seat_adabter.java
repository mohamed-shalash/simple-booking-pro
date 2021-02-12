package com.example.trainbooking;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class seat_adabter extends RecyclerView.Adapter<seat_adabter.holder> {
    private ArrayList<seat_> personArrayList;
    interfaceclick ic ;

    public ArrayList<seat_> getseatArrayList() {
        return personArrayList;
    }

    public void setseatArrayList(ArrayList<seat_> personArrayList) {
        this.personArrayList = personArrayList;
    }

    public seat_adabter(ArrayList<seat_> personArrayList,interfaceclick ic) {
        this.personArrayList = personArrayList;
        this.ic=ic;
    }

    @NonNull
    @Override
    public seat_adabter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_seat_card_view,null,false);
        holder h =new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull seat_adabter.holder holder, int position) {
        seat_ p =personArrayList.get(position);
        if (p.getIm() !=null )
            holder.iv.setImageURI(Uri.parse(p.getIm()));
        holder.cos.setText(p.getCos()+"");
        holder.pos.setText(p.getPos());
        holder.pos.setTag(p.getNum());
    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    class holder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView cos,pos;
        public holder(@NonNull View itemView) {
            super(itemView);
            iv =itemView.findViewById(R.id.seat_image);
            cos =itemView.findViewById(R.id.cost_card_view_seat);
            pos =itemView.findViewById(R.id.pos_card_view_seat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x=(int)pos.getTag();
                    ic.onclickthat(x);
                }
            });
        }

    }
}
