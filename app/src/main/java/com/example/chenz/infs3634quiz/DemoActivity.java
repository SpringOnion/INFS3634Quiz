package com.example.chenz.infs3634quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class DemoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String mDeveloperKey = "AIzaSyDrG78CdhYTAw_W-iVJpccmQ4w7ayx3W9I";
    String mYoutubeLink = "-vH2eZAM30s";
    Button mNextButton;
    RelativeLayout mScreen1;
    RelativeLayout mScreenYoutube;
    YouTubePlayerView player;
    int mDemoProgress = 0;
    TextView mText1;
    TextView mText2;
    ImageView mImage;

    //A lot of the code in this demo is referenced from the tutorial slides (as well as the methods being shown)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mScreen1 = findViewById(R.id.screen_youtube_1);
        mScreenYoutube = findViewById(R.id.screen_youtube_player);
        player = findViewById(R.id.player_youtube);
        mText1 = findViewById(R.id.text_box_1);
        mText2 = findViewById(R.id.text_box_2);
        mImage = findViewById(R.id.image_view);
        mImage.setImageResource(R.drawable.credentials);

        mScreen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mText1.getVisibility() == View.VISIBLE) {
                    mText1.setVisibility(View.INVISIBLE);
                    mText2.setVisibility(View.INVISIBLE);
                    mImage.setVisibility(View.VISIBLE);
                } else {
                    mImage.setVisibility(View.INVISIBLE);
                    mText1.setVisibility(View.VISIBLE);
                    mText2.setVisibility(View.VISIBLE);
                }
            }
        });

        mNextButton = findViewById(R.id.button_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYoutubeLink = "-vH2eZAM30s";
                player.initialize(mDeveloperKey, DemoActivity.this);
                mScreen1.setVisibility(View.INVISIBLE);
                mScreenYoutube.setVisibility(View.VISIBLE);
            }
        });
    }

    private void progressNext() {
        mDemoProgress++;
        if (mDemoProgress == 1) {
            mText1.setText("Once you have an API key, the next step is to import the library. ");
            mText2.setText("");
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(mYoutubeLink);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(DemoActivity.this, "There was an error with initialising the Youtube Player: " + youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
    }
}
