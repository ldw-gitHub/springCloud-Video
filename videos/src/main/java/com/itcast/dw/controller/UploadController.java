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
import com.itcast.dw.common.FTPClientHelper;
import com.itcast.dw.common.UploadFile;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.service.VideoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UploadController {
	static Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private FTPClientHelper ftpClientHelper;
	
	@RequestMapping(value = "/uploadFile")
	@HystrixCommand(fallbackMethod="uploadFallback")
	public String uploadFile(@RequestParam("uploadfile") MultipartFile uploadFile,HttpServletRequest request) throws IOException {
		JSONObject response = new JSONObject();
		
		String count = request.getParameter("count");
		boolean isLast = true;
		if(request.getParameter("isLast") != null){
			isLast = Boolean.parseBoolean(request.getParameter("isLast"));
		}
		String name = request.getParameter("name");
		int total = 1;
		if(request.getParameter("total") != null){
			total = Integer.parseInt(request.getParameter("total"));
		}
		String attachName = request.getParameter("attachName");
		String tag = request.getParameter("tag");//文件夹名
		String path = "img";
		
		InputStream stream = uploadFile.getInputStream();
		
		if(StringUtils.isNotEmpty(tag)) {
			path = tag;
		}
		if(total == 1 && isLast){//只有一个文件
			
		}else if(total > 1){ //切割上传的文件
			String pathName = name.substring(0, name.indexOf("."));
			path = tag + "/" + pathName;
		}
		
		attachName = "[" + CommonUtil.getDataFormat(new Date(), "yyyyMMddHHmmssSSS") + "]" + attachName;
		
		UploadFile ftp = new UploadFile();
		boolean flag = ftp.UploadFileToServer(stream,attachName, path);
	/*	boolean flag = false;
		try {
			String savefilePathName = path + "/" + attachName;
			if(ftpClientHelper.haveDirectory(savefilePathName)){
				flag = ftpClientHelper.storeFile(savefilePathName, stream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		if(total > 1 && isLast){
			String resultPath = "/" + path.substring(0,path.lastIndexOf("/")) + "/" + attachName;
			flag = ftp.mergeFiles(path,resultPath);
		}
		
		response.put("success", flag);
		response.put("uploadFileName", attachName);
		
		return response.toString();
	}
	
	public String uploadFallback(@RequestParam("uploadfile") MultipartFile uploadFile,HttpServletRequest request) throws IOException {
		JSONObject response = new JSONObject();
		response.put("error", "upload error");
		return response.toJSONString();
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
