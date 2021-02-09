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

public class favorate extends AppCompatActivity {

    databaseHelper myDb;
    RecyclerView recyclerView;
    ArrayList<String> name,phno,url,lat,log,sid;
    String Id;

    customFav customFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorate);
        Id = getIntent().getStringExtra("coname");
        recyclerView = findViewById(R.id.recyclerView);

        myDb = new databaseHelper(this);
        sid = new ArrayList<>();
        name = new ArrayList<>();
        phno = new ArrayList<>();
        url = new ArrayList<>();
        lat = new ArrayList<>();
        log = new ArrayList<>();

        displayData();


        customFav = new customFav(favorate.this,favorate.this,name,phno,url,lat,log,Id,sid);
        recyclerView.setAdapter(customFav);
        recyclerView.setLayoutManager(new LinearLayoutManager(favorate.this));
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            recreate();
        }
    }

    void displayData(){
        Cursor cursor = myDb.readFavData(Id);
        if(cursor.getCount() == 0){
            Toast.makeText(favorate.this, "NO DATA FOUND",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                sid.add(cursor.getString(0));
                name.add(cursor.getString(1));
                phno.add(cursor.getString(2));
                url.add(cursor.getString(3));
                lat.add(cursor.getString(4));
                log.add(cursor.getString(5));
            }
        }
    }
}