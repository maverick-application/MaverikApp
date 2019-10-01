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
import com.example.maverikapp.pojo_response.posts.DisplayPostDetailsResponse;
import com.example.maverikapp.ui.home.FullPostView;
import com.example.maverikapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutureEventsFragment extends Fragment {

    private View feView;
    private RecyclerView feRecyclerView;
    private List<DisplayEventsDetailResponse> feEvents = new ArrayList<>();
    Call<DisplayEventsResponse> feCall;
    private EventsAdapter feAdapter;


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
                        LinearLayoutManager feLayoutManager = new LinearLayoutManager(getContext());
                        feRecyclerView.setLayoutManager(feLayoutManager);
                        feRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        feRecyclerView.setNestedScrollingEnabled(false);
                        feEvents = eventR.getEvents();
                        feAdapter = new EventsAdapter(feEvents,getContext());
                        feRecyclerView.setAdapter(feAdapter);
                        feAdapter.notifyDataSetChanged();
                        eventClickListener();


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

    private void eventClickListener() {
        feAdapter.setOnItemClickListener(new EventsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                DisplayEventsDetailResponse eResp = feEvents.get(position);
                Intent fe = new Intent(getActivity(),EventFullView.class);
                Toast.makeText(getContext(), "Full View Post", Toast.LENGTH_SHORT).show();
                fe.putExtra(Constants.E_ID,eResp.getE_id());
                fe.putExtra(Constants.E_NAME,eResp.getE_name());
                fe.putExtra(Constants.E_DESC,eResp.getE_desc());
                fe.putExtra(Constants.E_IMG_LINK,eResp.getE_img_n());
                fe.putExtra(Constants.E_COLLEGE_NAME,eResp.getE_college().getCollege_name());
                fe.putExtra(Constants.E_COLLEGE_IMG,eResp.getE_college().getCollege_img());
                fe.putExtra(Constants.E_COLLEGE_LOC,eResp.getE_college().getCollege_link());
                fe.putExtra(Constants.E_COLLEGE_ADD,eResp.getE_college().getCollege_address());
                fe.putExtra(Constants.E_SPONSOR_NAME,eResp.getE_sponsor_name());
                fe.putExtra(Constants.E_SPONSOR_IMG,eResp.getE_sponsor_img());
                fe.putExtra(Constants.E_SPONSOR_LINK,eResp.getE_sponsor_link());
                startActivity(fe);

            }
        });
    }

}
