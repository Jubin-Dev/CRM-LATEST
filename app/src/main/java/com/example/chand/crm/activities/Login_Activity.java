package com.example.chand.crm.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chand.crm.fragments.Home_Fragment;
import com.example.chand.crm.R;

public class Login_Activity extends AppCompatActivity implements TextWatcher{
    private TextInputLayout inputLayoutUid, inputLayoutPassword;
    private  EditText UserPass, Userid;
    private CheckBox Idpass;
    private Button button;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private static final String MyPREFERENCE = "MyPref";
    private  static final String KEY_USERID = "Userid";
    private  static  final String KEY_PASS = "Userpass";
    private  static  final String KEY_REMEMBER = "remember";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!CheckNetwork.isInternetAvailable(Login_Activity.this)) {

            Intent intent = new Intent(Login_Activity.this, NoConnection.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            init();

        } else {
            setContentView(R.layout.activity_login_);
            init();
        }
    }
    public void init() {

        sharedpreferences = getSharedPreferences(MyPREFERENCE, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        Userid = findViewById(R.id.uid);
        UserPass = findViewById(R.id.password);
        Idpass = findViewById(R.id.remember);
        button = findViewById(R.id.login_button);
        inputLayoutUid = findViewById(R.id.inputUid);
        inputLayoutPassword = findViewById(R.id.inputPassword);
        if (sharedpreferences.getBoolean(KEY_REMEMBER ,false)) {
            Idpass.setChecked(true);
//            finish();
//            startActivity(new Intent(Login_Activity.this, Navigation.class));
        } else{
            Idpass.setChecked(false);
        }
        Userid.setText(sharedpreferences.getString(KEY_USERID,""));
        UserPass.setText(sharedpreferences.getString(KEY_PASS,""));
        button = findViewById(R.id.login_button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                button.setEnabled(false);
                String Uid = Userid.getText().toString().trim();
                String Pass = UserPass.getText().toString().trim();
                if (Uid.isEmpty()) {
                    inputLayoutUid.setError(getString(R.string.error_field_required));
                    requestFocus(inputLayoutUid);
                    button.setEnabled(true);
                    return;
                }
                if (Pass.isEmpty()) {
                    inputLayoutPassword.setError(getString(R.string.error_field_required));
                    requestFocus(inputLayoutPassword);
                    button.setEnabled(true);
                    return;
                }



                button.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();


//                userLogin();
                final ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this,
                        R.style.Theme_AppCompat_Light_Dialog_Alert);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();
                new android.os.Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        // On complete call either onLoginSuccess or onLoginFailed
                                        userLogin();
//                                        onLoginSuccess();
//                                        onLoginFailed();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);
            }
        });
    }
            private void userLogin() {
                onLoginSuccess();
//                if (!isUidValid()) {
//                    return;
//                }
//                if (!isPasswordValid()) {
//                    return;
//                }
//                button.setEnabled(false);
//                Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
            }

//            private boolean isUidValid() {
//                String Uid = inputLayoutUid.getEditText();
//
//                if (Uid.isEmpty() || !isUidValid()) {
//                    inputLayoutUid.setError(getString(R.string.error_field_required));
//                    requestFocus(inputLayoutUid);
//                    return false;
//                } else {
//                    inputLayoutUid.setErrorEnabled(true);
//                }
//
//                return true;
//            }
//
//            private boolean isPasswordValid() {
//                String Pass = inputLayoutPassword.getEditText().toString().trim();
//
//                if (Pass.isEmpty() || !isPasswordValid()) {
//                    inputLayoutPassword.setError(getString(R.string.error_field_required));
//                    requestFocus(inputLayoutPassword);
//                    return false;
//                } else {
//                    inputLayoutPassword.setErrorEnabled(true);
//                }
//
//                return true;
//            }



            private boolean isUidValid() {
                String Uid = inputLayoutUid.getEditText().toString().trim();
//                if (Uid.length() >= 6) {
                    Pattern pattern;
                    Matcher matcher;
                    final String Email_pattern = "^[A-Za-z0-9]{6,15}$";
                    pattern = Pattern.compile(Email_pattern);
                    matcher = pattern.matcher(Uid);
                    if (matcher.matches()){
                        return false;
                    }else {
                        return true;
                    }
//                    return matcher.matches();
//                } else {
//                    inputLayoutUid.setError(getString(R.string.error_invalid_email));
//                    requestFocus(inputLayoutUid);
//                    inputLayoutUid.setErrorEnabled(true);
//                    return false;
//                }
            }

            private boolean isPasswordValid() {
                String Pass = inputLayoutUid.getEditText().toString().trim();
//                if (Pass.length() >= 6) {
                    Pattern pattern;
                    Matcher matcher;
                    final String Pass_Pattern = "^[A-Za-z0-9_@#$%-]{6,15}$";
                    pattern = Pattern.compile(Pass_Pattern);
                    matcher = pattern.matcher(Pass);
                    if (matcher.matches()){
                        return false;
                    }else {
                        return true;
                    }
//                    return matcher.matches();
//                } else {
//                    inputLayoutPassword.setError(getString(R.string.error_invalid_pass));
//                    requestFocus(inputLayoutPassword);
//                    inputLayoutUid.setErrorEnabled(true);
//                    return false;
//                }
            }
    public void onLoginSuccess() {
        button.setEnabled(true);
        Intent i = new Intent(Login_Activity.this , Home_Fragment.class);
        startActivity(i);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        button.setEnabled(false);
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.ANIMATION_CHANGED);
        }
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        managePrefs();

    }

    @Override
    public void afterTextChanged(Editable s) {
        managePrefs();
    }
    private void managePrefs() {
        if (Idpass.isChecked()){
            editor.putString(KEY_USERID,Userid.getText().toString().trim());
            editor.putString(KEY_PASS,UserPass.getText().toString().trim());
            editor.putString(KEY_REMEMBER,Idpass.getText().toString().trim());
        }else{
            editor.putBoolean(KEY_REMEMBER,false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERID);
            editor.commit();
        }
    }
}
