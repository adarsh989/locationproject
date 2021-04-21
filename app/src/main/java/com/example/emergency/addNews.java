package com.example.emergency;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addNews extends AppCompatActivity {

    EditText newstext;
    Button submit;
    databaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        newstext = (EditText)findViewById(R.id.ne);
        submit = (Button)findViewById(R.id.sup);

        db = new databaseHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tnews=newstext.getText().toString();

                if(!Tnews.equals("")){
                    Boolean insertNews = db.insertIntoNews("NEWS",Tnews);
                    if (insertNews == true) {
                        Toast.makeText(addNews.this, "INSERTED", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}