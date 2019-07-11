package com.example.maverikapp.ui.authentication.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.maverikapp.R;


public class StartUpFragment extends Fragment {

    private View view;
    private Button loginB,signB;

    public StartUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_start_up, container, false);;

        loginB = (Button) view.findViewById(R.id.loginB);
        signB = (Button)view.findViewById(R.id.signupB);

        /*
         On the click of the button we are navigating into SignUp OTP fragment
         */
        signB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.signUpOtpFragment);
            }
        });

        /*
        On the click of the button we are navigation into Login Fragment
         */
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginChoiceFragment);
            }
        });

        return view;
    }

}
