package com.itcast.dw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.itcast.dw.common.CommonUtil;
import com.itcast.dw.common.UploadFile;
import com.itcast.dw.model.User;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.service.VideoService;

@RestController
public class UploadController {
	static Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("uploadfile") MultipartFile uploadFile,HttpServletRequest request) throws IOException {
		JSONObject response = new JSONObject();
		
		String attachName = request.getParameter("attachName");
		String tag = request.getParameter("tag");//文件夹名
		String path = "img";
		
		InputStream stream = uploadFile.getInputStream();
		
		if(StringUtils.isNotEmpty(tag)) {
			path = tag;
		}
		
		attachName = "[" + CommonUtil.getDataFormat(new Date(), "yyyyMMddHHmmssSSS") + "]" + attachName;
		
		UploadFile ftp = new UploadFile();
		boolean flag = ftp.UploadFileToServer(stream,attachName, path);
		
		response.put("success", flag);
		response.put("uploadFileName", attachName);
		
		return response.toString();
	}
	
	
	@RequestMapping(value = "/deleteFile",method = RequestMethod.POST,produces = "application/json")
    public String deleteFile(HttpServletRequest request) throws Exception {    	
		String attachName = request.getParameter("attachName");
		String tag = request.getParameter("tag");
		
		UploadFile ftp = new UploadFile();
		if ("img".equals(tag)) {
			ftp.changeDir("img"); 
		} else if ("video".equals(tag)) {
			ftp.changeDir("video");
		} 
		ftp.deleteFile(attachName);
		ftp.close();
		JSONObject response = new JSONObject();
		response.put("success", true);
		return response.toString();
	}
    
    @RequestMapping(value = "/saveMedia",method = RequestMethod.POST,produces = "application/json")
    public String saveMedia(HttpServletRequest request) throws Exception {    	
    	JSONObject response = new JSONObject();
    	String uploadImgPath = request.getParameter("uploadImgPath");
    	String uploadVideoPath = request.getParameter("uploadVideoPath");
    	String videoType = request.getParameter("videoType");
    	String description = request.getParameter("description");
    	String filename = request.getParameter("filename");
    	
    	VideoInfo vi = new VideoInfo();
    	vi.setCreateuserid(1);
    	vi.setImgpath("img/" + uploadImgPath);
    	vi.setVideopath("video/" + uploadVideoPath);
    	vi.setTitle(filename);
    	vi.setVideoType(videoType);
    	
    	vi.setCreatetime(new Date());
    	vi.setUpdatetime(new Date());
    	vi.setDescription(description);
    	
    	videoService.saveMedia(vi);
    	response.put("success", true);
    	
    	return response.toString();
    }
}
