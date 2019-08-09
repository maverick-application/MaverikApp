package com.example.maverikapp.ui.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.adapter.FeedsAdapter;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.data_models.Post;
import com.example.maverikapp.data_models.PostDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private View hfView;
    private RecyclerView hfRecyclerView;
    private RecyclerView.LayoutManager hfLayoutManager;
    private List<PostDetails> hfPosts = new ArrayList<>();
    private FeedsAdapter hfAdapter;


    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hfView = inflater.inflate(R.layout.fragment_home, container, false);

        hfRecyclerView = hfView.findViewById(R.id.h_recycler_view);
        hfLayoutManager = new LinearLayoutManager(getContext());
        hfRecyclerView.setLayoutManager(hfLayoutManager);
        hfRecyclerView.setItemAnimator(new DefaultItemAnimator());
        hfRecyclerView.setNestedScrollingEnabled(false);

        loadJson();
        return  hfView;
    }

    public void loadJson(){

        final Call<Post> hfCall = RetrofitClient
                .getInstance()
                .getApi()
                .getPosts();

        hfCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful() && response.body().getPosts() != null){

                    if(!hfPosts.isEmpty()){
                        hfPosts.clear();
                    }

                    hfPosts = response.body().getPosts();
                    hfAdapter = new FeedsAdapter(hfPosts,getContext());
                    hfRecyclerView.setAdapter(hfAdapter);
                    hfAdapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(getContext(), "No Result", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getContext(), "Failed :"+t.getMessage().trim(), Toast.LENGTH_SHORT).show();
                Log.d("Error.",t.getMessage());
            }
        });

    }

}
