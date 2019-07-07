package com.example.maverikapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFormFragment extends Fragment {

    private String[] suRoles= {"captain","vice Captain","team member","accountant" };
    private String suUsername,suEmail,suPassword;
    private Button suButton;

    public SignUpFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_form, container, false);

        Spinner suSpinner = (Spinner) view.findViewById(R.id.su_spinner);

        EditText suEmailEdit = (EditText) view.findViewById(R.id.su_email);
        EditText suUsernameEdit = (EditText) view.findViewById(R.id.su_username);
        EditText suPasswordEdit = (EditText) view.findViewById(R.id.su_password);

        suUsername = suUsernameEdit.getText().toString().trim();
        suPassword = suPasswordEdit.getText().toString().trim();
        suEmail = suEmailEdit.getText().toString().trim();


        //Creating Array Adapter for the roles in the maverick.
        ArrayAdapter suAdapter = new ArrayAdapter(getContext(),R.layout.spinner_layout,suRoles);
        suAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        suSpinner.setAdapter(suAdapter);

        suSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        view.findViewById(R.id.su_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if(TextUtils.isEmpty(suEmail) || TextUtils.isEmpty(suPassword) || TextUtils.isEmpty(suUsername)){
//                            Toast.makeText(getContext(), "please fill the following fields", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        if (suPassword.length() < 6){
//                            Toast.makeText(getContext(), "password length minimum 8 characters", Toast.LENGTH_SHORT).show();
//                            return;
//                        }

                        Call<ResponseBody> call = RetrofitClient
                                .getInstance()
                                .getApi()
                                .createUser("pandfdjfiofsd","fsdfsdfsd","fsdfsdfsd","IARE");
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(getContext(), "Successful SignUp"+response.toString().trim(), Toast.LENGTH_LONG).show();
                                String action;
                                Intent na = new Intent(getContext(),MainActivity.class);
                                startActivity(na);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                Toast.makeText(getContext(), "Fail SignUp"+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });




        // Inflate the layout for this fragment
        return view;
    }

}
