package com.example.emergency;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userlist extends AppCompatActivity {

    databaseHelper myDb;
    RecyclerView recyclerView;
    ArrayList<String> name;

    customerUserAdapter customUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        recyclerView = findViewById(R.id.recyclerView1);

        myDb = new databaseHelper(this);
        name = new ArrayList<>();

        displayUserData();

        customUserAdapter = new customerUserAdapter(userlist.this,userlist.this,name);
        recyclerView.setAdapter(customUserAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(userlist.this));

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            recreate();
        }
    }


    void displayUserData(){
        Cursor cursor = myDb.readUserData();
        if(cursor.getCount() == 0){
            Toast.makeText(userlist.this, "NO DATA FOUND",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(2));
                System.out.println("name are"+name);
            }
        }
    }
}