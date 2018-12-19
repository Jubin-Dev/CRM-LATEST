package com.example.chand.crm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chand.crm.activities.CheckNetwork;
import com.example.chand.crm.activities.NoConnection;
import com.example.chand.crm.R;
import com.example.chand.crm.adapters.HomePagerAdapter;
import com.example.chand.crm.adapters.MyPagerAdapter;

public class Home_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public TabLayout tabLayout;
    public ViewPager viewPager;
    View view;
    SwipeRefreshLayout swipeLayout;
    //    private OnFragmentInteractionListener mListener;
    private final int[] ICONS = {R.drawable.ic_call_black_18dp, R.drawable.ic_insert_invitation_black_48dp,
            R.drawable.ic_event_available_black_48dp};
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    public Home_Fragment() {

    }

    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!CheckNetwork.isInternetAvailable(getContext())) {
            CheckNetwork.buildDialog(getActivity()).show();
            getActivity().finish();
            Intent mainIntent = new Intent(getContext(), NoConnection.class);
            startActivity(mainIntent);
        } else {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            viewPager = view.findViewById(R.id.container);
            tabLayout = view.findViewById(R.id.tab_layout);
            tabLayout.setupWithViewPager(viewPager);
            viewPager.setAdapter(new HomePagerAdapter(getActivity().getSupportFragmentManager()));
            tabLayout.setupWithViewPager(viewPager);
        }
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }
        return view;
    }
}