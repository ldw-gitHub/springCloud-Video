package com.itcast.dw.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.itcast.dw.baseDao.BaseServiceImpl;
import com.itcast.dw.dao.VideoInfoMapper;
import com.itcast.dw.feign.ESFeign;
import com.itcast.dw.model.VideoComments;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.model.VideoInfoVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;
import com.itcast.dw.service.VideoService;
import com.itcast.dw.vo.VideoVo;

@Service
public class VideoServiceImpl extends BaseServiceImpl<VideoInfo> implements VideoService {

	@Resource
	private VideoInfoMapper vm;
	@Autowired
	private ESFeign esFeign;
	
	@Override
	public void saveMedia(VideoInfo vi) {
		vm.saveMedia(vi);
		
		new Thread(() -> esFeign.saveVideoToES(new VideoVo(vi.getId(),
				vi.getTitle(),
				vi.getDescription(),
				vi.getClick(),
				vi.getImgpath(),
				vi.getVideopath()))).start();
	}

	@Override
	public void updateVideoClick(VideoInfo vi) {
		vm.updateVideoClick(vi);
	}

	@Override
	public void saveVideoComments(VideoComments vc) {
		vm.saveVideoComments(vc);
	}

	@Override
	public List<VideoInfoVo> findAllMedia() {
		return vm.findAllMedia();
	}

	@Override
	public PageBean<VideoInfoVo> getVideosByType(PageModel page, String videoType) {
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<VideoInfoVo> list = vm.getVideosByType(videoType);
		PageBean<VideoInfoVo> pageBean = new PageBean<VideoInfoVo>(list);
		return pageBean;
	}

	@Override
	public List<VideoInfoVo> getIndexVideosByType(String videoType) {
		return vm.getIndexVideosByType(videoType);
	}

	@Override
	public List<VideoInfoVo> findRelateVideos(Map<String, Object> parameterMap) {
		return vm.findRelateVideos(parameterMap);
	}

	@Override
	public VideoInfo getVideosById(int videoId) {
		return vm.getVideosById(videoId);
	}

	@Override
	public PageBean<VideoInfoVo> getVideosByUserId(PageModel page, int userId) {
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<VideoInfoVo> list = vm.getVideosByUserId(userId);
		PageBean<VideoInfoVo> pageBean = new PageBean<VideoInfoVo>(list);
		return pageBean;
	}

	@Override
	public List<VideoComments> getVideoCommentsByid(int videoid) {
		return vm.getVideoCommentsByid(videoid);
	}

}
