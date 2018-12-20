package com.example.chand.crm.navigation;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.chand.crm.activities.CheckNetwork;
import com.example.chand.crm.activities.ContactMain;
import com.example.chand.crm.activities.NoConnection;
import com.example.chand.crm.forms.Main_forms;
import com.example.chand.crm.fragments.Home_Fragment;
import com.example.chand.crm.R;
import com.example.chand.crm.map.MapsActivity2;


public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;
    public TabLayout tabLayout;
    public ViewPager viewPager;

//    setupViewPager(viewPager);
    private int[] tabIcons = {
            R.drawable.ic_add_location_white_48dp,
            R.drawable.ic_insert_invitation_black_48dp,
            R.drawable.ic_event_available_black_48dp
    };
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!CheckNetwork.isInternetAvailable(Navigation.this)) {
            finish();
            Intent intent = new Intent(Navigation.this, NoConnection.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_home_nav);
//            floatingActionButton1 = findViewById(R.id.location);
//            floatingActionButton2 = findViewById(R.id.Event);
//            floatingActionButton3 = findViewById(R.id.task);
//            navigationView = findViewById(R.id.nav_view);
//            viewPager = findViewById(R.id.container);
//            tabLayout = findViewById(R.id.tab_layout);
//            tabLayout.setupWithViewPager(viewPager);
//            setupTabIcon();
            setUpToolbar();
//            floatingActionButton1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(Navigation.this, MapsActivity2.class);
//                    startActivity(i);
//                    finish();
//
//                }
//            });
//            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    switch (item.getItemId()) {
//                        case R.id.nav_view:
//                            break;
//                    }
//                    return false;
//                }
//            });
        }
    }

    protected void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu =navigationView.getMenu();
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Home_Fragment hf = (Home_Fragment) getSupportFragmentManager().findFragmentByTag("home-fragment");
        if (hf == null) {
            hf = Home_Fragment.newInstance("", "");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, hf).commit();
    }
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public  void  onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void setToolbar(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu =navigationView.getMenu();
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Home_Fragment hf = (Home_Fragment) getSupportFragmentManager().findFragmentByTag("home-fragment");
        if (hf == null) {
            hf = Home_Fragment.newInstance("", "");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, hf).commit();
    }
//    private  void setupTabIcon(){
//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_layout,null);
//        tabOne.setText("MAP");
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_add_location_white_48dp,0,0);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
//
//        TextView tabtwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_layout,null);
//        tabtwo.setText("Events");
//        tabtwo.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_insert_invitation_black_48dp,0,0);
//        tabLayout.getTabAt(1).setCustomView(tabtwo);
//
//        TextView tabthree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_layout,null);
//        tabthree.setText("Tasks");
//        tabthree.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_event_available_black_48dp,0,0);
//        tabLayout.getTabAt(2).setCustomView(tabthree);
//    }
//    private void setupViewPager(ViewPager viewPager) {
//        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
//
//        adapter.addfrag(new OneFragment(), "ONE");
//        adapter.addFrag(new TwoFragment(), "TWO");
//        adapter.addFrag(new ThreeFragment(), "THREE");
//        viewPager.setAdapter(adapter);

    public void openShare(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getText(R.string.share_text));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share"));
    }
   //This is for navigate to select from menu item to fragment activities or other normal Activites
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homepage) {
            Home_Fragment hf = (Home_Fragment) getSupportFragmentManager().findFragmentByTag("home-fragment");
            if (hf == null) {
                hf = Home_Fragment.newInstance("", "");
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, hf).commit();
        }else if (id == R.id.nav_addcustomer ){
            Intent i = new Intent(Navigation.this, Main_forms.class);
            startActivity(i);
        } else if(id == R.id.contacts_menu) {
            Intent intent = new Intent(Navigation.this, ContactMain.class);
            startActivity(intent);
        } else if (id == R.id.nav_location){
            Intent intent = new Intent(Navigation.this, MapsActivity2.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}