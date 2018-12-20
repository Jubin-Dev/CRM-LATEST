package com.example.chand.crm.activities;
import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.chand.crm.R;
import com.example.chand.crm.adapters.MyPagerAdapter;
import com.example.chand.crm.fragments.Call_Fragment;
import com.example.chand.crm.interfaces.IFragmentListener;
import com.example.chand.crm.interfaces.ISearch;

import java.util.ArrayList;


public class ContactMain extends AppCompatActivity implements SearchView.OnQueryTextListener,IFragmentListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    SearchView searchView = null;
    private final int[] ICONS = {R.drawable.ic_call_black_18dp, R.drawable.ic_contact_phone_black_24dp, R.drawable.ic_star_36pt};

    ArrayList<ISearch> iSearch = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_main);
        Toolbar mytoolbar = findViewById(R.id.toolbar);
        mytoolbar.setTitle("Contacts");
        mytoolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Toast.makeText(getApplicationContext(), "OnPageScrolled", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getApplicationContext(), "Selected page position: " + position,
                        Toast.LENGTH_LONG).show();
                if (searchView != null && !searchView.isIconified()) {
                    searchView.setIconified(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Toast.makeText(getApplicationContext(), "onPageScrollStateChange",
                        Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }
        askPermission();
    }

    private void askPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_optionmenu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search_button);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }
        searchView.setIconifiedByDefault(true);
        MenuItemCompat.expandActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("serach data:", query);
        Toast.makeText(getApplicationContext(), "ffffff", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("serach data1:", newText);
//        PagerAdapter adapter = (PagerAdapter) viewPager.getAdapter();
//        for (int i = 0; i < adapter.getCount(); i++) {
//            Fragment viewPagerFragment = (Fragment) viewPager.getAdapter().instantiateItem(viewPager, i);
//            if (viewPagerFragment != null && viewPagerFragment.isAdded()) {
//              if (viewPagerFragment instanceof Call_Fragment ) {
//                  viewPager.getAdapter();
//                  }
//
//              }
//                }
//            for (ISearch iSearchLocal : this.iSearch)
//                iSearchLocal.onTextQuery(newText);


            int fragIndex = viewPager.getCurrentItem();
            switch (fragIndex){
                case 0: iSearch.get(fragIndex).onTextQuery(newText);
                    break;
                case 1: iSearch.get(fragIndex).onTextQuery(newText);
                    break;
                case 2: iSearch.get(fragIndex).onTextQuery(newText);
                    break;
            }
//            Toast.makeText(getApplicationContext(), String.valueOf(fragIndex), Toast.LENGTH_LONG).show();

//        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
//        if (f instanceof Call_Fragment){
//            viewPager.getAdapter();
//            Toast.makeText(getApplicationContext(),"call",Toast.LENGTH_LONG).show();
//        }if (f instanceof Contacts_Fragment){
//            Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_LONG).show();
//        }if (f instanceof Fav_Fragment){
//            Toast.makeText(getApplicationContext(),"fav",Toast.LENGTH_LONG).show();
//        }
            return true;
        }

    @Override
    public void addISearch(ISearch iSearch) {
        this.iSearch.add(iSearch);
    }

    @Override
    public void removeISearch(ISearch iSearch) {
        this.iSearch.remove(iSearch);
    }
}
