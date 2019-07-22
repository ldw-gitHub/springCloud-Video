package com.itcast.dw.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class UploadFile {

	private static String ftpIp;
	private static String ftpPort;
	private static String ftpUsername;
	private static String ftpPassword;
	private static String ftpPath;
	private FTPClient ftpClient = null;

	static {
		Properties properties = new Properties();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("system.properties");
		try {
			properties.load(is);
			ftpIp = properties.getProperty("ftpServerIP");
			ftpPort = properties.getProperty("ftpServerPort");
			ftpUsername = properties.getProperty("ftpUsername");
			ftpPassword = properties.getProperty("ftpPassword");
			ftpPath = properties.getProperty("ftpPath");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UploadFile.class);

	public UploadFile() {
		this.ftpClient = new FTPClient();
		this.ftpClient.setControlEncoding("GBK");
		login();
	}

	public void login() {
		try {
			this.ftpClient.connect(ftpIp, Integer.parseInt(ftpPort));
			this.ftpClient.login(ftpUsername, ftpPassword);
			System.out.println("连接到ftp服务器：" + ftpIp + " 成功..开始登录");
		} catch (FTPConnectionClosedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createDirectroy(String pathname) {
		try {
			this.ftpClient.makeDirectory(pathname);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeDir(String remoteDir) {
		try {
			this.ftpClient.changeWorkingDirectory(remoteDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("变更工作目录为:" + remoteDir);
	}

	public boolean deleteFile(String fileName) {
		boolean flag = false;
		if (fileName != null)
			try {
				this.ftpClient.dele(fileName);
				return flag;
			} catch (IOException e) {
				e.printStackTrace();
			}
		return flag;
	}

	/**
	 * 获取某个FTP目录下的所有文件
	 */
	public FTPFile[] getFiles() throws Exception {
		return ftpClient.listFiles();
	}

	/**
	 * 文件下载功能
	 * 
	 * @param remoteFile
	 *            远程文件
	 * @param out
	 *            输出流
	 * @throws Exception
	 */
	public void downloadFile(String remoteFile, OutputStream out) throws Exception {
		ftpClient.retrieveFile(remoteFile, out);
	}

	public void close() {
		try {
			if (this.ftpClient != null && this.ftpClient.isConnected()) {
				this.ftpClient.logout();
				this.ftpClient.disconnect();
				log.info("ftp 连接关闭");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean UploadFileToServer(InputStream in, String newFileName, String path) {
		boolean uploadFlag = false;
		try {
			ftpClient.enterLocalPassiveMode();// 设置passiveMode传输
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);// 设置以二进制流的方式传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				log.info("连接ftp服务器失败");
				return uploadFlag;
			}
			log.info("connect ftp success");

			// 检查上传路径是否存在 如果不存在返回false
			boolean isChangeWork = ftpClient.changeWorkingDirectory(path);
			if (!isChangeWork) {
				// 创建上传的路径 该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
				boolean isMade = ftpClient.makeDirectory(path);
				if (!isMade) {
					log.info("目录创建失败");
					return uploadFlag;
				}
				isChangeWork = ftpClient.changeWorkingDirectory(path);
			}

			uploadFlag = ftpClient.storeFile(newFileName, in);//java.net.NoRouteToHostException: No route to host (Host unreachable)
			if (uploadFlag) {
				log.info("file upload success");
			} else {
				log.info("file upload error");
			}

		} catch (Exception e) {
			log.info("fail upload!!!");
			e.printStackTrace();
			uploadFlag = false;
		} 
		return uploadFlag;
	}
	
	public boolean mergeFiles(String fpaths, String resultPath,String attachName){
		try {
			ftpClient.enterLocalPassiveMode();// 设置passiveMode传输
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("UTF-8");
			
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				log.info("连接ftp服务器失败");
				return false;
			}
			log.info("connect ftp success");
			log.info("resultPath = " + resultPath + "---attachName = " +attachName);// video  [20181212033239299]VID_20181122_192905.mp4
			
			ftpClient.changeWorkingDirectory(fpaths);// video/VID_20181122_192905
			FTPFile[] listFiles = ftpClient.listFiles();
			
			String folderName = fpaths.substring(fpaths.indexOf("/")+1, fpaths.length());
			for(int i = 0; i < listFiles.length; i ++){
				byte[] bytes=listFiles[i].getName().getBytes("iso-8859-1");
 			    String fn=new String(bytes,"GBK");
 			    log.info(fn);
				
				InputStream retrieveFileStream = ftpClient.retrieveFileStream(fn);
				byte[] bytews = IOUtils.toByteArray(retrieveFileStream);
				InputStream FileStream = new ByteArrayInputStream(bytews);
				boolean savechangeWorkingDirectory = ftpClient.changeWorkingDirectory("..");
				log.info(savechangeWorkingDirectory);
				
				retrieveFileStream.close();
				ftpClient.completePendingCommand();
				
				boolean appendFile = ftpClient.appendFile(new String(attachName.getBytes("GBK"),"iso-8859-1"), FileStream);
				log.info(appendFile);
				FileStream.close();
				
				boolean changeWorkingDirectorys = ftpClient.changeWorkingDirectory(folderName);
				log.info(changeWorkingDirectorys);
				ftpClient.deleteFile(fn);
			}
			log.info("合并完成！");
			ftpClient.changeWorkingDirectory("..");
			ftpClient.removeDirectory(folderName);
			
		} catch (IOException e) {
			log.error(e,e);
			return false;
		}

	/*    File resultFile = new File(resultPath);
	    if(!resultFile.exists()){
	    	try {
				resultFile.createNewFile();
				resultFile.setWritable(true, false);
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
	    }

	    try {
	        int bufSize = 1024;
	        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(resultFile));
	        byte[] buffer = new byte[bufSize];

	        for (int i = 0; i < fileList.length; i ++) {
	            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileList[i]));
	            int readcount;
	            while ((readcount = inputStream.read(buffer)) > 0) {
	                outputStream.write(buffer, 0, readcount);
	            }
	            inputStream.close();
	        }
	        outputStream.flush();
	        deleteDirAndFile(file);
	        outputStream.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }*/

	    return true;
	}
	
	public static boolean deleteDirAndFile(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				try {
					deleteDirAndFile(new File(dir, children[i]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return dir.delete(); // 目录此时为空，可以删除
	}
	

}
