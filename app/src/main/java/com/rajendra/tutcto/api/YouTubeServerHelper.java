package com.rajendra.tutcto.api;

import android.content.Context;
import android.util.Log;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.rajendra.tutcto.api.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajendraverma on 28-05-2016.
 */
public abstract class YouTubeServerHelper implements IYouTubeServerHelper {
    public static String CONSTANT_SUCCESSFUL;
    public static String CONSTANT_FAILURE;
    public static String CONSTANT_INVALID;
    public static String CONSTANT_NETWORK;
    private String Tag="YouTubeServerHelper";

    public abstract void onResponse(List<Video> videoList, String type);

    private static String CONSTANT_URL = "https://www.googleapis.com/youtube/v3/activities?part=contentDetails";
    private static String developerkey = "AIzaSyAX1dzkHeMkkFmmnmUXCKQyaGOBE1kU0g0";

    @Override
    public void downloadVideos(Context context, String channelId) {
        Ion.with(context).load(CONSTANT_URL+"&channelId="+channelId+"&key="+developerkey).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {

                handleResponse(e,result);


            }
        });


    }

    private void handleResponse(Exception e, String result) {
        if (e != null) {
            onResponse(null, CONSTANT_INVALID);
            return;
        }

        if (result == null) {
            onResponse(null, CONSTANT_INVALID);
            return;
        }

        if (result != null) {

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                List<Video> videos = new ArrayList<Video>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    JSONObject contentDetails = jsonObject1.getJSONObject("contentDetails");
                    if (!contentDetails.isNull("upload")) {
                        JSONObject upload = contentDetails.getJSONObject("upload");
                        String vedioID = upload.getString("videoId");
                        Log.i(Tag, "VideoID" + vedioID);
                        videos.add(new Video(vedioID));

                    }

                }

                onResponse(videos, CONSTANT_SUCCESSFUL);

            } catch (JSONException e1) {
                e1.printStackTrace();
                onResponse(null, CONSTANT_INVALID);
            }



        }

    }




}
