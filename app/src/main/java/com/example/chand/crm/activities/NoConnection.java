package com.example.chand.crm.activities;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.chand.crm.R;

public class NoConnection extends AppCompatActivity {

    TextView tryagain,txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);
        new ChangeStatusBarColor(NoConnection.this);
        tryagain = findViewById(R.id.angry_btn);
        txt1 = findViewById(R.id.can_we);
        txt2 = findViewById(R.id.msg);
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CheckNetwork.isInternetAvailable(NoConnection.this)){
                    txt1.setVisibility(View.INVISIBLE);
                    txt2.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txt1.setVisibility(View.VISIBLE);
                            txt2.setVisibility(View.VISIBLE);
                        }
                    }, 500);

                }else {
                    finish();
                    Intent mainIntent = new Intent(NoConnection.this, Login_Activity.class);
                    startActivity(mainIntent);
                }
            }
        });
    }
}
