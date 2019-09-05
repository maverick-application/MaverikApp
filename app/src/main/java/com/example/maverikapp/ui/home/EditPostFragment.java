package com.example.maverikapp.ui.home;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maverikapp.R;
import com.example.maverikapp.api.RetrofitClient;
import com.example.maverikapp.pojo_response.posts.EditPostResponse;
import com.example.maverikapp.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class EditPostFragment extends Fragment {

    private View epView;
    private EditText epEditTitle,epEditDesc;
    private ImageView epImgView;
    private String epTitle,epDesc,epImg,epPostId,epUserId;
    private SharedPreferences epSharedPref;
    private FloatingActionButton epDeleteButton;
    private Uri postImageUri;
    private byte[] epImgData;
    private String epImgBase64;
    private Call<EditPostResponse> epCall;


    public EditPostFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       epView =  inflater.inflate(R.layout.fragment_edit_post, container, false);

       epSharedPref = getActivity().getSharedPreferences(Constants.POST_DETAILS, Context.MODE_PRIVATE);

       epTitle = epSharedPref.getString(Constants.TITLE,"Title");
       epDesc = epSharedPref.getString(Constants.DESC,"Desc");
       epImg = epSharedPref.getString(Constants.IMG,"img");
       epPostId = epSharedPref.getString(Constants.P_ID,"id");
       epUserId = epSharedPref.getString(Constants.P_USER_ID,"user id");

       epEditTitle = (EditText) epView.findViewById(R.id.ep_title);
       epEditDesc = (EditText) epView.findViewById(R.id.ep_desc);
       epImgView = (ImageView) epView.findViewById(R.id.ep_img);

       epEditTitle.setText(epTitle);
       epEditDesc.setText(epDesc);
       Glide.with(epView).load(epImg).into(epImgView);

       epImgView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               CropImage.activity()
                       .setGuidelines(CropImageView.Guidelines.ON)
                       .setMinCropResultSize(512, 512)
                       .setAspectRatio(1, 1)
                       .start(getActivity());
           }
       });

       epView.findViewById(R.id.ep_submit).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String epTitle = epEditTitle.getText().toString();
               String epDesc = epEditDesc.getText().toString();

               epCall = RetrofitClient
                       .getInstance()
                       .getApi()
                       .editPost(epTitle,epDesc,epImgBase64,epPostId);

               epCall.enqueue(new Callback<EditPostResponse>() {
                   @Override
                   public void onResponse(Call<EditPostResponse> call, Response<EditPostResponse> response) {
                       EditPostResponse epResp = response.body();

                       if(epResp != null){

                           if(epResp.getResult() == 1){
                               Toast.makeText(getContext(), "successfully uploaded !", Toast.LENGTH_SHORT).show();
                           }else{
                               Toast.makeText(getContext(), epResp.getMessage(), Toast.LENGTH_SHORT).show();
                           }

                       }else{
                           Toast.makeText(getContext(), "error in uploading", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<EditPostResponse> call, Throwable t) {
                       Toast.makeText(getContext(), "check our internet connection", Toast.LENGTH_SHORT).show();
                   }
               });

           }
       });



       //Getting the

        return epView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                postImageUri = result.getUri();
                if (postImageUri.toString().isEmpty()) {

                    Toast.makeText(getContext(), "Please insert image !", Toast.LENGTH_SHORT).show();

                } else {

                    epImgView.setImageURI(postImageUri);

                    File actucalImage = new File(result.getUri().getPath());

                    try {
                        Bitmap compressedImage = new Compressor(getContext())
                                .setQuality(100)
                                .compressToBitmap(actucalImage);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        compressedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        epImgData = baos.toByteArray();
                        epImgBase64 = Base64.encodeToString(epImgData, Base64.DEFAULT);


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
