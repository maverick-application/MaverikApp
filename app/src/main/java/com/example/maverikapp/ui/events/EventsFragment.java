package com.example.maverikapp.ui.events;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maverikapp.R;
import com.example.maverikapp.adapter.EventsTabsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class EventsFragment extends Fragment {

    private TabLayout efTabLayout;
    private ViewPager efViewPager;
    private View efView;
    private EventsTabsAdapter efTabAdapter;
    private View feView;
    private FloatingActionButton feButton;



    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        efView =  inflater.inflate(R.layout.fragment_events, container, false);

        efViewPager = (ViewPager)efView.findViewById(R.id.efViewPager);
        efTabLayout = (TabLayout)efView.findViewById(R.id.efTabLayout);

        efTabAdapter = new EventsTabsAdapter(getChildFragmentManager());
        efTabAdapter.addFragments(new FutureEventsFragment(),"FUTURE");
        efTabAdapter.addFragments(new PastEventsFragment(),"PAST");

        efViewPager.setAdapter(efTabAdapter);
        efTabLayout.setupWithViewPager(efViewPager);

        feButton = (FloatingActionButton)efView.findViewById(R.id.e_create_event);
        feButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),CreateEvent.class));
            }
        });




        return efView;
    }

}
