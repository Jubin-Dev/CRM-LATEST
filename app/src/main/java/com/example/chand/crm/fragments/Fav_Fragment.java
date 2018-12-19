package com.example.chand.crm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chand.crm.R;


public class Fav_Fragment extends Fragment {

    private View view;

    public Fav_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fav_contact_fragment,container,false);
        return view;
    }
}
