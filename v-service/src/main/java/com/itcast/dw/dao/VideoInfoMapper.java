package com.itcast.dw.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.itcast.dw.baseDao.BaseMapper;
import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;

public interface VideoInfoMapper extends BaseMapper<VideoInfo>{
	void saveMedia(VideoInfo vi);
	
	void saveVideoComments(VideoComments vc);
	
	List<VideoInfoVo> findAllMedia();
	
	List<VideoInfoVo> getVideosByType(@Param("videoType") String videoType);
	
	List<VideoInfoVo> getIndexVideosByType(@Param("videoType") String videoType);
	
	List<VideoInfoVo> findRelateVideos(Map<String,Object> parameterMap);
	
	VideoInfo getVideosById(@Param("videoId") int videoId);
	
	List<VideoInfoVo> getVideosByUserId(@Param("userId") int userId);
	
	List<VideoComments> getVideoCommentsByid(@Param("videoid") int videoid);
	
	void updateVideoClick(VideoInfo vi);
}
