package com.example.maverikapp.ui.authentication.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.api.Constants;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.data_models.AuthenticationServerRequest;
import com.example.maverikapp.data_models.AuthenticationServerResponse;
import com.example.maverikapp.data_models.User;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginFragment extends Fragment {

    private EditText lfEditUsername,lfEditPassword;
    private View lfView;


    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lfView = inflater.inflate(R.layout.fragment_login, container, false);

        lfEditUsername = (EditText)lfView.findViewById(R.id.lf_username);
        lfEditPassword = (EditText)lfView.findViewById(R.id.lf_password);

        lfView.findViewById(R.id.lf_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String lfUsername = lfEditUsername.getText().toString().trim();
                        String lfPassword = lfEditPassword.getText().toString().trim();
                        Toast.makeText(getContext(), lfUsername+"  "+lfPassword, Toast.LENGTH_SHORT).show();
                        loginProcessWithRetrofit(lfUsername,lfPassword);

                    }

                });


        return lfView;

    }



    private void loginProcessWithRetrofit(final String email, String password){

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        AuthenticationServerRequest request = new AuthenticationServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);

        Call<AuthenticationServerResponse> response = RetrofitClient
                .getInstance()
                .getApi()
                .operation(request);

        response.enqueue(new Callback<AuthenticationServerResponse>() {
            @Override
            public void onResponse(Call<AuthenticationServerResponse> call, retrofit2.Response<AuthenticationServerResponse> response) {

                AuthenticationServerResponse resp = response.body();
                Toast.makeText(getContext(),resp.getResult()+"    "+resp.getMessage(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<AuthenticationServerResponse> call, Throwable t) {

                Log.d("Maverik","failed");

            }
        });
    }

}
