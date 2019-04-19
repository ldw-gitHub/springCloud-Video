package com.itcast.dw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.itcast.dw.common.CommonUtil;
import com.itcast.dw.common.UploadFile;
import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.service.VideoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UploadController {
	static Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private VideoService videoService;
	
	@PostMapping(value = "/uploadFile")
	@HystrixCommand(fallbackMethod="uploadFallback")
	public ResultInfo<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadFile,HttpServletRequest request) throws IOException {
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
			
		}else if(total > 1){ //切割上传的文件,文件目录不放在ftp文件夹下
			String pathName = name.substring(0, name.indexOf("."));
			path = tag + "/" + pathName;
		}
		
		attachName = "[" + CommonUtil.getDataFormat(new Date(), "yyyyMMddHHmmssSSS") + "]" + attachName;
		
		UploadFile ftp = new UploadFile();
		boolean flag = ftp.UploadFileToServer(stream,attachName, path.trim());
		
		if(flag){
			if(total > 1 && isLast){
				//String resultPath = "/" + path.substring(0,path.lastIndexOf("/")) + "/" + attachName;
				String resultPath = path.substring(0,path.lastIndexOf("/"));
				flag = ftp.mergeFiles(path.trim(),resultPath.trim(),attachName);
			}
		}
		ftp.close();
		
		response.put("uploadFileName", attachName);
		
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
	}
	
	public ResultInfo<?> uploadFallback(@RequestParam("uploadfile") MultipartFile uploadFile,HttpServletRequest request) throws IOException {
		return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
	}
	
	
	@PostMapping(value = "/deleteFile")
    public ResultInfo<?> deleteFile(HttpServletRequest request) throws Exception {    	
		String attachName = request.getParameter("attachName");
		String tag = request.getParameter("tag");
		
		UploadFile ftp = new UploadFile();
		if ("img".equals(tag)) {
			ftp.changeDir("img"); 
		} else if ("video".equals(tag)) {
			ftp.changeDir("video");
		} else if("file".equals(tag)){
			ftp.changeDir("file");
		}
		ftp.deleteFile(attachName);
		ftp.close();
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
	}
    
	@PostMapping(value = "/saveMedia")
    public ResultInfo<?> saveMedia(HttpServletRequest request) throws Exception {    	
    	String uploadImgPath = request.getParameter("uploadImgPath");
    	String uploadVideoPath = request.getParameter("uploadVideoPath");
    	String videoType = request.getParameter("videoType");
    	String description = request.getParameter("description");
    	String filename = request.getParameter("filename");
    	String isown = request.getParameter("isown");
    	String userId = request.getParameter("userId");
    	
    	VideoInfo vi = new VideoInfo();
    	vi.setCreateuserid(Integer.parseInt(userId));
    	vi.setImgpath("img/" + uploadImgPath);
    	vi.setVideopath("video/" + uploadVideoPath);
    	vi.setTitle(filename);
    	vi.setVideoType(videoType);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String nowDate = sdf.format(new Date());
    	vi.setCreatetime(sdf.parse(nowDate));
    	vi.setUpdatetime(sdf.parse(nowDate));
    	vi.setDescription(description);
    	vi.setIsown(isown);
    	vi.setClick(0);
    	
    	videoService.saveMedia(vi);
    	
    	return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }
}
