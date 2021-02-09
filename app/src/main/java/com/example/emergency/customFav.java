package com.example.emergency;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customFav extends RecyclerView.Adapter<customFav.MyfavViewHolder> {

    private Context context;
    private ArrayList name,phone,url,lat,log,sid;
    Activity activity;
    String mbno,id;

    customFav(Activity activity,Context context, ArrayList name,
              ArrayList phone,
              ArrayList url,
              ArrayList lat,
              ArrayList log,
              String id,
              ArrayList sid){
        this.activity=activity;
        this.context =context;
        this.id = id;
        this.sid = sid;
        this.name = name;
        this.phone = phone;
        this.url = url;
        this.lat = lat;
        this.log=log;
    }
    @NonNull
    @Override
    public MyfavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fav_row,parent,false);
        return new MyfavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyfavViewHolder holder, int position) {
        holder.name.setText(String.valueOf(name.get(position)));

        holder.imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbno = String.valueOf(phone.get(position));
                String call = "tel:" +mbno.trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(call));
                context.startActivity(intent);
            }
        });

        holder.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Url = String.valueOf(url.get(position));
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Url));
                context.startActivity(i);
            }
        });

        holder.imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitude = String.valueOf(lat.get(position));
                String longitude = String.valueOf(log.get(position));
                String uri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);
            }
        });

        holder.imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper db = new databaseHelper(context);
                String name1 =  String.valueOf(name.get(position));
                int result = db.favorate(name1,id,0);

                if (result > 0){
                    
                    Toast.makeText(context,"FAVORATE REMOVED",Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(context,"FAILED",Toast.LENGTH_LONG).show();
            }
        });

        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update.class);
                intent.putExtra("name" ,String.valueOf(name.get(position)));
                intent.putExtra("phno" ,String.valueOf(phone.get(position)));
                intent.putExtra("web" , String.valueOf(url.get(position)));
                intent.putExtra("lag" ,String.valueOf(lat.get(position)));
                intent.putExtra("log" ,String.valueOf(log.get(position)));
                intent.putExtra("sid" ,String.valueOf(sid.get(position)));
                intent.putExtra("id" ,id);

                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyfavViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageButton imageButton1,imageButton2,imageButton3,imageButton4;
        LinearLayout main;

        public MyfavViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.s2);
            imageButton1 = itemView.findViewById(R.id.call);
            imageButton2 = itemView.findViewById(R.id.url);
            imageButton3 = itemView.findViewById(R.id.loc);
            imageButton4 = itemView.findViewById(R.id.delt);
            main = itemView.findViewById(R.id.fav);
        }
    }
}
