package com.rifqinaufal.aplikasiquran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rifqinaufal.aplikasiquran.Fragment.FragmentHome;

import java.util.prefs.Preferences;

public class ActivityDoang extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuma_tombol_doang);

        TextView nama = findViewById(R.id.tv_namaMain);

        nama.setText(Preference.getLoggedInUser(getBaseContext()));


        findViewById(R.id.button_logoutMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), FragmentHome.class));
                finish();
            }
        });
    }
}
