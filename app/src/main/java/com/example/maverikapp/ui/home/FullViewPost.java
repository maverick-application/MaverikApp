package com.example.maverikapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maverikapp.R;

public class FullViewPost extends AppCompatActivity {

    private String fvpTitle,fvpDesc,fvpImg,fvpLike,fvpTime,fvpUser;
    private TextView fvpTextTitle,fvpTextDesc,fvpTextTime,fvpTextUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view_post);

        Intent intent = getIntent();
        fvpTitle = intent.getStringExtra("title");
        fvpDesc = intent.getStringExtra("desc");
        fvpImg = intent.getStringExtra("img");
        fvpLike = intent.getStringExtra("like");
        fvpTime = intent.getStringExtra("time");
        fvpUser = intent.getStringExtra("user");

        fvpTextTitle = (TextView)findViewById(R.id.fvp_title);
        fvpTextDesc = (TextView) findViewById(R.id.fvp_desc);
        fvpTextTime = (TextView)findViewById(R.id.fvp_time);
        fvpTextUser = (TextView)findViewById(R.id.fvp_user);

        fvpTextTitle.setText(fvpTitle);
        fvpTextDesc.setText(fvpDesc);
        fvpTextUser.setText(fvpUser);
        fvpTextTime.setText(fvpTime);

        Toast.makeText(FullViewPost.this, fvpTitle+" "+fvpDesc+" "+fvpTime+" "+fvpImg, Toast.LENGTH_SHORT).show();

    }

}
