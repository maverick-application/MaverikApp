package com.example.maverikapp.ui.home;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maverikapp.R;
import com.example.maverikapp.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullViewPostFragment extends Fragment {

    private String fvpTitle,fvpDesc,fvpImg,fvpLike,fvpTime,fvpUserName,fvpUserImg;
    private TextView fvpTextTitle,fvpTextDesc,fvpTextTime,fvpTextUserName;
    private ImageView fvpUserImage;
    private FloatingActionButton fvpDeleteButton;
    private View fvpView;
    private SharedPreferences fvpSharedPref;
    public FullViewPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fvpView = inflater.inflate(R.layout.fragment_full_view_post, container, false);

        fvpSharedPref = getActivity().getSharedPreferences(Constants.POST_DETAILS, Context.MODE_PRIVATE);

        //Getting the posts details from the shared preferences
        fvpTitle = fvpSharedPref.getString(Constants.TITLE,"Title");
        fvpDesc = fvpSharedPref.getString(Constants.DESC,"Desc");
        fvpTime = fvpSharedPref.getString(Constants.TIME,"Time");
        fvpUserName = fvpSharedPref.getString(Constants.COLLEGE_NAME,"mavericks");
        fvpUserImg = fvpSharedPref.getString(Constants.COLLEGE_IMG,"null");


        fvpTextTitle = (TextView)fvpView.findViewById(R.id.fvp_title);
        fvpTextDesc = (TextView) fvpView.findViewById(R.id.fvp_desc);
        fvpTextTime = (TextView)fvpView.findViewById(R.id.fvp_time);
        fvpTextUserName = (TextView)fvpView.findViewById(R.id.fvp_user);
        fvpUserImage = (ImageView)fvpView.findViewById(R.id.fvp_user_img);


        fvpTextTitle.setText(fvpTitle);
        fvpTextDesc.setText(fvpDesc);
        fvpTextUserName.setText(fvpUserName);
        fvpTextTime.setText(fvpTime);

        Glide.with(getActivity()).load(fvpUserImg).into(fvpUserImage);

        fvpDeleteButton = (FloatingActionButton)fvpView.findViewById(R.id.fvp_delete_button);
        fvpDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(fvpView).navigate(R.id.editPostFragment);
            }
        });
        return fvpView;
    }

}
