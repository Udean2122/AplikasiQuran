package com.rifqinaufal.aplikasiquran;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rifqinaufal.aplikasiquran.Fragment.FragmentAlquran;
import com.rifqinaufal.aplikasiquran.Fragment.FragmentHome;

public class LoginActivity extends AppCompatActivity {
    private EditText mViewUser, mViewPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewUser = findViewById(R.id.emailSignin);
        mViewPassword = findViewById(R.id.passwordSignin);
        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_NULL){
                    makan();
                    return true;
                }
                return false;
            }

        });

        findViewById(R.id.button_SigninSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makan();
            }
        });

        findViewById(R.id.buton_SignupSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preference.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(), FragmentHome.class));
            finish();
        }
    }

    private  void makan(){
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        if ( TextUtils.isEmpty(user)) {
            mViewUser.setError("This Field Is Required");
            fokus = mViewUser;
            cancel = true;
        }else if (!cekUser(user)){
            mViewUser.setError("This Username Is On Mars");
            fokus = mViewUser;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password)){
            mViewPassword.setError("This password is incorrect");
            fokus = mViewPassword;
            cancel = true;
        }

        if (cancel) fokus.requestFocus();
        else masuk();
    }

    private void masuk(){
        Preference.setLoggedInUser(getBaseContext(),Preference.getRegisteredUser(getBaseContext()));
        Preference.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(), ActivityDoang.class));finish();
    }

    private boolean cekPassword(String password){
        return password.equals(Preference.getRegisteredPass(getBaseContext()));
    }

    private boolean cekUser(String user){
        return user.equals(Preference.getRegisteredUser(getBaseContext()));
    }

}
