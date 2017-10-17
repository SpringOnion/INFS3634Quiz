package com.example.chenz.infs3634quiz;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;

public class DemoActivity extends YouTubeBaseActivity {

    private static final String mDeveloperKey = "AIzaSyDrG78CdhYTAw_W-iVJpccmQ4w7ayx3W9I";
    String mYoutubeLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }
}
