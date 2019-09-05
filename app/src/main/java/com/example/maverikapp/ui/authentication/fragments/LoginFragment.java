package com.example.maverikapp.ui.authentication.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.utils.Constants;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.pojo_response.auth.UserDetailsResponse;
import com.example.maverikapp.pojo_response.auth.AuthenticationResponse;
import com.example.maverikapp.ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {

    private LinearLayout lfLinearLayout;
    private EditText lfEditUsername,lfEditPassword;
    private View lfView;
    private ProgressBar lfProgressBar;
    private SharedPreferences lfPref;
    private Call<AuthenticationResponse> lfCall;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lfView = inflater.inflate(R.layout.fragment_login, container, false);

        lfPref = getActivity().getSharedPreferences(Constants.USER_DETAILS,MODE_PRIVATE);

        lfEditUsername = (EditText)lfView.findViewById(R.id.lf_username);
        lfEditPassword = (EditText)lfView.findViewById(R.id.lf_password);
        lfProgressBar = lfView.findViewById(R.id.lf_progress_bar);
        lfLinearLayout = (LinearLayout)lfView.findViewById(R.id.lf_parent_layout);

        lfView.findViewById(R.id.lf_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String lfUsername = lfEditUsername.getText().toString().trim();
                        String lfPassword = lfEditPassword.getText().toString().trim();
                        if(lfUsername.isEmpty() || lfPassword.isEmpty()){
                            Toast.makeText(getContext(), "please enter the details", Toast.LENGTH_SHORT).show();
                        }else if(lfUsername.length() < 8){
                            Toast.makeText(getContext(), "password must contain 8 letters", Toast.LENGTH_SHORT).show();
                        }
                        lfProgressBar.setVisibility(View.VISIBLE);
                        lfLinearLayout.setVisibility(View.GONE);
                        loginProcessWithRetrofit(lfUsername,lfPassword);
                    }

                });

        return lfView;

    }


    private void loginProcessWithRetrofit(final String email, String password){

        lfCall = RetrofitClient
        .getInstance()
        .getApi()
        .userLogin(email,password);

        lfCall.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                AuthenticationResponse authResp = response.body();

                if(authResp != null){
                    if(authResp.getResult() == 1){

                        Toast.makeText(getContext(), "login successful", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor store = lfPref.edit();
                        store.putBoolean(Constants.IS_LOGGED_IN,true);
                        store.putString(Constants.EMAIL,authResp.getUserDetailsResponse().getEmail());
                        store.putString(Constants.NAME,authResp.getUserDetailsResponse().getName());
                        store.putString(Constants.USER_ID,authResp.getUserDetailsResponse().getMember_id());
                        store.putString(Constants.LEVEL,authResp.getUserDetailsResponse().getLevel());
                        store.putString(Constants.ROLE,authResp.getUserDetailsResponse().getRole());
                        store.putString(Constants.DOB,authResp.getUserDetailsResponse().getDob());
                        store.apply();
                        Intent su = new Intent(getActivity(), MainActivity.class);
                        startActivity(su);

                    }else{
                        Toast.makeText(getContext(),"try again after some time"+authResp.getResult()+"      "+authResp.getMessage(),Toast.LENGTH_LONG).show();
                        lfProgressBar.setVisibility(View.GONE);
                        lfLinearLayout.setVisibility(View.VISIBLE);
                    }
                }else{
                    Toast.makeText(getContext(),"error in network",Toast.LENGTH_LONG).show();
                    lfProgressBar.setVisibility(View.GONE);
                    lfLinearLayout.setVisibility(View.VISIBLE);
                }

                    }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Error Occured : "+t.getMessage(),Toast.LENGTH_LONG).show();
                lfProgressBar.setVisibility(View.GONE);
                lfLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }

}
