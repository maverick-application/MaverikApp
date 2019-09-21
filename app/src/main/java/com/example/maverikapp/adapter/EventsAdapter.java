package com.example.maverikapp.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventsAdapter  extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {


    public EventsAdapter() {
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public EventsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
