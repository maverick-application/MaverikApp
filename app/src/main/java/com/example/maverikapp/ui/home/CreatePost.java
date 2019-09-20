package com.example.maverikapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.maverikapp.R;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.pojo_response.posts.PostResponse;
import com.example.maverikapp.ui.MainActivity;
import com.example.maverikapp.utils.Constants;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import id.zelory.compressor.Compressor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePost extends AppCompatActivity {

    private TextView cpImageUpload;
    private EditText cpEditTitle, cpEditDesc, cpEditLink;
    private String cpTitle, cpDesc, cpLink, cpImgName, cpImg,cpUserId;
    private ImageView cpImageView;
    private ProgressBar cpProgressBar;
    private SharedPreferences cpSharedPreference;

    private Uri postImageUri;
    private byte[] cpImgData;
    private String cpImgBase64;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        cpEditTitle = (EditText) findViewById(R.id.cf_title);
        cpEditDesc = (EditText) findViewById(R.id.cf_desc);
        cpEditLink = (EditText) findViewById(R.id.cf_links);
        cpImageUpload = (TextView) findViewById(R.id.cf_img_upload);
        cpProgressBar = (ProgressBar) findViewById(R.id.cf_progressBar);

        cpImageView = (ImageView) findViewById(R.id.cf_view_img);

        findViewById(R.id.cf_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setMinCropResultSize(512, 512)
                            .setAspectRatio(1, 1)
                            .start(CreatePost.this);
            }
        });

        findViewById(R.id.cf_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpProgressBar.setVisibility(View.VISIBLE);
                cpSharedPreference = getApplicationContext().getSharedPreferences(Constants.USER_DETAILS,MODE_PRIVATE);
                cpUserId = cpSharedPreference.getString(Constants.USER_ID,"No User Id");
                cpTitle = cpEditTitle.getText().toString();
                cpDesc = cpEditDesc.getText().toString();
                cpLink = cpEditLink.getText().toString();
                cpImgName = UUID.randomUUID().toString();
                cpImg = cpImgBase64;

                final Call<PostResponse> hfCall = RetrofitClient
                        .getInstance()
                        .getApi()
                        .createPost(cpTitle,cpDesc,cpUserId,cpImgName,cpImg);
                hfCall.enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        PostResponse createPostResponse = response.body();
                        if (createPostResponse != null) {
                            if(createPostResponse.getResult()  == 1){

                                cpProgressBar.setVisibility(View.GONE);
                                Toast.makeText(CreatePost.this, createPostResponse.getMessage(),Toast.LENGTH_LONG).show();
                                Intent na = new Intent(CreatePost.this, MainActivity.class);
                                startActivity(na);

                            }else{
                                Toast.makeText(CreatePost.this, "Creating Post Failed", Toast.LENGTH_SHORT).show();
                                cpProgressBar.setVisibility(View.GONE);
                            }
                        }else{
                            Toast.makeText(CreatePost.this, "Response Empty"+response.errorBody()+"   "+ createPostResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Error : ",response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Toast.makeText(CreatePost.this, "Error : "+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        cpProgressBar.setVisibility(View.GONE);
                    }
                });

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

                    cpImageView.setImageURI(postImageUri);
                    cpImageUpload.setVisibility(View.VISIBLE);

                    File actucalImage = new File(result.getUri().getPath());

                    try {
                        Bitmap compressedImage = new Compressor(CreatePost.this)
                                .setQuality(100)
                                .compressToBitmap(actucalImage);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        compressedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        cpImgData = baos.toByteArray();
                        cpImgBase64 = Base64.encodeToString(cpImgData, Base64.DEFAULT);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }
}
