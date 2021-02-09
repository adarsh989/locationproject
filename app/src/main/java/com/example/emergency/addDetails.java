package com.example.emergency;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addDetails extends AppCompatActivity {

    Button submit;
    EditText name,phone,web,lat,log;
    Spinner cat;
    databaseHelper db;
    ArrayAdapter<CharSequence>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        submit =(Button)findViewById(R.id.sup);
        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phno);
        web = (EditText)findViewById(R.id.website);
        lat = (EditText)findViewById(R.id.lat);
        log = (EditText)findViewById(R.id.log);
        cat = (Spinner) findViewById(R.id.catg);
        adapter = ArrayAdapter.createFromResource(this,R.array.departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat.setAdapter(adapter);



        db = new databaseHelper(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e1 = name.getText().toString();
                String e2 = phone.getText().toString();
                String e3 = web.getText().toString();
                String e4 = lat.getText().toString();
                String e5 = log.getText().toString();
                String catgry = cat.getSelectedItem().toString();
                if (e1.equals("") || e2.equals("") || e3.equals("") || e4.equals("") || e5.equals("")){
                    Toast.makeText(addDetails.this, "NO FIELD SHOULD BE EMPTY",Toast.LENGTH_SHORT).show();
                }
                else if (!e2.matches("[0-9]{10}")){
                    //Toast.makeText(singUp.this,"INVALID NUMBER",Toast.LENGTH_LONG).show();
                    phone.requestFocus();
                    phone.setError("INVALID PHONE NUMBER");
                }


                else if(!Patterns.WEB_URL.matcher(e3).matches()){
                    web.requestFocus();
                    web.setError("INVALID URL");
                }
                else {
                    if(!catgry.equals("SELECT THE DEPARTMENT")) {
                        Boolean insertData = db.insertToTable(catgry, e1, e2, e3, e4, e5);
                        if (insertData == true) {
                            Toast.makeText(addDetails.this, "INSERTED", Toast.LENGTH_LONG).show();
                            name.getText().clear();
                            phone.getText().clear();
                            web.getText().clear();
                            lat.getText().clear();
                            log.getText().clear();

                        }
                    }
                    else{
                        Toast.makeText(addDetails.this,"NO DEPARTMENT SELECTED",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}