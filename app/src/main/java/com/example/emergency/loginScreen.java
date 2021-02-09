package com.example.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class loginScreen extends AppCompatActivity {

    Button signUp,login;
    EditText usernm,pswrd;
    databaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        signUp = (Button)findViewById(R.id.sup);
        login = (Button) findViewById((R.id.sucess));
        usernm = (EditText)findViewById(R.id.us);
        pswrd = (EditText)findViewById(R.id.ps);
        db = new databaseHelper(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginScreen.this,singUp.class);
                startActivity(i);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = usernm.getText().toString();
                String pass = pswrd.getText().toString();
                if (name.equals("") && pass.equals("")){
                    usernm.requestFocus();
                    usernm.setError("Enter username");
                    pswrd.requestFocus();
                    pswrd.setError("Enter password");
                }
                else if(name.equals("adarsh1") && pass.equals("1234")){
                    Intent i = new Intent(loginScreen.this, MainActivity.class);
                    startActivity(i);
                }
                else {

                    Boolean login = db.checkLogin(name,pass);
                    if(login == true) {
                        Intent i = new Intent(loginScreen.this, user_main.class);
                        i.putExtra("username",usernm.getText().toString());
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "INCORRECT USERNAME OR PASSWORD",Toast.LENGTH_LONG).show();
                        usernm.getText().clear();
                        pswrd.getText().clear();
                    }
                }
            }
        });
    }
}