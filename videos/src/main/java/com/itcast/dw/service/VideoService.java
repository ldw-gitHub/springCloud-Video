package com.itcast.dw.service;

import java.util.List;

import com.itcast.dw.model.VideoInfo;

public interface VideoService {
    void saveMedia(VideoInfo vi);
    
    List<VideoInfo> findAllMedia();
    
    List<VideoInfo> getVideosByType(String videoType);
    
    VideoInfo getVideosById(int videoId);
}
