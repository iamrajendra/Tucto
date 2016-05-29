package com.rajendra.tutcto;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rajendra.tutcto.api.model.Video;
import com.rajendra.tutcto.api.YouTubeServerHelper;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Context context;
    private String youtubeChannelId ="UCq-Fj5jknLsUf-MWSy4_brA";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          init();
    }

    private void init() {
        context = getApplicationContext();
        recyclerView = (RecyclerView)findViewById(R.id.videosRV);


        YouTubeServerHelper  youTubeServerHelper = new YouTubeServerHelper() {
            @Override
            public void onResponse(List<Video> videoList, String type) {
                if(YouTubeServerHelper.CONSTANT_SUCCESSFUL==type)
                {
                    GridLayoutManager manager = new GridLayoutManager(context, 2);

                    recyclerView.setLayoutManager(manager);
                    VideoAdapter videoAdapter  = new VideoAdapter(context,videoList,MainActivity.this);
                    recyclerView.setAdapter(videoAdapter);



                }
            }
        };
        youTubeServerHelper.downloadVideos(context,youtubeChannelId);

    }


}
