package com.itcast.dw.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;

@FeignClient(value = "service-db")
public interface VideoService {
	
	@PostMapping(value = "/saveMedias")
    void saveMedia(@RequestBody VideoInfo vi);
	
	@PostMapping(value = "/updateVideoClick")
	void updateVideoClick(@RequestBody VideoInfo vi);
	
	@PostMapping(value = "/saveVideoComments")
	void saveVideoComments(@RequestBody VideoComments vc);
    
	@GetMapping(value = "/findAllMedia")
    List<VideoInfoVo> findAllMedia();
    
	@PostMapping(value = "/getVideosByType")
	PageBean<VideoInfoVo> getVideosByType(@RequestBody PageModel page,@RequestParam("videoType") String videoType);
    
	@GetMapping(value = "/getIndexVideosByType/{videoType}")
	List<VideoInfoVo> getIndexVideosByType(@PathVariable("videoType") String videoType);
	
	@GetMapping(value = "/findRelateVideos")
	List<VideoInfoVo> findRelateVideos(@RequestParam Map<String,Object> parameterMap);
	
	@GetMapping(value = "/getVideosById/{videoId}")
    VideoInfo getVideosById(@PathVariable("videoId") int videoId);
	
	@PostMapping(value = "/getVideosByUserId")
	PageBean<VideoInfoVo> getVideosByUserId(@RequestBody PageModel page,@RequestParam("userId") int userId);
	
	@GetMapping(value = "/getVideoCommentsByid/{videoid}")
	List<VideoComments> getVideoCommentsByid(@PathVariable("videoid") int videoid);
	
}
