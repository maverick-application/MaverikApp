package com.example.maverikapp.ui.events;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maverikapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FutureEventsFragment extends Fragment {

    private View feView;
    private RecyclerView feRecyclerView;


    public FutureEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       feView = inflater.inflate(R.layout.fragment_future_events, container, false);





        return feView;
    }

}
