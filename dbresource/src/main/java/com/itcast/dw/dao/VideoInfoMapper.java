package com.itcast.dw.dao;

import java.util.List;

import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;

public interface VideoInfoMapper {
	void saveMedia(VideoInfo vi);
	
	void saveVideoComments(VideoComments vc);
	
	List<VideoInfo> findAllMedia();
	
	List<VideoInfo> getVideosByType(String videoType);
	
	VideoInfo getVideosById(int videoId);
	
	List<VideoInfo> getVideosByUserId(int userId);
	
	List<VideoComments> getVideoCommentsByid(int videoid);
}
