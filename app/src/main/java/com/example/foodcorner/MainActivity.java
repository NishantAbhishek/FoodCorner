package com.example.foodcorner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.foodcorner.Fragment.CartFragment;
import com.example.foodcorner.Fragment.HomeFragment;
import com.example.foodcorner.Fragment.ProfileFragment;
import com.example.foodcorner.Fragment.SearchFragment;
import com.google.android.libraries.places.api.Places;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Places.initialize(getApplicationContext(), getString(R.string.ApiKey), Locale.US);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
        initialize();

    }
    private void initialize(){
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(selectedListener);

    }

    BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.nav_search:
                    fragment = new SearchFragment();
                    break;
                case R.id.nav_cart:
                    fragment = new CartFragment();
                    break;
                case R.id.nav_account:
                    fragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }
    };
}