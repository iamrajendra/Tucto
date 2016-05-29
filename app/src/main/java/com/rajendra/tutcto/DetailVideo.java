package com.rajendra.tutcto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.rajendra.tutcto.api.AppBarStateChangeListener;

public class DetailVideo extends AppCompatActivity  {
    private AppBarLayout appBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
/*
        ViewCompat.setTransitionName(findViewById(R.id.thumbnail), "rajendra");
        supportPostponeEnterTransition();
*/

        final   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout = (AppBarLayout)findViewById(R.id.app_bar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = YouTubeIntents.createPlayVideoIntentWithOptions(DetailVideo.this, getIntent().getStringExtra("videoId"), true, false);
                startActivity(intent);            }
        });
        YouTubeThumbnailView youTubeThumbnailView = (YouTubeThumbnailView)findViewById(R.id.thumbnail);
        youTubeThumbnailView.initialize("AIzaSyAX1dzkHeMkkFmmnmUXCKQyaGOBE1kU0g0", new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader loader) {
                loader.setVideo(getIntent().getStringExtra("videoId"));
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }



}
