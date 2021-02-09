package com.example.emergency;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customerUserAdapter  extends RecyclerView.Adapter<customerUserAdapter.userViewHolder> {

    private Context context;
    private ArrayList name;
    Activity activity;
    String dlname;

    customerUserAdapter(Context context,Activity activity,ArrayList name){
        this.context=context;
        this.activity=activity;
        this.name=name;
    }


    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row,parent,false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        System.out.println(name.size());
        holder.name.setText(String.valueOf(name.get(position)));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("DELETE!!!");
                builder.setMessage("ARE YOU SURE WANT TO DELETE");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper db = new databaseHelper(context);
                        dlname = String.valueOf(name.get(position));
                        int result = db.deleteUser(dlname);

                        if(result > 0){
                            name.remove(dlname);
                            notifyDataSetChanged();
                            Toast.makeText(context,"DELETED",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(context,"FAILED",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("no",null);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public  class userViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        Button delete;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username);
            delete = itemView.findViewById(R.id.userdelete);

        }
    }
}
