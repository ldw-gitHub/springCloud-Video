package com.itcast.dw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.itcast.dw.dao.FileMapper;
import com.itcast.dw.model.FileModel;
import com.itcast.dw.model.FileModelVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;
import com.itcast.dw.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Resource
	private FileMapper fileMapper;

	@Override
	public void saveFileModel(FileModel fm) {
		fileMapper.saveFileModel(fm);
	}

	@Override
	public PageBean<FileModelVo> getFilesByUserId(PageModel page, int userId) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return new PageBean<FileModelVo>(fileMapper.getFilesByUserId(userId));
	}

}
