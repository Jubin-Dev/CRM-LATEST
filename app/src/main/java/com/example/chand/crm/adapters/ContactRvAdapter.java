package com.example.chand.crm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chand.crm.interfaces.CallRvAdapterInterface;
import com.example.chand.crm.interfaces.ContactsRvAdapterInterface;
import com.example.chand.crm.models.Modelcontacts;
import com.example.chand.crm.R;

import java.util.List;

/**
 * Created by chand on 12/7/2018.
 */

public class ContactRvAdapter extends RecyclerView.Adapter<ContactRvAdapter.ViewHolder> {
    private Context mcontext;
    private LayoutInflater inflater;
    private ContactsRvAdapterInterface mAdapterCallback;
private List<Modelcontacts> mlistContacts;
public ContactRvAdapter(Context context,List<Modelcontacts>listContacts, ContactsRvAdapterInterface adapterCallback){
    mlistContacts = listContacts;
    mcontext=context;
    mAdapterCallback = adapterCallback;

}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mcontext);
//        View view = inflater.inflate(R.layout.item_contact,parent,false);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        TextView contact_name,contact_number;
        Button button;
        contact_name=holder.contact_name;
        contact_number=holder.contact_number;
        button = holder.button;
        contact_name.setText(mlistContacts.get(position).getName());
        contact_number.setText(mlistContacts.get(position).getNumber());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterCallback.onClickCallback(mlistContacts.get(position).getNumber());

            }
        });
}

    @Override
    public int getItemCount() {
        return mlistContacts.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{
        TextView contact_name,contact_number;
        Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
            button = itemView.findViewById(R.id.callbutton);
        }
    }
}
