package com.example.emergency;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class display extends AppCompatActivity {

    databaseHelper myDb;
    RecyclerView recyclerView;
    ArrayList<String> name,phno,url,lat,log,sid;
    String Id;
    double mylan,mylon;
    Button fav;

    customAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Bundle b = getIntent().getExtras();
        Id = getIntent().getStringExtra("Police");
        mylan = b.getDouble("lan");
        mylon = b.getDouble("lon");
        recyclerView = findViewById(R.id.recyclerView);
        fav = (Button) findViewById(R.id.fav);


        myDb = new databaseHelper(this);
        sid = new ArrayList<>();
        name = new ArrayList<>();
        phno = new ArrayList<>();
        url = new ArrayList<>();
        lat = new ArrayList<>();
        log = new ArrayList<>();


        displayData();

        customAdapter = new customAdapter(display.this,display.this,name,phno,url,lat,log,Id,sid);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(display.this));

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(display.this,favorate.class);
                intent.putExtra("coname",Id);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            recreate();
        }
    }

    void displayData(){
        Cursor cursor = myDb.readData(Id,mylan,mylon);
        if(cursor.getCount() == 0){
            Toast.makeText(display.this, "NO DATA FOUND",Toast.LENGTH_SHORT).show();
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