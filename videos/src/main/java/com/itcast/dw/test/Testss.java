package com.itcast.dw.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class Testss {
	
	public static void main(String[] args) throws MalformedURLException {/*
		String ip = "47.107.95.112"; // 服务器IP地址//47.107.95.112
		String port = "21"; // 服务器IP地址
		String username = "ftpldw"; // 用于登陆服务器的用户名
		String password = "ldw520520"; // 登陆密码
		String remoteDirectoryPath = "video/liudianb"; // 远程文件夹的绝对路径
		String savePath = ".."; // 远程文件夹的绝对路径
		String remoteFileName = "teste.mp4"; // 将本地文件上传到服务器后文件的名字

		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(ip,Integer.parseInt(port));
			boolean isLogin = ftpClient.login(username, password);
			ftpClient.setControlEncoding("UTF-8");
			//ftpClient.enterLocalActiveMode();// 设置passiveMode传输
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			System.out.println("登陆成功？ " + isLogin);
			
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.out.println("连接ftp服务器失败");
			}
			
			
			boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory(remoteDirectoryPath);
			System.out.println("第0次：change文件夹 ： " + changeWorkingDirectory);
			FTPFile[] ftpFiles = ftpClient.listFiles();
			System.out.println("获取文件长度：" + ftpFiles.length);
			for (int i = 0; i <ftpFiles.length;i++) {
 				//解决中文乱码问题，两次解码
 				byte[] bytes=ftpFiles[i].getName().getBytes("iso-8859-1");
 			    String fn=new String(bytes,"GBK");
				System.out.println("合并文件名称：" + fn);
				InputStream retrieveFileStream = ftpClient.retrieveFileStream(fn);
				byte[] bytews = IOUtils.toByteArray(retrieveFileStream);
				InputStream FileStream = new ByteArrayInputStream(bytews);
				boolean savechangeWorkingDirectory = ftpClient.changeWorkingDirectory(savePath);
				System.out.println("改变到合并文件目录：" + savechangeWorkingDirectory);
				retrieveFileStream.close();
				ftpClient.completePendingCommand();
				
				boolean appendFile = ftpClient.appendFile(new String(remoteFileName.getBytes("GBK"),"iso-8859-1"), FileStream);
				FileStream.close();
				
				boolean changeWorkingDirectorys = ftpClient.changeWorkingDirectory("liudianb");
				System.out.println("第"+i+"次：change文件夹 ： " + changeWorkingDirectorys);
				boolean deleteFile = ftpClient.deleteFile(fn);
				System.out.println("文件合并：" + appendFile + deleteFile);
			}
			ftpClient.changeWorkingDirectory(savePath);
			boolean removeDirectory = ftpClient.removeDirectory("liudianb");
			System.out.println("执行完毕！" + removeDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	*/}

}
