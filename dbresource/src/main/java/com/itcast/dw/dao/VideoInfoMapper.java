package com.itcast.dw.dao;

import java.util.List;

import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;

public interface VideoInfoMapper {
	void saveMedia(VideoInfo vi);
	
	void saveVideoComments(VideoComments vc);
	
	List<VideoInfoVo> findAllMedia();
	
	List<VideoInfoVo> getVideosByType(String videoType);
	
	VideoInfo getVideosById(int videoId);
	
	List<VideoInfoVo> getVideosByUserId(int userId);
	
	List<VideoComments> getVideoCommentsByid(int videoid);
	
	void updateVideoClick(VideoInfo vi);
}
