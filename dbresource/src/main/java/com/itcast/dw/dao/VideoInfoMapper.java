package com.itcast.dw.dao;

import java.util.List;

import com.itcast.dw.model.VideoInfo;

public interface VideoInfoMapper {
	void saveMedia(VideoInfo vi);
	
	List<VideoInfo> findAllMedia();
	
	List<VideoInfo> getVideosByType(String videoType);
	
	VideoInfo getVideosById(int videoId);
	
	List<VideoInfo> getVideosByUserId(int userId);
}
