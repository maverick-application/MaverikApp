package com.example.maverikapp.ui.events;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.adapter.EventsAdapter;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.pojo_response.events.DisplayEventsDetailResponse;
import com.example.maverikapp.pojo_response.events.DisplayEventsResponse;
import com.example.maverikapp.pojo_response.posts.DisplayPostResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FutureEventsFragment extends Fragment {

    private View feView;
    private RecyclerView feRecyclerView;
    private List<DisplayEventsDetailResponse> feEvents = new ArrayList<>();
    Call<DisplayEventsResponse> feCall;


    public FutureEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       feView = inflater.inflate(R.layout.fragment_future_events, container, false);

       loadEventsJson();

        return feView;
    }

    private void loadEventsJson() {

        feCall = RetrofitClient
                .getInstance()
                .getApi()
                .displayEvents("yes");

        feCall.enqueue(new Callback<DisplayEventsResponse>() {
            @Override
            public void onResponse(Call<DisplayEventsResponse> call, Response<DisplayEventsResponse> response) {
                DisplayEventsResponse eventR = response.body();
                if(eventR != null){
                    if(eventR.getResult() == 1){

                        //On successful retrieving of all events
                        Toast.makeText(getContext(),eventR.getMessage(),Toast.LENGTH_SHORT).show();

                        feRecyclerView = feView.findViewById(R.id.fe_recycler_view);
                        Context context;
                        LinearLayoutManager feLayoutManager = new LinearLayoutManager(getContext());
                        feRecyclerView.setLayoutManager(feLayoutManager);
                        feRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        feRecyclerView.setNestedScrollingEnabled(false);
                        feEvents = eventR.getEvents();
                        EventsAdapter feAdapter = new EventsAdapter(feEvents,getContext());
                        feRecyclerView.setAdapter(feAdapter);
                        feAdapter.notifyDataSetChanged();


                    }else{
                        Toast.makeText(getContext(), "Error : "+eventR.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Error : "+response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DisplayEventsResponse> call, Throwable t) {

            }
        });

    }

}
