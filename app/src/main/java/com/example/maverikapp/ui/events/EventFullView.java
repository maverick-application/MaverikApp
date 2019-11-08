package com.example.maverikapp.ui.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.pojo_response.events.EventsResponse;
import com.example.maverikapp.utils.Constants;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventFullView extends AppCompatActivity {

    private TextView efvNameView,efvDateView,efvCostView,efvCNameView,efvCLocationView,efvDescView,efvSponsorView,efvTeamView;
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
    private String efvCost;
    private String efvSponsorImg;
    private String efvTeamImg;
    private String efvSponsorName;
    private String efvTeamName;
    private String efvId;
    private Call<EventsResponse> efvCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_full_view);

        efvImageView = (ImageView) findViewById(R.id.efv_img);
        efvNameView = (TextView) findViewById(R.id.efv_event_name);
        efvDateView = (TextView) findViewById(R.id.efv_date);
        efvCostView = (TextView)findViewById(R.id.efv_cost);
        efvCNameView = (TextView) findViewById(R.id.efv_college_name);
        efvCLocationView = (TextView) findViewById(R.id.efv_college_address);
        efvImgMaps = (ImageView) findViewById(R.id.efv_maps);
        efvDescView = (TextView) findViewById(R.id.efv_desc);
        efvSponsorView = (TextView) findViewById(R.id.efv_sponsor_name);
        efvSponsor = (CircleImageView) findViewById(R.id.efv_sponsor_img);
        efvTeam = (CircleImageView) findViewById(R.id.efv_team_img);
        efvTeamView = (TextView) findViewById(R.id.efv_team_name);

        findViewById(R.id.efv_event_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventFullView.this,EventDetails.class));
            }
        });

        Intent efvIntent = getIntent();
        Bundle efvBundle = efvIntent.getExtras();

        //Getting the Event details from the shared preferences
       efvId = efvBundle.getString(Constants.E_ID,"123");
       efvName = efvBundle.getString(Constants.E_NAME,"Event Name");
       efvDesc = efvBundle.getString(Constants.E_DESC,"Event Description");
       efvDate = efvBundle.getString(Constants.E_DATE,"Event Date");
       efvCost = efvBundle.getString(Constants.E_COST,"Event Cost");
       efvImg = efvBundle.getString(Constants.E_IMG_LINK,"Event Img");
       efvCollegeName = efvBundle.getString(Constants.E_COLLEGE_NAME,"College Name");
       efvCollegeLoc = efvBundle.getString(Constants.E_COLLEGE_LOC,"College Loc");
       efvCollegeImg = efvBundle.getString(Constants.E_COLLEGE_IMG,"College Img");
       efvCollegeAdd = efvBundle.getString(Constants.E_COLLEGE_ADD,"College Add");
       efvSponsorName = efvBundle.getString(Constants.E_SPONSOR_NAME,"Sponsor Name");
       efvSponsorImg = efvBundle.getString(Constants.E_SPONSOR_IMG,"Sponsor Img");


       efvNameView.setText(efvName);
       efvDescView.setText(efvDesc);
       efvCostView.setText("Cost : ₹"+efvCost);
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
       efvSponsor.setOnClickListener(new View.OnClickListener() {
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

       //Floating Button for the deletion of Events

        findViewById(R.id.evf_delete_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                efvCall = RetrofitClient
                        .getInstance()
                        .getApi()
                        .deleteEvent(efvId);
                efvCall.enqueue(new Callback<EventsResponse>() {
                    @Override
                    public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                        if(response != null){
                            if(response.isSuccessful()){
                                Toast.makeText(EventFullView.this, "Event Deleted !"+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(EventFullView.this, "Not Deleted "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(EventFullView.this, "Error in Network !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EventsResponse> call, Throwable t) {
                        Toast.makeText(EventFullView.this, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
