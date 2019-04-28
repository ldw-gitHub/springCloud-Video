/**
 * 
 * @date 2019年4月26日
 */
package com.itcast.dw.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.dw.info.ResultInfo;

/**
 * 一句话功能简述
 * @author liudawei
 */
@RestController
public class LogFileController {
	
	private Logger log = LoggerFactory.getLogger(FileController.class);
	public static final String logFoldPath = "/var/ftp/pub/log";
	public static final String logFilePath = "/var/ftp/pub/log/videoLog.log";
	
	@PostMapping(value = "/adminLogFile")
	public ResultInfo<?> loadAdminLog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//String path = System.getProperty("java.class.path");
		//String path = LogFileController.class.getClassLoader().getResource("").getPath();
		String path = LogFileController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		log.info("path ============= " + path);
		//file:/javaProject/video/videos.jar!/BOOT-INF/classes!/
		path = (path.substring(0, path.lastIndexOf("videos.jar")) + "nohup.out").replace("file:", "");
		log.info("path ============= " + path);
		///javaProject/video/nohup.out
		
		//读取文件(缓存字节流)
		File oldfile = new File(path); 
		if(oldfile.exists()){
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(oldfile));
			//写入相应的文件
			File file = new File(logFoldPath);
			if(!file.exists()){
				file.mkdirs();
			}
			File logFile = new File(logFilePath);
			if(logFile.exists()){
				logFile.delete();
			}
			logFile.createNewFile();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(logFile));
			//读取数据
			//一次性取多少字节
			byte[] bytes = new byte[2048*100];
			//接受读取的内容(n就代表的相关数据，只不过是数字的形式)
			int n = -1;
			//循环取出数据
			while ((n = in.read(bytes,0,bytes.length)) != -1) {
				//写入相关文件
				out.write(bytes, 0, n);
			}
			//清楚缓存
			out.flush();
			//关闭流
			in.close();
			out.close();
			return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
		}
		return new ResultInfo<>(ResultInfo.FAILURE, "文件不存在");
        
	}
	
	@PostMapping("/deleteLog")
	public ResultInfo<?> delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//读取文件(缓存字节流)
		File file = new File(logFilePath); 
		if(file.exists()){
			file.delete();
		}
		return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
	}
	

}
