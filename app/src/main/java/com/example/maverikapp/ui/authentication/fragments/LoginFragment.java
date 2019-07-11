package com.example.maverikapp.ui.authentication.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.data_models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private View lfView;
    private EditText lfEditUsername,lfEditPassword;
    private String lfUsername,lfPassword;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        lfView = inflater.inflate(R.layout.fragment_login, container, false);

        lfEditUsername = (EditText) lfView.findViewById(R.id.lf_username);
        lfEditPassword = (EditText) lfView.findViewById(R.id.lf_password);

         lfView.findViewById(R.id.lf_button)
                 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lfUsername = lfEditUsername.getText().toString().trim();
                lfPassword = lfEditPassword.getText().toString().trim();

                Toast.makeText(getContext(), lfUsername+"   "+lfPassword, Toast.LENGTH_SHORT).show();

                if(TextUtils.isEmpty(lfUsername) || TextUtils.isEmpty(lfPassword)){
                    Toast.makeText(getContext(), "please enter the following fields", Toast.LENGTH_SHORT).show();
                }

                Call<LoginResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .userLogin(lfUsername,lfPassword);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();

                        if(!loginResponse.isError()){
                            Toast.makeText(getContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("err",t.getMessage());
                    }
                });
            }
        });
        return lfView;
    }

}
