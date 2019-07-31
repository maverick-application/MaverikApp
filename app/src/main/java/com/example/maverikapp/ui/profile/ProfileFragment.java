package com.example.maverikapp.ui.profile;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.api.Constants;
import com.example.maverikapp.ui.MainActivity;
import com.example.maverikapp.ui.authentication.StartUp;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private View pfView;
    private SharedPreferences pfPref;

    private TextView pfName,pfEmail;
    private Button pfButton;
    private String pfId;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pfView = inflater.inflate(R.layout.fragment_profile, container, false);

        pfName = (TextView) pfView.findViewById(R.id.pf_name);
        pfEmail = (TextView) pfView.findViewById(R.id.pf_email);

        pfPref = getActivity().getSharedPreferences("Mem",MODE_PRIVATE);
        pfName.setText("Welcome : "+(pfPref.getString(Constants.NAME,"")));
        Toast.makeText(getContext(),pfPref.getString(Constants.NAME,""),Toast.LENGTH_LONG).show();
        pfEmail.setText(pfPref.getString(Constants.EMAIL,""));

        pfView.findViewById(R.id.pf_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pfPref.edit();
                editor.putBoolean(Constants.IS_LOGGED_IN,false);
                editor.putString(Constants.EMAIL,"");
                editor.putString(Constants.NAME,"");
                editor.putString(Constants.UNIQUE_ID,"");
                editor.apply();

                Intent na = new Intent(getActivity(), StartUp.class);
                startActivity(na);

            }
        });


        return pfView;
    }

}
