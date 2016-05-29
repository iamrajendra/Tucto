package com.rajendra.tutcto.api.model;

/**
 * Created by rajendraverma on 28-05-2016.
 */
public class Video {
    private String videoId;

    public Video(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
