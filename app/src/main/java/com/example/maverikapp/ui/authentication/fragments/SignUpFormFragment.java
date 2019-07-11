package com.example.maverikapp.ui.authentication.fragments;


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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.data_models.DefaultResponse;
import com.example.maverikapp.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFormFragment extends Fragment {

    private View view;
    private String[] suRoles= {"captain","vice Captain","team member","accountant" };
    private String suUsername,suEmail,suPassword,suRole,suGender,suYear,suCollege;
    private Button suButton;
    private Spinner suSpinner;
    private RadioGroup suGenderGroup,suYearGroup;
    private RadioButton suRadioButtonG,suRadioButtonY;
    private EditText suNameEdit,suPasswordEdit,suEmailEdit,suCollegeEdit;


    public SignUpFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_form, container, false);

         suSpinner = (Spinner) view.findViewById(R.id.su_spinner);

         suEmailEdit = (EditText) view.findViewById(R.id.su_email);
         suNameEdit = (EditText) view.findViewById(R.id.su_username);
         suPasswordEdit = (EditText) view.findViewById(R.id.su_password);
         suCollegeEdit = (EditText) view.findViewById(R.id.su_college);

        suGenderGroup = (RadioGroup)view.findViewById(R.id.su_gender_group);
        suYearGroup = (RadioGroup)view.findViewById(R.id.su_year_group);

        //Creating Array Adapter for the roles in the maverick.
        ArrayAdapter suAdapter = new ArrayAdapter(getContext(),R.layout.spinner_layout,suRoles);
        suAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        suSpinner.setAdapter(suAdapter);

        suSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                suRole = suRoles[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Please select your assigned role", Toast.LENGTH_SHORT).show();
            }
        });


        view.findViewById(R.id.su_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suUsername = suNameEdit.getText().toString().trim();
                        suPassword = suPasswordEdit.getText().toString().trim();
                        suEmail = suEmailEdit.getText().toString().trim();
                        suCollege = suCollegeEdit.getText().toString().trim();

                        suRadioButtonG = (RadioButton)view.findViewById(suGenderGroup.getCheckedRadioButtonId());
                        suGender = suRadioButtonG.getText().toString().trim();

                        suRadioButtonY = (RadioButton)view.findViewById(suYearGroup.getCheckedRadioButtonId());
                        suYear = suRadioButtonY.getText().toString().trim();

                        if(TextUtils.isEmpty(suEmail) || TextUtils.isEmpty(suPassword) || TextUtils.isEmpty(suUsername)||TextUtils.isEmpty(suCollege)){
                            Toast.makeText(getContext(), "please fill the following fields", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (suPassword.length() < 6){
                            Toast.makeText(getContext(), "password length minimum 8 characters", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(suYear.isEmpty() || suGender.isEmpty()){
                            Toast.makeText(getContext(), "please fill the following fields", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Call<DefaultResponse> call = RetrofitClient
                                .getInstance()
                                .getApi()
                                .createUser(suEmail,suPassword,suUsername,suCollege,suYear,suRole,suGender);

                        call.enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                                if(response.code() == 201){
                                    DefaultResponse dr = response.body();
                                    Toast.makeText(getContext(), dr.getMessage(), Toast.LENGTH_SHORT).show();
                                }else if(response.code() == 422){
                                    Toast.makeText(getContext(),"User already Exists",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            }
                        });
                    }
                });

        // Inflate the layout for this fragment
        return view;
    }

}
