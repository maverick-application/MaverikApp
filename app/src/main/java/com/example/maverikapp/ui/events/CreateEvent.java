package com.example.maverikapp.ui.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.pojo_response.events.EventsResponse;
import com.example.maverikapp.ui.MainActivity;
import com.example.maverikapp.utils.Constants;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEvent extends AppCompatActivity {

    private EditText ceNameE,ceDescE,ceCapacityE,ceBudgetE,ceSponsorNameE,ceSponsorLinkE;
    private ImageView ceImgViewN,ceImgViewL,ceImgViewS;
    private String ceName,ceDesc,ceCapacity,ceBudget,ceSponsorName,ceSponsorLink,ceImgN,ceImgL,ceImgS;

    private Call<EventsResponse> ceCall;

    private Uri postImageUri;
    private byte[] ceImgData;

    private int pos;
    private SharedPreferences ceSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        ceNameE =(EditText) findViewById(R.id.ce_name);
        ceDescE = (EditText)findViewById(R.id.ce_desc);
        ceCapacityE = (EditText)findViewById(R.id.ce_total_no);
        ceBudgetE = (EditText)findViewById(R.id.ce_budget);
        ceSponsorNameE = (EditText)findViewById(R.id.ce_sponsor_name);
        ceSponsorLinkE = (EditText)findViewById(R.id.ce_sponsor_link);

        ceImgViewN = (ImageView)findViewById(R.id.ce_img_n);
        ceImgViewL = (ImageView)findViewById(R.id.ce_img_l);
        ceImgViewS = (ImageView)findViewById(R.id.ce_img_s);

        ceSharedPref = CreateEvent.this.getSharedPreferences(Constants.USER_DETAILS,MODE_PRIVATE);

        ceImgViewN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(CreateEvent.this);

                pos = 2;
            }
        });

        ceImgViewL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(CreateEvent.this);

                pos = 3;
            }
        });
        ceImgViewS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(CreateEvent.this);

                pos = 4;
            }
        });

        findViewById(R.id.ce_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ceName = ceNameE.getText().toString();
                ceDesc = ceDescE.getText().toString();
                ceBudget = ceBudgetE.getText().toString();
                ceCapacity = ceCapacityE.getText().toString();
                ceSponsorLink = ceSponsorLinkE.getText().toString();
                ceSponsorName = ceSponsorNameE.getText().toString();

                if(ceName.isEmpty() && ceDesc.isEmpty() && ceBudget.isEmpty() && ceCapacity.isEmpty() && ceSponsorName.isEmpty() && ceSponsorLink.isEmpty()){
                    Toast.makeText(CreateEvent.this,"Fields are empty !",Toast.LENGTH_SHORT).show();
                }else if(ceImgL.isEmpty() && ceImgN.isEmpty() && ceImgS.isEmpty()){
                    Toast.makeText(CreateEvent.this, "Please their is error in uploading images , try again !", Toast.LENGTH_SHORT).show();
                }else{

                    ceCall = RetrofitClient
                            .getInstance()
                            .getApi()
                            .createEvent(ceName,ceDesc,ceSharedPref.getString(Constants.COLLEGE,""),ceBudget,ceCapacity,"yes",ceImgL,ceImgN,ceSponsorName,ceImgS,ceSponsorLink);

                    ceCall.enqueue(new Callback<EventsResponse>() {
                        @Override
                        public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                            EventsResponse eventsResp = response.body();
                            if(eventsResp != null){
                                if(eventsResp.getResult() == 1){
                                    Toast.makeText(CreateEvent.this,"Event Created Successfully !",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(CreateEvent.this, MainActivity.class));
                                }else{
                                    Toast.makeText(CreateEvent.this, eventsResp.getMessage()+" "+eventsResp.getResult(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(CreateEvent.this,"Error :"+response.errorBody(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<EventsResponse> call, Throwable t) {
                            Toast.makeText(CreateEvent.this,"Error : "+t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                postImageUri = result.getUri();
                if (postImageUri.toString().isEmpty()) {

                    Toast.makeText(this, "Please insert image !", Toast.LENGTH_SHORT).show();

                } else {


                    File actucalImage = new File(result.getUri().getPath());

                    try {
                        Bitmap compressedImage = new Compressor(CreateEvent.this)
                                .setQuality(100)
                                .compressToBitmap(actucalImage);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        compressedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        ceImgData = baos.toByteArray();
                        String imgCode = Base64.encodeToString(ceImgData, Base64.DEFAULT);
                        setImageView(postImageUri,imgCode);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
                Toast.makeText(this, "Failure.....!", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private void setImageView(Uri postImageUri,String imgCode) {
       if(pos == 2){
           ceImgViewN.setImageURI(postImageUri);
           ceImgN = imgCode;
       }else if(pos == 3){
           ceImgViewL.setImageURI(postImageUri);
           ceImgL = imgCode;
       }else{
           ceImgViewS.setImageURI(postImageUri);
           ceImgS = imgCode;
       }
    }
}
