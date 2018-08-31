package com.itcast.dw.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.dw.dao.VideoInfoMapper;
import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;

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
	
	@GetMapping(value = "/getVideosByType/{videoType}")
	public List<VideoInfoVo> getVideosByType(@PathVariable String videoType) {
		return vm.getVideosByType(videoType);
	}
	
	@GetMapping(value = "/getVideosById/{videoId}")
	public VideoInfo getVideosById(@PathVariable int videoId) {
		return vm.getVideosById(videoId);
	}
	
	@GetMapping(value = "/getVideosByUserId/{userId}")
	public List<VideoInfoVo> getVideosByUserId(@PathVariable int userId) {
		return vm.getVideosByUserId(userId);
	}
	
	@GetMapping(value = "/getVideoCommentsByid/{videoid}")
	public List<VideoComments> getVideoCommentsByid(@PathVariable int videoid) {
		return vm.getVideoCommentsByid(videoid);
	}

}
