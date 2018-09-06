package com.itcast.dw.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.itcast.dw.dao.VideoInfoMapper;
import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;

@RestController
public class VideoController {
	
	@Autowired
	private VideoInfoMapper vm;
	
	@PostMapping(value = "/saveVideoComments")
	public void saveVideoComments(@RequestBody VideoComments vc) {
        vm.saveVideoComments(vc);
	}
	
	@PostMapping(value = "/saveMedias")
	public void saveMedia(@RequestBody VideoInfo vi) {
		vm.saveMedia(vi);
	}
	
	@PostMapping(value = "/updateVideoClick")
	public void updateVideoClick(@RequestBody VideoInfo vi) {
		vm.updateVideoClick(vi);
	}

	@GetMapping(value = "/findAllMedia")
	public List<VideoInfoVo> findAllMedia() {
		return vm.findAllMedia();
	}
	
	@PostMapping(value = "/getVideosByType")
	public PageBean<VideoInfoVo> getVideosByType(@RequestBody PageModel page,@RequestParam("videoType") String videoType) {
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<VideoInfoVo> list = vm.getVideosByType(videoType);
		PageBean<VideoInfoVo> pageBean = new PageBean<VideoInfoVo>(list);
		return pageBean;
	}
	
	@GetMapping(value = "/getIndexVideosByType/{videoType}")
	public List<VideoInfoVo> getIndexVideosByType(@PathVariable("videoType") String videoType) {
		return vm.getIndexVideosByType(videoType);
	}
	
	@GetMapping(value = "/findRelateVideos")
	public List<VideoInfoVo> findRelateVideos(@RequestParam Map<String,Object> parameterMap) {
		return vm.findRelateVideos(parameterMap);
	}
	
	@GetMapping(value = "/getVideosById/{videoId}")
	public VideoInfo getVideosById(@PathVariable int videoId) {
		return vm.getVideosById(videoId);
	}
	
	@PostMapping(value = "/getVideosByUserId")
	public PageBean<VideoInfoVo> getVideosByUserId(@RequestBody PageModel page,@RequestParam int userId) {
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<VideoInfoVo> list = vm.getVideosByUserId(userId);
		PageBean<VideoInfoVo> pageBean = new PageBean<VideoInfoVo>(list);
		return pageBean;
	}
	
	@GetMapping(value = "/getVideoCommentsByid/{videoid}")
	public List<VideoComments> getVideoCommentsByid(@PathVariable int videoid) {
		return vm.getVideoCommentsByid(videoid);
	}

}
