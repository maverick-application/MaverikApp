package com.example.maverikapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.api.Constants;
import com.example.maverikapp.ui.authentication.StartUp;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences maPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView maBottomNavigationView = (BottomNavigationView) findViewById(R.id.ma_bottom_navigation_view);
        NavHostFragment maNavHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.ma_nav_host_fragment);
        NavigationUI.setupWithNavController(maBottomNavigationView,maNavHostFragment.getNavController());

        maPref = getPreferences(0);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent na;
        if(!maPref.getBoolean(Constants.IS_LOGGED_IN,false)){
//            Toast.makeText(MainActivity.this, "Not Logged in", Toast.LENGTH_SHORT).show();
//            na = new Intent(MainActivity.this, StartUp.class);
//            startActivity(na);
        }
    }
}
