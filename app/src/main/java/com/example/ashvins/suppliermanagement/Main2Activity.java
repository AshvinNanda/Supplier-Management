package com.example.ashvins.suppliermanagement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2activity);

        Toolbar tb = findViewById(R.id.tBar);
        setSupportActionBar(tb);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, tb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new addSupplier()).commit();
            navigationView.setCheckedItem(R.id.nav_add_supplier);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_add_supplier:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new addSupplier()).commit();
                break;
            case R.id.nav_view_supplier:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new supplierList()).commit();
                break;
            case R.id.nav_add_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new addSchedule()).commit();
                break;
            case R.id.nav_view_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new scheduleList()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
