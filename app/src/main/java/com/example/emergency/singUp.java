package com.example.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;



public class singUp extends AppCompatActivity {
    databaseHelper myDb;
    TextView toLogin;
    EditText name,username,email,number,password,repassword,err;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        myDb = new databaseHelper(this);


        toLogin = (TextView) findViewById(R.id.already);
        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.usern);
        email = (EditText) findViewById(R.id.email);
        number = (EditText) findViewById(R.id.phno) ;
        password = (EditText) findViewById(R.id.pass);
        repassword = (EditText) findViewById(R.id.repass);
        register = (Button) findViewById(R.id.sup);
       // err =(EditText) findViewById(R.id.err);

        NotificationManagerCompat.from(singUp.this).areNotificationsEnabled();




        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(singUp.this,loginScreen.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().equals(" ") || username.getText().toString().equals(" ") || email.getText().toString().equals(" ") ||  number.getText().toString().equals(" ") ||
                        password.getText().toString().equals(" ") || repassword.getText().toString().equals("")){
                    Toast.makeText(singUp.this, "NO FIELD SHOULD BE EMPTY",Toast.LENGTH_SHORT).show();
                }


                else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    //Toast.makeText(singUp.this,"INVALID EMAIL",Toast.LENGTH_LONG).show();
                    email.requestFocus();
                    email.setError("INVALID EMAIL");
                }
                else if (!number.getText().toString().matches("[0-9]{10}")){
                    //Toast.makeText(singUp.this,"INVALID NUMBER",Toast.LENGTH_LONG).show();
                    number.requestFocus();
                     number.setError("INVALID PHONE NUMBER");
                }

                else if(password.getText().toString().equals(repassword.getText().toString())){
                    //Toast.makeText(singUp.this, "PASSWORD DOES NOT MATCH",Toast.LENGTH_LONG).show();
                   //

                    Boolean chekuser = myDb.checkUser(username.getText().toString(),email.getText().toString(),number.getText().toString());
                    if(chekuser== false){
                        Boolean isInserted = myDb.insertData(name.getText().toString(),
                                username.getText().toString(),
                                email.getText().toString(),
                                number.getText().toString(),
                                password.getText().toString());

                        if (isInserted == true){
                            Toast.makeText(singUp.this,"INSERTED",Toast.LENGTH_LONG).show();
                            Intent a= new Intent(singUp.this,loginScreen.class);
                            startActivity(a);
                        }
                        else{
                            Toast.makeText(singUp.this,"REGISTRATION FAILED",Toast.LENGTH_LONG).show();
                            name.getText().clear();
                            username.getText().clear();
                            email.getText().clear();
                            number.getText().clear();
                            password.getText().clear();
                            repassword.getText().clear();
                        }
                    }
                    else{
                        username.requestFocus();
                        username.setError("ALREADY EXISTS");
                    }
                }
                else {
                    repassword.requestFocus();
                    repassword.setError("PASSWORD DOES'NT MATCH");
                }
            }
        });

    }
}