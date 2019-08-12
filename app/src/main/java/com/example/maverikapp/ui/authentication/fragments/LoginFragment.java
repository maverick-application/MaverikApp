package com.example.maverikapp.ui.authentication.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.maverikapp.R;
import com.example.maverikapp.ui.MainActivity;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {

    private EditText lfEditUsername,lfEditPassword;
    private View lfView;
    private SharedPreferences lfPref;


    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lfView = inflater.inflate(R.layout.fragment_login, container, false);

        lfPref = getActivity().getSharedPreferences("Mem",MODE_PRIVATE);

        lfEditUsername = (EditText)lfView.findViewById(R.id.lf_username);
        lfEditPassword = (EditText)lfView.findViewById(R.id.lf_password);

        lfView.findViewById(R.id.lf_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String lfUsername = lfEditUsername.getText().toString().trim();
                        String lfPassword = lfEditPassword.getText().toString().trim();
                        loginProcessWithRetrofit(lfUsername,lfPassword);
                    }

                });

        return lfView;

    }


    private void loginProcessWithRetrofit(final String email, String password){

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);


//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//
//        AuthenticationServerRequest request = new AuthenticationServerRequest();
//        request.setOperation(Constants.LOGIN_OPERATION);
//        request.setUser(user);
//
//        Call<AuthenticationServerResponse> response = RetrofitClient
//                .getInstance()
//                .getApi()
//                .operation(request);
//
//        response.enqueue(new Callback<AuthenticationServerResponse>() {
//            @Override
//            public void onResponse(Call<AuthenticationServerResponse> call, retrofit2.Response<AuthenticationServerResponse> response) {
//
//                AuthenticationServerResponse resp = response.body();
//
//                if(resp.getResult().equals(Constants.SUCCESS)){
//                    Toast.makeText(getContext(),"Message : "+resp.getUser().getEmail()+resp.getUser().getName(),Toast.LENGTH_LONG).show();
//
//                    SharedPreferences.Editor editor = lfPref.edit();
//                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
//                    editor.putString(Constants.EMAIL,resp.getUser().getEmail());
//                    editor.putString(Constants.NAME,resp.getUser().getName());
//                    editor.putString(Constants.UNIQUE_ID,resp.getUser().getUnique_id());
//                    editor.apply();
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AuthenticationServerResponse> call, Throwable t) {
//
//                Log.d("Maverik","failed");
//            }
//        });
    }

}
