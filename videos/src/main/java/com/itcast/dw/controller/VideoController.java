package com.itcast.dw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;
import com.itcast.dw.service.VideoService;

@RestController
public class VideoController {

	private Logger log = LoggerFactory.getLogger(VideoController.class);
	@Autowired
	private VideoService videoservice;
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 获取首页要展示的视频
	 * 
	 * @return
	 */
	@RequestMapping("/findAllVideos")
	public String findAllVideos() {
		List<VideoInfoVo> list = videoservice.findAllMedia();
		Map<String, Object> rtMap = new HashMap<String, Object>();
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findMyOwnVideos")
	public String findMyOwnVideos(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String curPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		if (curPage == null || "".equals(curPage)) {
			curPage = "1";
		}
		if (pageSize == null || "".equals(pageSize)) {
			pageSize = "24";
		}
		
		PageModel page = new PageModel(Integer.parseInt(curPage), Integer.parseInt(pageSize));
		PageBean<VideoInfoVo> pageBean = videoservice.getVideosByUserId(page,userId);
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("data", pageBean.getList());
		rtMap.put("totalRows", pageBean.getTotal());//总记录数
		rtMap.put("curPage", pageBean.getPageNum());//当前页
		rtMap.put("pages", pageBean.getPages());//总页数
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findVideosByType")
	public String findVideosByType(HttpServletRequest request) {
		String videoType = request.getParameter("videoType");
		String curPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		if (curPage == null || "".equals(curPage)) {
			curPage = "1";
		}
		if (pageSize == null || "".equals(pageSize)) {
			pageSize = "24";
		}
		
		PageModel page = new PageModel(Integer.parseInt(curPage), Integer.parseInt(pageSize));
		PageBean<VideoInfoVo> pageBean = videoservice.getVideosByType(page, videoType);
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("data", pageBean.getList());
		rtMap.put("totalRows", pageBean.getTotal());//总记录数
		rtMap.put("curPage", pageBean.getPageNum());//当前页
		rtMap.put("pages", pageBean.getPages());//总页数
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
	 * 根据视频类型获取数据12条
	 * 
	 * @return
	 */
	@RequestMapping(value = "/indexFindVideosByType")
	public String indexFindVideosByType(HttpServletRequest request) {
		String videoType = request.getParameter("videoType");
		
		List<VideoInfoVo> list = videoservice.getIndexVideosByType(videoType);
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
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
	
	@RequestMapping(value = "/findRelateVideos")
	public String findRelateVideos(HttpServletRequest request) {
		String thisTitle = request.getParameter("thisTitle");
		String thisDescription = request.getParameter("thisDescription");
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("thisTitle", thisTitle);
		parameterMap.put("thisDescription", thisDescription);
		
		List<VideoInfoVo> list = videoservice.findRelateVideos(parameterMap);
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findVideosById", method = RequestMethod.GET)
	public String findVideosById(HttpServletRequest request) {
		String videoId = request.getParameter("videoId");
		VideoInfo vi = videoservice.getVideosById(Integer.parseInt(videoId));
		int click = vi.getClick();
		
		vi.setClick(click + 1);
		
		videoservice.updateVideoClick(vi);
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("video", vi);
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
	 * 获取视频评论
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getVideoCommentsByid", method = RequestMethod.GET)
	public String getVideoCommentsByid(HttpServletRequest request) {
		String videoId = request.getParameter("videoId");
		List<VideoComments> list = videoservice.getVideoCommentsByid(Integer.parseInt(videoId));
		Map<String, Object> rtMap = new HashMap<String, Object>();
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
	 * 保存评论
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveVideoComments", method = RequestMethod.POST, produces = "application/json")
	public String saveVideoComments(HttpServletRequest request) throws Exception {
		JSONObject response = new JSONObject();
		String videoId = request.getParameter("videoId");
		String userId = request.getParameter("userId");
		String comments = request.getParameter("comments");
		String username = request.getParameter("username");

		VideoComments vc = new VideoComments();
		vc.setCommentuserid(Integer.parseInt(userId));
		vc.setCommentusername(username);
		vc.setCreatetime(new Date());
		vc.setMsg(comments);
		vc.setVideoid(Integer.parseInt(videoId));

		videoservice.saveVideoComments(vc);
		response.put("success", true);

		return response.toString();
	}

}
