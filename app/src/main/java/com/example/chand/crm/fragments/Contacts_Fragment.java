package com.example.chand.crm.fragments;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chand.crm.adapters.ContactRvAdapter;
import com.example.chand.crm.interfaces.ContactsRvAdapterInterface;
import com.example.chand.crm.interfaces.IFragmentListener;
import com.example.chand.crm.interfaces.ISearch;
import com.example.chand.crm.models.Modelcontacts;
import com.example.chand.crm.R;
 import java.util.ArrayList;
 import java.util.List;


public class Contacts_Fragment extends Fragment implements ContactsRvAdapterInterface, ISearch {

    private View view;
    private RecyclerView recyclerView;
    private IFragmentListener mIFragmentListener = null;
    List<Modelcontacts> list;
    ContactRvAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contacts_,container,false);
        recyclerView = view.findViewById(R.id.contacts);
        LinearLayoutManager Manager= new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = Manager;
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContactRvAdapter(getContext(),getContacts(false, null),this);
        recyclerView.setAdapter(adapter);

        return view;
    }
    private List<Modelcontacts>getContacts(boolean filter,String query){
        list = new ArrayList<>();
        Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,ContactsContract.Contacts.DISPLAY_NAME + " ASC ");

        cursor. moveToFirst();

        while(cursor.moveToNext()){
            if (filter){
                if (cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds
                        .Phone.DISPLAY_NAME )).contains(query)){
                    list.add(new Modelcontacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds
                            .Phone.DISPLAY_NAME )),cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));
                }
            }else {
                list.add(new Modelcontacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds
                        .Phone.DISPLAY_NAME )),cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));
            }
        }
        return list;
    }

    @Override
    public void onClickCallback(String contact_number) {
       // Toast.makeText(getContext(),contact_number,Toast.LENGTH_LONG).show();
        String dial = "tel:" + contact_number;
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mIFragmentListener = (IFragmentListener) context;
//        mIFragmentListener.addISearch(Contacts_Fragment.this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != mIFragmentListener)
            mIFragmentListener.removeISearch(Contacts_Fragment.this);
    }

    @Override
    public void onTextQuery(String text) {
        list.clear();
//        Toast.makeText(getContext(),"from contact frag: "+text,Toast.LENGTH_SHORT).show();
        adapter = new ContactRvAdapter(getContext(), getContacts(true,text),this);
        recyclerView.setAdapter(adapter);
    }
}
