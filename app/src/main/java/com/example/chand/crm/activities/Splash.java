package com.example.chand.crm.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.chand.crm.R;

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    ImageView imageView;
    //    Animation frombottom;
    SharedPreferences sharedpreferences;
    private static final String MyPREFERENCES = "pref";
    private static final int MY_PERMISSIONS_REQUEST_CONTACTS = 101;
    private static final int REQUEST_READ_STORAGE = 102;
    private static final int REQUEST_ACTION_LOCATION = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.logoprix);
//        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
//        imageView.setAnimation(frombottom);
        new ChangeStatusBarColor(Splash.this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            askPermissions();
        } else {
            init();
        }
    }

    //ask for Permission
    private void askPermissions() {
        boolean permissionContact = (ContextCompat.checkSelfPermission(Splash.this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED);
        boolean permissionExternal = (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
//        boolean permissionloacation = (ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        if ( !permissionExternal || !permissionContact) {
            if (!permissionContact) {
                ActivityCompat.requestPermissions(Splash.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_CONTACTS);

            }
            if (!permissionExternal) {
                ActivityCompat.requestPermissions(Splash.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_READ_STORAGE);

            }
//            if (!permissionloacation) {
//                ActivityCompat.requestPermissions(Splash.this,
//                        new String[]{Manifest.permission_group.LOCATION},
//                        REQUEST_ACTION_LOCATION);
//            }
        } else {
            init();
        }
    }
    //This method is for when we called the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CONTACTS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init();
                }
                break;
            }
                case REQUEST_READ_STORAGE: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        init();
                    }
                    break;
                }
            }
    }
    private void init(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent in = new Intent(Splash.this,Login_Activity.class);
                    startActivity(in);
                    finish();
            }
        },SPLASH_TIME_OUT);
    }
}