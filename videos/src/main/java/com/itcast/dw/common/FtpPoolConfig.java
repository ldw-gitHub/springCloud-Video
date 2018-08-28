package com.itcast.dw.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;

/**
 * ftp配置参数对象 继承自GenericObjectPoolConfig
 * 
 * @author jelly
 *
 */
@Component
public class FtpPoolConfig extends GenericObjectPoolConfig {

	private static String host;// 主机名
	private static int port = 21;// 端口
	private static String username;// 用户名
	private static String password;// 密码
	private static String ftpPath;

	private static int connectTimeOut = 5000;// ftp 连接超时时间 毫秒
	private static String controlEncoding = "GBK";
	private static int bufferSize = 10240;// 缓冲区大小
	
	private static int fileType = FTP.STREAM_TRANSFER_MODE;// 传输数据格式 2表binary二进制数据
	private static int dataTimeout = 120000;
	private static boolean useEPSVwithIPv4 = false;
	private static boolean passiveMode = true;// 是否启用被动模式

	static {
		Properties properties = new Properties();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("system.properties");
		try {
			properties.load(is);
			setHost(properties.getProperty("ftpServerIP"));
			setUsername(properties.getProperty("ftpUsername"));
			setPassword(properties.getProperty("ftpPassword"));
			setFtpPath(properties.getProperty("ftpPath"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		FtpPoolConfig.host = host;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		FtpPoolConfig.port = port;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		FtpPoolConfig.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		FtpPoolConfig.password = password;
	}

	public static int getConnectTimeOut() {
		return connectTimeOut;
	}

	public static void setConnectTimeOut(int connectTimeOut) {
		FtpPoolConfig.connectTimeOut = connectTimeOut;
	}

	public static String getControlEncoding() {
		return controlEncoding;
	}

	public static void setControlEncoding(String controlEncoding) {
		FtpPoolConfig.controlEncoding = controlEncoding;
	}

	public static int getBufferSize() {
		return bufferSize;
	}

	public static void setBufferSize(int bufferSize) {
		FtpPoolConfig.bufferSize = bufferSize;
	}

	public static int getFileType() {
		return fileType;
	}

	public static void setFileType(int fileType) {
		FtpPoolConfig.fileType = fileType;
	}

	public static int getDataTimeout() {
		return dataTimeout;
	}

	public static void setDataTimeout(int dataTimeout) {
		FtpPoolConfig.dataTimeout = dataTimeout;
	}

	public static boolean isUseEPSVwithIPv4() {
		return useEPSVwithIPv4;
	}

	public static void setUseEPSVwithIPv4(boolean useEPSVwithIPv4) {
		FtpPoolConfig.useEPSVwithIPv4 = useEPSVwithIPv4;
	}

	public static boolean isPassiveMode() {
		return passiveMode;
	}

	public static void setPassiveMode(boolean passiveMode) {
		FtpPoolConfig.passiveMode = passiveMode;
	}

	public static String getFtpPath() {
		return ftpPath;
	}

	public static void setFtpPath(String ftpPath) {
		FtpPoolConfig.ftpPath = ftpPath;
	}





}
