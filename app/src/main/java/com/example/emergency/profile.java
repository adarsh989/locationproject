package com.example.emergency;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {

    databaseHelper myDb;
    EditText name,username,email,number,password,repassword,err;
    Button update;
    String uname;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myDb = new databaseHelper(this);



        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.usern);
        email = (EditText) findViewById(R.id.email);
        number = (EditText) findViewById(R.id.phno) ;
        password = (EditText) findViewById(R.id.pass);
        repassword = (EditText) findViewById(R.id.repass);
        update = (Button) findViewById(R.id.update);

        uname = getIntent().getStringExtra("username");
        displayData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper db = new databaseHelper(profile.this);

                boolean result = db.updateUserdata(id,name.getText().toString(),username.getText().toString(),email.getText().toString(),password.getText().toString(),number.getText().toString());
                if(result == true){
                    System.out.println("sxsxsd");
                    Toast.makeText(getApplicationContext(),"SUCESSFULLY UPDATED", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    void displayData(){
        Cursor cursor = myDb.userReadData(uname);
        if(cursor.getCount() == 0){
            Toast.makeText(profile.this, "NO DATA FOUND",Toast.LENGTH_SHORT).show();
        }else {
            if (cursor.moveToFirst()) {
                id = cursor.getInt(0);
                name.setText(cursor.getString(1));
                username.setText(cursor.getString(2));
                email.setText(cursor.getString(3));
                password.setText(cursor.getString(5));
                number.setText(cursor.getString(4));
            }
        }
    }
}