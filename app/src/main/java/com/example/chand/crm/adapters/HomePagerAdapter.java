package com.example.chand.crm.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chand.crm.fragments.Call_Fragment;
import com.example.chand.crm.fragments.Contacts_Fragment;
import com.example.chand.crm.fragments.Fav_Fragment;
import com.example.chand.crm.fragments.Task_Fragment;


public class HomePagerAdapter extends FragmentPagerAdapter {
//    private List<Fragment> fragmentList = new ArrayList<>();
//    private List<String> listTitles = new ArrayList<String>();

    private final String[] PAGE_TITLE = new String[]{
      "Call","Tasks","Events"
    };
    private final Fragment[] PAGES = new Fragment[]{
      new Call_Fragment(),new Task_Fragment(),new Fav_Fragment()
    };


    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PAGES[position];
    }

    @Override
    public int getCount() {
        return PAGES.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLE[position];
    }

//    public void addFragment(Fragment fragment, String title){
//        fragmentList.add(fragment);
//        listTitles.add(title);
//
//    }
}
