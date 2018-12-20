package com.example.chand.crm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chand.crm.R;
import com.example.chand.crm.models.ModelTasks;


import java.util.List;

public class Task_RV_adapter extends RecyclerView.Adapter<Task_RV_adapter.MyViewHolder> {
    private Context mcontext;
    private LayoutInflater inflater;
    private List<ModelTasks> mlistTasks;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mcontext);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task,parent,false);
        Task_RV_adapter.MyViewHolder viewHolder = new Task_RV_adapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlistTasks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView  name,adress,Email,number;
        private Button map,addEvents;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            adress = itemView.findViewById(R.id.item_addres);
            Email = itemView.findViewById(R.id.item_emails);
            number = itemView.findViewById(R.id.item_mobile);
            map = (Button)itemView.findViewById(R.id.location);
            addEvents = (Button)itemView.findViewById(R.id.addtoEvent);

        }
    }


}
