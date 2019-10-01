package com.example.maverikapp.ui.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maverikapp.R;
import com.example.maverikapp.utils.Constants;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventFullView extends AppCompatActivity {

    private TextView efvNameView,efvDateView,efvCNameView,efvCLocationView,efvDescView,efvSponsorView,efvTeamView;
    private ImageView efvImageView,efvSImg,efvTImg,efvImgMaps;
    private CircleImageView efvSponsor,efvTeam;
    private String efvImg;
    private String efvName;
    private String efvDate;
    private String efvCollegeName;
    private String efvCollegeImg;
    private String efvCollegeAdd;
    private String efvCollegeLoc;
    private String efvDesc;
    private String efvSponsorImg;
    private String efvTeamImg;
    private String efvSponsorName;
    private String efvTeamName;
    private int efvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_full_view);

        efvImageView = (ImageView) findViewById(R.id.efv_img);
        efvNameView = (TextView) findViewById(R.id.efv_event_name);
        efvDateView = (TextView) findViewById(R.id.efv_date);
        efvCNameView = (TextView) findViewById(R.id.efv_college_name);
        efvCLocationView = (TextView) findViewById(R.id.efv_college_address);
        efvImgMaps = (ImageView) findViewById(R.id.efv_maps);
        efvDescView = (TextView) findViewById(R.id.efv_desc);
        efvSponsorView = (TextView) findViewById(R.id.efv_sponsor_name);
        efvSponsor = (CircleImageView) findViewById(R.id.efv_sponsor_img);
        efvTeam = (CircleImageView) findViewById(R.id.efv_team_img);
        efvTeamView = (TextView) findViewById(R.id.efv_team_name);


        Intent efvIntent = getIntent();
        Bundle efvBundle = efvIntent.getExtras();

        //Getting the Event details from the shared preferences
       efvId = efvBundle.getInt(Constants.E_ID,1);
       efvName = efvBundle.getString(Constants.E_NAME,"Event Name");
       efvDesc = efvBundle.getString(Constants.E_DESC,"Event Description");
       efvImg = efvBundle.getString(Constants.E_IMG_LINK,"Event Img");
       efvCollegeName = efvBundle.getString(Constants.E_COLLEGE_NAME,"College Name");
       efvCollegeLoc = efvBundle.getString(Constants.E_COLLEGE_LOC,"College Loc");
       efvCollegeImg = efvBundle.getString(Constants.E_COLLEGE_IMG,"College Img");
       efvCollegeAdd = efvBundle.getString(Constants.E_COLLEGE_ADD,"College Add");
       efvSponsorName = efvBundle.getString(Constants.E_SPONSOR_NAME,"Sponsor Name");
       efvSponsorImg = efvBundle.getString(Constants.E_SPONSOR_IMG,"Sponsor Img");


       efvNameView.setText(efvName);
       efvDescView.setText(efvDesc);
        Picasso.get()
                .load(efvImg)
                .placeholder(R.drawable.sample_bg)
                .error(R.drawable.ic_no_internet)
                .into(efvImageView);
       efvDateView.setText(efvDate);
       efvCNameView.setText(efvCollegeName);
       efvCLocationView.setText(efvCollegeAdd);
       efvImgMaps.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
       Picasso.get()
               .load(efvSponsorImg)
               .placeholder(R.drawable.ic_user)
               .error(R.drawable.ic_user)
               .into(efvSponsor);
       efvSponsorView.setText(efvSponsorName);
       efvSImg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
       Picasso.get()
               .load(efvCollegeImg)
               .placeholder(R.drawable.ic_user)
               .error(R.drawable.ic_user)
               .into(efvTeam);
       efvTeamView.setText(efvCollegeName);


    }
}
