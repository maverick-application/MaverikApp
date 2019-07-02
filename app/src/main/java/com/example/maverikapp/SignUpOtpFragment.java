package com.example.maverikapp;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpOtpFragment extends Fragment {

    private View view;
    private Button b1,b2;
    private CardView cardViewS,cardViewF;

    public SignUpOtpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_otp, container, false);

        cardViewS = (CardView)view.findViewById(R.id.cardViews);
        cardViewF = (CardView)view.findViewById(R.id.cardViewf);

        b1 = (Button) view.findViewById(R.id.buttons);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewS.setVisibility(View.GONE);
                cardViewF.setVisibility(View.VISIBLE);
            }
        });
        b2 = (Button) view.findViewById(R.id.buttonf);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.signUpFormFragment);
            }
        });
        return  view;
    }

}
