package com.itcast.dw.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.model.FileModel;
import com.itcast.dw.model.FileModelVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;
import com.itcast.dw.page.Paging;
import com.itcast.dw.service.FileService;

@RestController
public class FileController {
	
	private Logger log = LoggerFactory.getLogger(FileController.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private FileService fileService;
	
	
	@PostMapping("/saveFile")
	public JSONObject saveFile(HttpServletRequest request){
		JSONObject response = new JSONObject();
		
		String filename = request.getParameter("filename");
		double filesize = Double.parseDouble(request.getParameter("fileSize"));
		String filepath = request.getParameter("uploadFilePath");
		int createuserid = Integer.parseInt(request.getParameter("userId"));
		
		FileModel fm = new FileModel();
		fm.setFilename(filename);
		fm.setFilepath(filepath);
		fm.setFilesize(filesize);
		fm.setDownload(1);
		fm.setDownloadnumber(0);
		fm.setCreateuserid(createuserid);
		fm.setCreatetime(new Date());
		fm.setUpdatetime(new Date());
		
		fileService.saveFileModel(fm);
		
		response.put("success", true);
		
		return response;
	}
	
	@PostMapping(value = "/getFilesByUserId")
	public ResultInfo<?> getFilesByUserId(HttpServletRequest request) {
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
		PageBean<FileModelVo> pageBean = fileService.getFilesByUserId(page, userId);

		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,new Paging(pageBean));
	}
	
	
	
	

}
