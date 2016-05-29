package com.rajendra.tutcto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.rajendra.tutcto.api.model.Video;

import java.util.List;

/**
 * Created by rajendraverma on 28-05-2016.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    private Context context;
    private List<Video> videoList;
    private Activity activity;

    public VideoAdapter(Context context, List<Video> videoList, Activity activity) {
        this.context = context;
        this.videoList = videoList;
        this.activity = activity;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( context );

        return new VideoHolder( layoutInflater.inflate( R.layout.video_adapter, null ) );
    }

    @Override
    public void onBindViewHolder(final VideoHolder holder, final int position) {
        holder.youTubeThumbnailView.initialize( "AIzaSyAX1dzkHeMkkFmmnmUXCKQyaGOBE1kU0g0", new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader loader) {
                loader.setVideo( videoList.get( position ).getVideoId() );
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        } );
        holder.youTubeThumbnailView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, DetailVideo.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                intent.putExtra( "videoId", videoList.get( position ).getVideoId() );
                context.startActivity( intent );
              /*  ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, holder.youTubeThumbnailView, "rajendra");
                ActivityCompat.startActivity( activity, intent, options.toBundle());
*/

            }
        } );


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        YouTubeThumbnailView youTubeThumbnailView;

        public VideoHolder(View itemView) {
            super( itemView );
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById( R.id.thumbnail );


        }


    }
}
