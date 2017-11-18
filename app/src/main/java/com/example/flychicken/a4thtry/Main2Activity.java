package com.example.flychicken.a4thtry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
LocationManager locationManager;
String lattitude,longitude;
    private static final int REQUEST_LOCATION = 1;
    DatabaseReference myRef;
    FirebaseDatabase database;
    EditText inputTitle;
    Button btn_post;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myRef = FirebaseDatabase.getInstance().getReference("work");

        btn_post = (Button)findViewById(R.id.btn_postwork);
        inputTitle=(EditText) findViewById(R.id.input_workTitle);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    buildAlertMessageNoGps();
                }  else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getLocation();
                }
              //  addWork();
            }
        });

    }
    private double[] getLocation() {
        if (ActivityCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (Main2Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            return null;
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                return new double[]{ latti,longi};



            }else{
                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();
                return null;
            }
        }

    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    private void addWork(){
        String title = inputTitle.getText().toString().trim();
        double gps[] =null;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            buildAlertMessageNoGps();
        }  else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            gps = getLocation();
    }

        String lon =null;
        String lat =null;
        if(lon != null) {
           lon = String.valueOf(gps[0]);
            lat = String.valueOf(gps[1]);
        }else{
            Toast.makeText(this, "gps not working", Toast.LENGTH_SHORT).show();

        }
        if(!TextUtils.isEmpty(title)){
            String id = myRef.push().getKey();
            workInput oneWork = new workInput(id,title,lat,lon);

            myRef.child(id).setValue(oneWork);

            Toast.makeText(this,"work added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_SHORT).show();
        }
    }


}
