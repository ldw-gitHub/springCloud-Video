package com.itcast.dw.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;

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
    
	@GetMapping(value = "/getVideosByType/{videoType}")
    List<VideoInfoVo> getVideosByType(@PathVariable("videoType") String videoType);
    
	@GetMapping(value = "/getVideosById/{videoId}")
    VideoInfo getVideosById(@PathVariable("videoId") int videoId);
	
	@GetMapping(value = "/getVideosByUserId/{userId}")
	List<VideoInfoVo> getVideosByUserId(@PathVariable("userId") int userId);
	
	@GetMapping(value = "/getVideoCommentsByid/{videoid}")
	List<VideoComments> getVideoCommentsByid(@PathVariable("videoid") int videoid);
	
}
