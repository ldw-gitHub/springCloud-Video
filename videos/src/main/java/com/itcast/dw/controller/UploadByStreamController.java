package com.itcast.dw.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.itcast.dw.common.CommonUtil;
import com.itcast.dw.common.UploadFileByStream;
import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.service.VideoService;

@RestController
public class UploadByStreamController {

	static Logger log = LoggerFactory.getLogger(UploadByStreamController.class);
	
	@Value("${video.path}")
	private String videoPath; 
	
	@Autowired
	private VideoService videoService;
	
	/**
	 * 
	 * 文件上传
	 * @param uploadFile
	 * @param request
	 * @return
	 * @throws IOException 
	 * @date 2019年7月22日
	 * @author liudawei
	 */
	@PostMapping(value = "/uploadFile")
	//@HystrixCommand(fallbackMethod="uploadFallback")
	public ResultInfo<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadFile,HttpServletRequest request) throws IOException {
		JSONObject response = new JSONObject();
		
		//String count = request.getParameter("count");
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
		
		path = videoPath + path.trim() + File.separator;
		
		boolean flag = UploadFileByStream.saveFile(stream, path , attachName);
		
		if(flag){
			if(total > 1 && isLast){
				String filePath = path;
				String saveFileName = attachName;
				String resultPath = path.substring(0,path.lastIndexOf("/"));
				//合并文件
				new Thread(() -> {
					UploadFileByStream.mergeFile(filePath, saveFileName, resultPath);
					log.info("filePath ====== " + filePath);
					log.info("saveFileName ====== " + saveFileName);
					log.info("resultPath ====== " + resultPath);
				}).start();
			}
		}else{
			return new ResultInfo<>(ResultInfo.FAILURE, "上传失败！");
		}
		
		response.put("uploadFileName", attachName);
		
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
	}
	
/*	public ResultInfo<?> uploadFallback(@RequestParam("uploadfile") MultipartFile uploadFile,HttpServletRequest request) throws IOException {
		return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
	}*/
	
	/**
	 * 
	 * 删除
	 * @param request
	 * @return
	 * @throws Exception 
	 * @date 2019年7月22日
	 * @author liudawei
	 */
	@PostMapping(value = "/deleteFile")
    public ResultInfo<?> deleteFile(HttpServletRequest request) throws Exception {    	
		String attachName = request.getParameter("attachName");
		String tag = request.getParameter("tag");
		
		if ("img".equals(tag)) {
			videoPath += "img/";
		} else if ("video".equals(tag)) {
			videoPath += "video/";
		} else if("file".equals(tag)){
			videoPath += "file/";
		}
		
		File file = new File(videoPath + attachName);
		file.delete();
		
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
    	vi.setCreatetime(new Date());
    	vi.setUpdatetime(new Date());
    	vi.setDescription(description);
    	vi.setIsown(isown);
    	vi.setClick(0);
    	
    	videoService.saveMedia(vi);
    	
    	return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }
	

}
