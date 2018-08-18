package com.itcast.dw.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itcast.dw.model.VideoInfo;

@FeignClient(value = "service-db")
public interface VideoService {
	
	@RequestMapping(method = RequestMethod.POST,value = "/getUserByName")
    void saveMedia(@RequestBody VideoInfo vi);
    
	@GetMapping(value = "/findAllMedia")
    List<VideoInfo> findAllMedia();
    
	@GetMapping(value = "/getVideosByType/{videoType}")
    List<VideoInfo> getVideosByType(@PathVariable("videoType") String videoType);
    
	@GetMapping(value = "/getVideosById/{videoId}")
    VideoInfo getVideosById(@PathVariable("videoId") int videoId);
}
