package com.example.maverikapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maverikapp.R;
import com.example.maverikapp.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FullPostView extends AppCompatActivity {

    private String fvpTitle, fvpDesc, fvpImg, fvpLike, fvpTime, fvpUserName, fvpUserImg;
    private TextView fvpTextTitle, fvpTextDesc, fvpTextTime, fvpTextUserName;
    private ImageView fvpUserImage;
    private FloatingActionButton fvpDeleteButton;
    private View fvpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post_view);

        Intent fvpIntent = getIntent();
        Bundle fvpBundle = fvpIntent.getExtras();
        
        //Getting the posts details from the shared preferences
        fvpTitle = fvpBundle.getString(Constants.P_TITLE, "Title");
        fvpDesc = fvpBundle.getString(Constants.P_DESC, "Desc");
        fvpTime = fvpBundle.getString(Constants.P_TIME, "Time");
        fvpUserName = fvpBundle.getString(Constants.P_COLLEGE_NAME, "mavericks");
        fvpUserImg = fvpBundle.getString(Constants.P_COLLEGE_IMG, "null");


        fvpTextTitle = (TextView)  findViewById(R.id.fvp_title);
        fvpTextDesc = (TextView)  findViewById(R.id.fvp_desc);
        fvpTextTime = (TextView)  findViewById(R.id.fvp_time);
        fvpTextUserName = (TextView)  findViewById(R.id.fvp_user);
        fvpUserImage = (ImageView)  findViewById(R.id.fvp_user_img);


        fvpTextTitle.setText(fvpTitle);
        fvpTextDesc.setText(fvpDesc);
        fvpTextUserName.setText(fvpUserName);
        fvpTextTime.setText(fvpTime);

        Glide.with(FullPostView.this).load(fvpUserImg).into(fvpUserImage);

        fvpDeleteButton = (FloatingActionButton)  findViewById(R.id.fvp_delete_button);
        fvpDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(fvpView).navigate(R.id.editPostFragment);
            }
        });
    }
}
