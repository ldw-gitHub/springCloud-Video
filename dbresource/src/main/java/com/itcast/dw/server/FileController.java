package com.itcast.dw.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.itcast.dw.dao.FileMapper;
import com.itcast.dw.model.FileModel;
import com.itcast.dw.model.FileModelVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;

@RestController
public class FileController {

	@Autowired
	private FileMapper fileMapper;
	
	@PostMapping(value = "/saveFileModel")
	public void saveFileModel(@RequestBody FileModel fm){
		fileMapper.saveFileModel(fm);
	}
	
	@PostMapping(value = "/getFilesByUserId")
	public PageBean<FileModelVo> getFilesByUserId(@RequestBody PageModel page,@RequestParam("userId") int userId){
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return new PageBean<FileModelVo>(fileMapper.getFilesByUserId(userId));
	}
	
}
