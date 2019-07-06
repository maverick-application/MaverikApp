package com.example.maverikapp;


import android.content.Context;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFormFragment extends Fragment {

    private String[] suRoles= {"captain","vice Captain","team member","accountant" };
    private EditText suUsernameEdit,suPasswordEdit,suEmailEdit;
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

        suEmailEdit = (EditText)view.findViewById(R.id.su_email);
        suUsernameEdit = (EditText)view.findViewById(R.id.su_username);
        suPasswordEdit = (EditText)view.findViewById(R.id.su_password);

        suUsername = suUsernameEdit.getText().toString().trim();
        suPassword = suPasswordEdit.getText().toString().trim();
        suEmail = suEmailEdit.getText().toString().trim();


        //Creating Array Adapter for the roles in the maverick.
        ArrayAdapter suAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,suRoles);
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
                        if(TextUtils.isEmpty(suEmail) || TextUtils.isEmpty(suPassword) || TextUtils.isEmpty(suUsername)){
                            Toast.makeText(getContext(), "please fill the following fields", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (suPassword.length() < 6){
                            Toast.makeText(getContext(), "password length minimum 8 characters", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });




        // Inflate the layout for this fragment
        return view;
    }

}
