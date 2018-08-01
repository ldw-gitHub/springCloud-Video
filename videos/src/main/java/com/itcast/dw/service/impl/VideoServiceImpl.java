package com.itcast.dw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcast.dw.dao.VideoInfoMapper;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.service.VideoService;

@Service("videoservice")
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoInfoMapper vm;
	
	@Override
	public void saveMedia(VideoInfo vi) {
        vm.saveMedia(vi);
	}

	@Override
	public List<VideoInfo> findAllMedia() {
		return vm.findAllMedia();
	}
	
	@Override
	public List<VideoInfo> getVideosByType(String videoType) {
		return vm.getVideosByType(videoType);
	}
	
	@Override
	public VideoInfo getVideosById(int videoId) {
		return vm.getVideosById(videoId);
	}

}
