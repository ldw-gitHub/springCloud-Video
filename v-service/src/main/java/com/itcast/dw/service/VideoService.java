package com.itcast.dw.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.itcast.dw.baseDao.BaseService;
import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;

public interface VideoService extends BaseService<VideoInfo>{
	
    void saveMedia(VideoInfo vi);
	
	void updateVideoClick(VideoInfo vi);
	
	void saveVideoComments(VideoComments vc);
    
    List<VideoInfoVo> findAllMedia();
    
	PageBean<VideoInfoVo> getVideosByType(PageModel page,String videoType);
    
	List<VideoInfoVo> getIndexVideosByType(String videoType);
	
	List<VideoInfoVo> findRelateVideos(Map<String,Object> parameterMap);
	
    VideoInfo getVideosById(int videoId);
	
	PageBean<VideoInfoVo> getVideosByUserId(@RequestBody PageModel page,int userId);
	
	List<VideoComments> getVideoCommentsByid(int videoid);
	
}
