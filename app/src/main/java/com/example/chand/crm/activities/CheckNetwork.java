package com.example.chand.crm.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.text.Html;
public class CheckNetwork {

    public static boolean isInternetAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public static AlertDialog buildDialog(final Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);

//        builder.getWindow().setBackgroundDrawableResource(android.R.color.background_dark);
        builder.setTitle(Html.fromHtml("<font color='#5e914d' ><b>No Internet Connection</b></font>"));
        builder.setMessage(Html.fromHtml("<font color='#ffffff' ><b>Please turn on Wifi or Mobile data to access. Press ok to Exit</b></font>"));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity)c).finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                ((Activity)c).finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.darker_gray);
        return dialog;
    }
}
