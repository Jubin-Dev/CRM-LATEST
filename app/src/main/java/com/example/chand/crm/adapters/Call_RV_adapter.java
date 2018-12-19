package com.example.chand.crm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.chand.crm.interfaces.CallRvAdapterInterface;
import com.example.chand.crm.models.ModelCalls;
import com.example.chand.crm.R;
import java.util.List;



public class Call_RV_adapter extends RecyclerView.Adapter<Call_RV_adapter.ViewHolder> {
    private Context mcontext;
    private List<ModelCalls>mcalls ;
    private CallRvAdapterInterface mAdapterCallback;
    public Call_RV_adapter(Context context, List<ModelCalls>callsList, CallRvAdapterInterface adapterCallback){

        mcontext = context;
        mcalls = callsList;
        mAdapterCallback = adapterCallback;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        View view = layoutInflater.inflate(R.layout.items_call,parent,false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final TextView name,duration,date,number,type;
        Button button;
        type = holder.type;
        name = holder.name;
        number = holder.number;
        duration = holder.duration;
        date = holder.date;
        button = holder.button;
        if (mcalls.get(position).getType().equalsIgnoreCase("Incoming")){
            type.setBackgroundResource(R.drawable.ic_call_received_black_24dp);
        }else if (mcalls.get(position).getType().equalsIgnoreCase("Outgoing")){
            type.setBackgroundResource(R.drawable.ic_call_made_black_24dp);
        }else {
            type.setBackgroundResource(R.drawable.ic_call_missed_black_24dp);
        }
        number.setText(mcalls.get(position).getNumber());
        name.setText(mcalls.get(position).getName());
        duration.setText(mcalls.get(position).getDuration()+" sec");
        date.setText(mcalls.get(position).getDate());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterCallback.onClickCallback(mcalls.get(position).getNumber());
            }
        });
        }

    @Override
    public int getItemCount() {
        return mcalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,duration,date,type,number;
        Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            number = itemView.findViewById(R.id.number);
            name = itemView.findViewById(R.id.name);
            duration = itemView.findViewById(R.id.duration);
            date = itemView.findViewById(R.id.date);
            button = itemView.findViewById(R.id.call_button);
        }


    }
}
