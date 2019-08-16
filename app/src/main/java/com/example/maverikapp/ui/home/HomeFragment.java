package com.example.maverikapp.ui.home;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.maverikapp.R;
import com.example.maverikapp.adapter.FeedsAdapter;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.data_models.DisplayPost;
import com.example.maverikapp.data_models.DisplayPostDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private View hfView;
    private RecyclerView hfRecyclerView;
    private RecyclerView.LayoutManager hfLayoutManager;
    private List<DisplayPostDetails> hfPosts = new ArrayList<>();
    private FeedsAdapter hfAdapter;
    private ScrollView hfScrollView;
    private RelativeLayout hfRelativeLayout;
    private LottieAnimationView networkError;


    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hfView = inflater.inflate(R.layout.fragment_home, container, false);

        hfScrollView = (ScrollView)hfView.findViewById(R.id.h_parent_layout);
        hfRelativeLayout = (RelativeLayout)hfView.findViewById(R.id.h_layout_network);

        networkError = hfView.findViewById(R.id.h_network_gif);
        hfView.findViewById(R.id.h_network).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStart();
            }
        });

        hfView.findViewById(R.id.h_create_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String action;
                Intent na = new Intent(getContext(),CreatePost.class);
                startActivity(na);

            }
        });

        return  hfView;
    }

    public void loadJson(){

        final Call<DisplayPost> hfCall = RetrofitClient
                .getInstance()
                .getApi()
                .getPosts();

        hfCall.enqueue(new Callback<DisplayPost>() {
            @Override
            public void onResponse(Call<DisplayPost> call, Response<DisplayPost> response) {
                if(response.isSuccessful() && response.body().getPosts() != null){

                    if(!hfPosts.isEmpty()){
                        hfPosts.clear();
                    }

                    hfRecyclerView = hfView.findViewById(R.id.h_recycler_view);
                    hfLayoutManager = new LinearLayoutManager(getActivity());
                    hfRecyclerView.setLayoutManager(hfLayoutManager);
                    hfRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    hfRecyclerView.setNestedScrollingEnabled(false);
                    hfPosts = response.body().getPosts();
                    hfAdapter = new FeedsAdapter(hfPosts,getContext());
                    hfRecyclerView.setAdapter(hfAdapter);
                    hfAdapter.notifyDataSetChanged();
                    initListener();


                }else {
                    Toast.makeText(getContext(), "No Result", Toast.LENGTH_SHORT).show();
                }
            }

            private void initListener(){
                hfAdapter.setOnItemClickListener(new FeedsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent na = new Intent(getContext(),FullViewPost.class);
                        DisplayPostDetails displayPostDetails = hfPosts.get(position);
                        na.putExtra("title",displayPostDetails.getP_name());
                        na.putExtra("desc",displayPostDetails.getP_desc());
                        na.putExtra("img",displayPostDetails.getP_img());
                        na.putExtra("like",displayPostDetails.getP_likes());
                        na.putExtra("time",displayPostDetails.getP_time());
                        na.putExtra("user",displayPostDetails.getP_id());
                        startActivity(na);

                    }
                });
            }
            @Override
            public void onFailure(Call<DisplayPost> call, Throwable t) {
                Toast.makeText(getContext(), "Failed :"+t.getMessage().trim(), Toast.LENGTH_SHORT).show();
                Log.d("Error.",t.getMessage());
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if(isNetworkAvailable()){
            hfScrollView.setVisibility(View.VISIBLE);
            loadJson();
        }else{
            hfRelativeLayout.setVisibility(View.VISIBLE);
            networkError.setProgress(0);
            networkError.playAnimation();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}
