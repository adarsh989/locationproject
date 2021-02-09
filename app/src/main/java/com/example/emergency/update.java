package com.example.emergency;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class update extends AppCompatActivity {

    EditText name,phno,url,lat,log;
    Button update,delete;
    String name1,phno1,url1,lat1,log1,id,sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name = (EditText) findViewById(R.id.name1);
        phno = (EditText) findViewById(R.id.phno1);
        url = (EditText) findViewById(R.id.website1);
        lat = (EditText) findViewById(R.id.lat1);
        log = (EditText) findViewById(R.id.log1);

        update = (Button) findViewById(R.id.sup1);
        delete = (Button) findViewById(R.id.del);
        getIntendData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext()," UPDATED", Toast.LENGTH_SHORT).show();
                databaseHelper db = new databaseHelper(update.this);

                boolean result = db.updateData(sid,id,name.getText().toString(),phno.getText().toString(),url.getText().toString(),lat.getText().toString(),log.getText().toString());
                if(result == true){
                    Toast.makeText(getApplicationContext(),"SUCESSFULLY UPDATED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper db = new databaseHelper(update.this);
                db.deleteRow(id,sid);
            }
        });


    }
    void getIntendData(){
        if(getIntent().hasExtra("name")  &&  getIntent().hasExtra("phno") &&
                getIntent().hasExtra("web")  &&  getIntent().hasExtra("lag")  &&  getIntent().hasExtra("log")){
            name1 = getIntent().getStringExtra("name");
            phno1 = getIntent().getStringExtra("phno");
            url1 = getIntent().getStringExtra("web");
            lat1 = getIntent().getStringExtra("lag");
            log1 = getIntent().getStringExtra("log");
            sid = getIntent().getStringExtra("sid");
            id= getIntent().getStringExtra("id");

            name.setText(name1);
            phno.setText(phno1);
            url.setText(url1);
            lat.setText(lat1);
            log.setText(log1);
        }else
            Toast.makeText(this,"NO DATA" ,Toast.LENGTH_LONG).show();
    }

}