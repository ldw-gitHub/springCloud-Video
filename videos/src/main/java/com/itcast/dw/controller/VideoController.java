package com.itcast.dw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.service.VideoService;

@RestController
public class VideoController {
	
	private Logger log = LoggerFactory.getLogger(VideoController.class);
	@Autowired
	private VideoService videoservice;
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 获取首页要展示的视频
	 * @return
	 */
	@RequestMapping("/findAllVideos")
	public String findAllVideos(){
		List<VideoInfo> list = videoservice.findAllMedia();
		Map<String,Object> rtMap = new HashMap<String,Object>();
		rtMap.put("data", list);
		rtMap.put("success", true);
		
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(rtMap);
		} catch (Exception e) {
			log.info("Error in getDataLists()", e);
		}
		return jsonString;
	}
	
	/**
	 * 根据视频类型获取数据
	 * @return
	 */
	@RequestMapping(value = "/findVideosByType")
	public String findVideosByType(HttpServletRequest request){
		String videoType = request.getParameter("videoType");
		List<VideoInfo> list = videoservice.getVideosByType(videoType);
		Map<String,Object> rtMap = new HashMap<String,Object>();
		rtMap.put("data", list);
		rtMap.put("success", true);
		
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(rtMap);
		} catch (Exception e) {
			log.info("Error in getDataLists()", e);
		}
		return jsonString;
	}
	
	/**
	 * 根据视频id获取数据
	 * @return
	 */
	@RequestMapping(value = "/findVideosById" ,method = RequestMethod.GET)
	public String findVideosById(HttpServletRequest request){
		String videoId = request.getParameter("videoId");
		VideoInfo list = videoservice.getVideosById(Integer.parseInt(videoId));
		Map<String,Object> rtMap = new HashMap<String,Object>();
		rtMap.put("video", list);
		rtMap.put("success", true);
		
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(rtMap);
		} catch (Exception e) {
			log.info("Error in getDataLists()", e);
		}
		return jsonString;
	}
}
