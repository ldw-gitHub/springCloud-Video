package com.itcast.dw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.model.AdminUser;
import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;
import com.itcast.dw.page.Paging;
import com.itcast.dw.service.VideoService;

@RestController
public class VideoController extends BaseController{

	private Logger log = LoggerFactory.getLogger(VideoController.class);
	@Autowired
	private VideoService videoservice;
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 获取首页要展示的视频
	 * 
	 * @return
	 */
	@PostMapping("/findAllVideos")
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
	 * @throws Exception 
	 */
	@PostMapping(value = "/findMyOwnVideos")
	public ResultInfo<?> findMyOwnVideos(HttpServletRequest request,HttpServletResponse response) throws Exception {
		AdminUser currentUserInfo = getCurrentUserInfo(request,response);
		int userId = currentUserInfo.getUserId();
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
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,new Paging(pageBean));
	}

	/**
	 * 根据视频类型获取数据
	 * 
	 * @return
	 */
	@PostMapping(value = "/findVideosByType")
	public ResultInfo<?> findVideosByType(HttpServletRequest request) {
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
		
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,new Paging(pageBean));
	}
	
	/**
	 * 根据视频类型获取数据12条
	 * 
	 * @return
	 */
	@PostMapping(value = "/indexFindVideosByType")
	public ResultInfo<?> indexFindVideosByType(HttpServletRequest request) {
		String videoType = request.getParameter("videoType");
		
		List<VideoInfoVo> list = videoservice.getIndexVideosByType(videoType);
		
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,list);
	}
	
	@PostMapping(value = "/findRelateVideos")
	public ResultInfo<?> findRelateVideos(HttpServletRequest request) {
		String thisTitle = request.getParameter("thisTitle");
		String thisDescription = request.getParameter("thisDescription");
		String videoid = request.getParameter("videoid");
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("thisTitle", thisTitle);
		parameterMap.put("thisDescription", thisDescription);
		parameterMap.put("videoid", videoid);
		
		List<VideoInfoVo> list = videoservice.findRelateVideos(parameterMap);
		
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,list);
	}

	/**
	 * 根据视频id获取数据
	 * 
	 * @return
	 */
	@PostMapping(value = "/findVideosById")
	public ResultInfo<?> findVideosById(HttpServletRequest request) {
		String videoId = request.getParameter("videoId");
		VideoInfo vi = videoservice.getVideosById(Integer.parseInt(videoId));
		int click = vi.getClick();
		
		vi.setClick(click + 1);
		
		videoservice.updateVideoClick(vi);
		
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,vi);
	}

	/**
	 * 获取视频评论
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/getVideoCommentsByid")
	public ResultInfo<?> getVideoCommentsByid(HttpServletRequest request) {
		String videoId = request.getParameter("videoId");
		List<VideoComments> list = videoservice.getVideoCommentsByid(Integer.parseInt(videoId));
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,list);
	}

	/**
	 * 保存评论
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/saveVideoComments")
	public ResultInfo<?> saveVideoComments(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String videoId = request.getParameter("videoId");
		String comments = request.getParameter("comments");
		
		AdminUser currentUserInfo = getCurrentUserInfo(request,response);
		int userId = -1;
		String userName = "游客";
		if(currentUserInfo != null){
			userId = currentUserInfo.getUserId();
			userName = currentUserInfo.getUserName();
		}
		
		VideoComments vc = new VideoComments();
		vc.setCommentuserid(userId);
		vc.setCommentusername(userName);
		vc.setCreatetime(new Date());
		vc.setMsg(comments);
		vc.setVideoid(Integer.parseInt(videoId));

		videoservice.saveVideoComments(vc);
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
	}

}
