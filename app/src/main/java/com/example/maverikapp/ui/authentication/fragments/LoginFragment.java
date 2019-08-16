package com.example.maverikapp.ui.authentication.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.api.Constants;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.data_models.AuthenticationServerRequest;
import com.example.maverikapp.data_models.User;
import com.example.maverikapp.pojo_response.AuthenticationServerResponse;
import com.example.maverikapp.ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {

    private LinearLayout lfLinearLayout;
    private EditText lfEditUsername,lfEditPassword;
    private View lfView;
    private ProgressBar lfProgressBar;
    private SharedPreferences lfPref;
    private String format = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";


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
                        lfLinearLayout.setClickable(false);
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

                if(resp.getResult().equals(Constants.SUCCESS)){
                    Toast.makeText(getContext(),"Welcome : "+resp.getUser().getName(),Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = lfPref.edit();
                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
                    editor.putString(Constants.EMAIL,resp.getUser().getEmail());
                    editor.putString(Constants.NAME,resp.getUser().getName());
                    editor.putString(Constants.UNIQUE_ID,resp.getUser().getUnique_id());
                    editor.apply();
                    lfProgressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }else{

                    Toast.makeText(getContext(), "Wrong Details", Toast.LENGTH_SHORT).show();
                    lfLinearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<AuthenticationServerResponse> call, Throwable t) {

                Log.d("Maverik","failed");
                Toast.makeText(getContext(), "process failed", Toast.LENGTH_SHORT).show();
                lfProgressBar.setVisibility(View.GONE);
            }
        });
    }

}
