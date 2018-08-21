package com.itcast.dw.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.dw.dao.VideoInfoMapper;
import com.itcast.dw.model.VideoInfo;

@RestController
public class VideoController {
	
	@Autowired
	private VideoInfoMapper vm;
	
	@PostMapping(value = "/saveMedias")
	public void saveMedia(@RequestBody VideoInfo vi) {
        vm.saveMedia(vi);
	}

	@GetMapping(value = "/findAllMedia")
	public List<VideoInfo> findAllMedia() {
		return vm.findAllMedia();
	}
	
	@GetMapping(value = "/getVideosByType/{videoType}")
	public List<VideoInfo> getVideosByType(@PathVariable String videoType) {
		return vm.getVideosByType(videoType);
	}
	
	@GetMapping(value = "/getVideosById/{videoId}")
	public VideoInfo getVideosById(@PathVariable int videoId) {
		return vm.getVideosById(videoId);
	}

}
