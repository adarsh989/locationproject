package com.example.emergency;

import  android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import de.hdodenhof.circleimageview.CircleImageView;

public class user_main extends AppCompatActivity {

    CircleImageView add;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8,cardView9,cardView10,cardView11,cardView12;
    double latitude,longitude;
    String uname,uname1;
    TextView news;
    databaseHelper myDb;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        getSupportActionBar().hide();



        add = (CircleImageView) findViewById(R.id.prof);
        cardView1 = (CardView) findViewById(R.id.crd1);
        cardView2 = (CardView) findViewById(R.id.crd2);
        cardView3 = (CardView) findViewById(R.id.crd3);
        cardView4 = (CardView) findViewById(R.id.crd4);
        cardView5 = (CardView) findViewById(R.id.crd5);
        cardView6 = (CardView) findViewById(R.id.crd6);
        cardView7 = (CardView) findViewById(R.id.crd7);
        cardView8 = (CardView) findViewById(R.id.crd8);
        cardView9 = (CardView) findViewById(R.id.crd9);
        cardView10 = (CardView) findViewById(R.id.crd10);
        cardView11 = (CardView) findViewById(R.id.crd11);
        cardView12 = (CardView) findViewById(R.id.crd12);
        news = (TextView)findViewById(R.id.sc);

        myDb = new databaseHelper(this);

        news.setMovementMethod(new ScrollingMovementMethod());

        //uname1 = getIntent().getStringExtra("NEWS");
        getNews();

        news.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        news.setSelected(true);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = getIntent().getStringExtra("username");
                Intent intent = new Intent(user_main.this,profile.class);
                intent.putExtra("username",uname);
                startActivity(intent);

            }
        });


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "police";
                System.out.println("aaaa");
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Fire_force";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Hospital";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Ambulance";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Child_helpline";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Women_helpline";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Kseb";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Water_authority";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Pwd_office";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Disaster_managmnt";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Coast_guard";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cardView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "Anti_ragging_helpline";
                Intent intent = new Intent(user_main.this,display.class);
                Bundle b = new Bundle();
                b.putDouble("lan", latitude);
                b.putDouble("lon",longitude);
                intent.putExtra("Police",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }

    private void getNews() {
        Cursor cursor = myDb.readNews();
        if(cursor.getCount() == 0){
            Toast.makeText(user_main.this, "NO DATA FOUND",Toast.LENGTH_SHORT).show();
        } else {
            if (cursor.moveToFirst()) {
                news.setText(cursor.getString(1));
            }
        }
    }

    public void onLocationChanged(@NonNull Location location) {
        if(location != null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();}

    }
}