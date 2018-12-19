package com.example.chand.crm.activities;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import com.example.chand.crm.R;

public class ChangeStatusBarColor {
    public ChangeStatusBarColor(Activity c) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            Window window = c.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(c.getResources().getColor(R.color.background_black));
        }
    }
}
