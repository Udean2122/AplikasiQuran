package com.rifqinaufal.aplikasiquran;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rifqinaufal.aplikasiquran.Fragment.FragmentAlquran;
import com.rifqinaufal.aplikasiquran.Fragment.FragmentHome;
import com.rifqinaufal.aplikasiquran.Fragment.FragmentSholat;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FrameLayout frame;
    BottomNavigationView botoomNavigationView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = findViewById(R.id.frame);
        botoomNavigationView = findViewById(R.id.nav);
        botoomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new FragmentHome());
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment f = null;


        switch (menuItem.getItemId()){
            case R.id.home:
                f = new FragmentHome();
                break;

            case  R.id.alquran:
                f = new FragmentAlquran();
                break;

            case R.id.sholat:
                f = new FragmentSholat();
                break;

        }
        return loadFragment(f);
    }
}
