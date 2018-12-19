package com.example.chand.crm.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.chand.crm.adapters.Call_RV_adapter;
import com.example.chand.crm.interfaces.CallRvAdapterInterface;
import com.example.chand.crm.interfaces.IFragmentListener;
import com.example.chand.crm.interfaces.ISearch;
import com.example.chand.crm.models.ModelCalls;
import com.example.chand.crm.R;
import com.example.chand.crm.models.Modelcontacts;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Call_Fragment extends Fragment implements CallRvAdapterInterface, ISearch {

    private View view;
    private RecyclerView recyclerView;
    Call_RV_adapter adapter;
    List<ModelCalls> list;
    private IFragmentListener mIFragmentListener = null;
    public Call_Fragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_call_log,container,false);
        recyclerView = view.findViewById(R.id.callog);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Call_RV_adapter(getContext(), getCallLogs(false,null),this);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private List<ModelCalls> getCallLogs(boolean filter,String query){
        list = new ArrayList<>();
        list.clear();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_CALL_LOG},1);
        }
        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,null,
                null,null,CallLog.Calls.DATE + " DESC ");
        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);

        cursor.moveToFirst();
        while(cursor.moveToNext()){
            String Calltype = cursor.getString(type);
            String call_type = "";

            int callTypeCode = Integer.parseInt(Calltype);
            switch (callTypeCode) {
                case 1:
                    call_type = "Incoming";
                    break;
                case 2:
                    call_type = "Outgoing";
                    break;
                case 3:
                    call_type = "Missed";
                    break;
            }

            Date date1 = new Date(Long.valueOf(cursor.getString(date)));
            String mnth_date = (String) DateFormat.getDateInstance().format(date1);
            if (filter){
                if (cursor.getString(number).contains(query)){
                    list.add(new ModelCalls(cursor.getString(name),cursor.getString(number),cursor.getString(duration),mnth_date,call_type));
                }
            }else {
                list.add(new ModelCalls(cursor.getString(name),cursor.getString(number),cursor.getString(duration),mnth_date,call_type));
            }
            Log.d("MIC::",cursor.getString(type));
        }
        return list;
    }


    @Override
    public void onClickCallback(String number) {
//        Toast.makeText(getContext(),number,Toast.LENGTH_LONG).show();
        String dial = "tel:" + number;
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIFragmentListener = (IFragmentListener) context;
        mIFragmentListener.addISearch(Call_Fragment.this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != mIFragmentListener)
            mIFragmentListener.removeISearch(Call_Fragment.this);
    }

    @Override
    public void onTextQuery(String text) {
        list.clear();
//        Toast.makeText(getContext(),"from call frag: "+text,Toast.LENGTH_SHORT).show();
        adapter = new Call_RV_adapter(getContext(), getCallLogs(true,text),this);
        recyclerView.setAdapter(adapter);
    }
}
