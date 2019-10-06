package com.example.maverikapp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maverikapp.R;
import com.example.maverikapp.pojo_response.events.DisplayEventsDetailResponse;

import java.util.List;

public class EventsAdapter  extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    private List<DisplayEventsDetailResponse> eaList;
    private Context eaContext;
    private OnItemClickListener onItemClickListener;
    private SharedPreferences eaSharedPrefernce;
    private String user_id;

    public EventsAdapter(List<DisplayEventsDetailResponse> eaList, Context eaContext) {
        this.eaList = eaList;
        this.eaContext = eaContext;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eaView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);;
        eaContext = parent.getContext();
        return new EventsViewHolder(eaView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holders, int position) {

        final EventsViewHolder holder = holders;
        final DisplayEventsDetailResponse model = eaList.get(position);

        holder.titleV.setText(model.getE_name());
        holder.locationV.setText(model.getE_college().getCollege_username());
        holder.costV.setText(model.getE_cost());
        holder.dateV.setText(model.getE_date());

    }

    @Override
    public int getItemCount() {
        return eaList.size();
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleV,locationV,costV,dateV;
        ImageView imgV;

        public EventsViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            titleV = itemView.findViewById(R.id.ie_title);
            locationV = itemView.findViewById(R.id.ie_location);
            costV =  itemView.findViewById(R.id.ie_cost);
            dateV = itemView.findViewById(R.id.ie_date);
            imgV = itemView.findViewById(R.id.ie_img);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());

        }
    }

    public void setOnItemClickListener(EventsAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
