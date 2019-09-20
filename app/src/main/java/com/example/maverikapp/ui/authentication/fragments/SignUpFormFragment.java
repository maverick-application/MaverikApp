package com.example.maverikapp.ui.authentication.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.pojo_response.auth.AuthenticationResponse;
import com.example.maverikapp.ui.MainActivity;
import com.example.maverikapp.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFormFragment extends Fragment {

    private View view;
    private SharedPreferences suPref;

    private String[] suRoles = {"council","committee","regency"};
    private String[] suYears = {"1st year","2nd year","3rd year","4th year"};

    private String[] suRolesCouncil= {"maverick regency officer","chief marketing officer","chief operating officer","chief finance officer","chief creative officer","chief business officer"};
    private String[] suRolesCommittee = {"talent operation executive","program operation executive","digital growth","information design executive"};
    private String[] suRolesRegency = {"crew captain","crew v.captain finance","crew v.captain talent operation","crew v.captain logistics","crew member"};

    private String suName,suEmail,suPassword,suRole,suLevel,suGender,suYear,suCollege,suDob;
    private Spinner suSpinnerYear,suSpinnerRole,suSpinnerLevel;
    private RadioGroup suGenderGroup;
    private RadioButton suRadioButtonG;
    private EditText suNameEdit,suPasswordEdit,suEmailEdit,suCollegeEdit,suDobEdit;
    private ProgressBar suProgressBar;
    private ScrollView suSrollView;
    private Call<AuthenticationResponse> suCall;
    private SharedPreferences suShared;
    private String suRoleS;
    private Calendar suCalendar;


    public SignUpFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_form, container, false);

        suShared = getActivity().getSharedPreferences(Constants.USER_DETAILS,MODE_PRIVATE);


        suSpinnerLevel = (Spinner)view.findViewById(R.id.su_level);
        suSpinnerRole = (Spinner)view.findViewById(R.id.su_role);
        suSpinnerYear = (Spinner)view.findViewById(R.id.su_year);

         suEmailEdit = (EditText) view.findViewById(R.id.su_email);
         suNameEdit = (EditText) view.findViewById(R.id.su_username);
         suPasswordEdit = (EditText) view.findViewById(R.id.su_password);
         suCollegeEdit = (EditText) view.findViewById(R.id.su_college);
         suDobEdit = (EditText)view.findViewById(R.id.su_dob);

        suGenderGroup = (RadioGroup)view.findViewById(R.id.su_gender_group);

        suProgressBar = (ProgressBar)view.findViewById(R.id.su_progress_bar);

        suSrollView = (ScrollView) view.findViewById(R.id.su_parent_layout);

        //EditText is used to get the date from user
         suCalendar= Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                suCalendar.set(Calendar.YEAR, year);
                suCalendar.set(Calendar.MONTH, monthOfYear);
                suCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        suDobEdit.setInputType(InputType.TYPE_NULL);
        suDobEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, suCalendar
                        .get(Calendar.YEAR), suCalendar.get(Calendar.MONTH),
                        suCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Spinner is used for the year for B.Tech
        ArrayAdapter suYearAdap = new ArrayAdapter(getContext(),R.layout.spinner_layout,suYears);
        suYearAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        suSpinnerYear.setAdapter(suYearAdap);

        suSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                suYear = suYears[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "please select current year studying", Toast.LENGTH_SHORT).show();
            }
        });


        //Spinner is used for the roles in the maverick
        ArrayAdapter suRoleAdap = new ArrayAdapter(getContext(),R.layout.spinner_layout,suRoles);
        suRoleAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        suSpinnerLevel.setAdapter(suRoleAdap);

        suSpinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                suRole = suRoles[position];

                if(position == 0){
                    suLevel = levelSpinner(suRolesCommittee);
                }else if(position == 1){
                    suLevel = levelSpinner(suRolesCouncil);
                }else{
                    suLevel = levelSpinner(suRolesRegency);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "please select your assigned year ", Toast.LENGTH_SHORT).show();
            }
        });




        view.findViewById(R.id.su_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        suProgressBar.setVisibility(View.VISIBLE);
                        suSrollView.setClickable(false);

                        suName = suNameEdit.getText().toString().trim();
                        suPassword = suPasswordEdit.getText().toString().trim();
                        suEmail = suEmailEdit.getText().toString().trim();
                        suCollege = suCollegeEdit.getText().toString().trim();


                        try{

                            suRadioButtonG = (RadioButton)view.findViewById(suGenderGroup.getCheckedRadioButtonId());
                            suGender = suRadioButtonG.getText().toString().trim();

                        }catch (NullPointerException e){
                            Toast.makeText(getContext(),"Please select the an option",Toast.LENGTH_SHORT).show();
                        }

                        if(TextUtils.isEmpty(suEmail) || TextUtils.isEmpty(suPassword) || TextUtils.isEmpty(suName)||TextUtils.isEmpty(suCollege)){
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

                        registerProcess(suName,suEmail,suPassword,suGender,suCollege,suRole,suYear,suDob,suLevel);
                    }
                });

        // Inflate the layout for this fragment
        return view;
    }




    private void registerProcess(final String name, final String email, String password, String gender, String college, String role, String soy,String dob,String level){

        suCall = RetrofitClient
                .getInstance()
                .getApi()
                .userSignUp(name,email,password,gender,dob,soy,college,level,role);

        suCall.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                AuthenticationResponse authResp = response.body();

                if(authResp != null){

                    if(authResp.getResult() == 0){

                        Toast.makeText(getContext(), "User Created Successfully", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor store = suShared.edit();
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
                        Toast.makeText(getContext(),"try again after some time"+authResp.getMessage()+"             "+authResp.getResult(),Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(),"error in network",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Toast.makeText(getContext(),"sorry couldn't create user : "+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Mav",t.getMessage());
            }
        });
    }


    private String levelSpinner(final String[] typeRole) {
        //Spinner is used for the roles in the maverick
        ArrayAdapter suRoleAdap = new ArrayAdapter(getContext(),R.layout.spinner_layout,typeRole);
        suRoleAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        suSpinnerRole.setAdapter(suRoleAdap);
        suSpinnerRole.setVisibility(View.VISIBLE);
        suSpinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                suRoleS = typeRole[i];
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                suRoleS = null;
            }
        });

        return  suRoleS;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        suDobEdit.setText(sdf.format(suCalendar.getTime()));
        suDob = sdf.format(suCalendar.getTime());
    }
}
