package com.itcast.dw.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	static Logger log = LoggerFactory.getLogger(UploadFile.class);

	public UploadFile() {
		this.ftpClient = new FTPClient();
		this.ftpClient.setControlEncoding("GBK");
		login();
	}

	public void login() {
		try {
			this.ftpClient.connect(ftpIp, Integer.parseInt(ftpPort));
			this.ftpClient.login(ftpUsername, ftpPassword);
		} catch (FTPConnectionClosedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("连接到ftp服务器：" + ftpIp + " 成功..开始登录");
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

	public void deleteFile(String fileName) {
		if (fileName != null)
			try {
				this.ftpClient.dele(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
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

			uploadFlag = ftpClient.storeFile(newFileName, in);
			if (uploadFlag) {
				log.info("file upload success");
			} else {
				log.info("file upload error");
			}

		} catch (Exception e) {
			log.info("fail upload!!!");
			e.printStackTrace();
			try {
				in.close();
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e1) {
				log.info("close io stream error");
				e1.printStackTrace();
			}
		} finally {
			try {
				in.close();
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e1) {
				log.info("close io stream error");
				e1.printStackTrace();
			}
		}
		return uploadFlag;
	}
	
	public static boolean mergeFiles(String fpaths, String resultPath) {
		resultPath = ftpPath + resultPath;
		fpaths = ftpPath + "/" + fpaths;
		File file = new File(fpaths);
	    File[] fileList = file.listFiles();

	    File resultFile = new File(resultPath);

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
	        deleteDirAndFile(file);
	        outputStream.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }

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
