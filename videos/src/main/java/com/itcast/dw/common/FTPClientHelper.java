package com.itcast.dw.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ftp客户端辅助bean
 * 
 * @author jelly
 *
 */
@Component
public class FTPClientHelper {

	@Autowired
	private FTPClientPool ftpClientPool;

	public void setFtpClientPool(FTPClientPool ftpClientPool) {
		this.ftpClientPool = ftpClientPool;
	}

	/**
	 * 下载 remote文件流
	 * 
	 * @param remote
	 *            远程文件
	 * @return 字节数据
	 * @throws Exception
	 */
	public byte[] retrieveFileStream(String remote) throws Exception {
		FTPClient client = null;
		InputStream in = null;
		try {
			// long start =System.currentTimeMillis();
			client = ftpClientPool.borrowObject();
			in = client.retrieveFileStream(remote);
			// long end =System.currentTimeMillis();
			// System.out.println("ftp下载耗时(毫秒):"+(end-start));
			return CommonUtil.inputStreamToByteArray(in);
		} finally {
			if (in != null) {
				in.close();
			}
			if (!client.completePendingCommand()) {
				client.logout();
				client.disconnect();
				ftpClientPool.getPool().invalidateObject(client);
			}
			ftpClientPool.returnObject(client);

		}
	}

	/**
	 * 创建目录 单个不可递归
	 * 
	 * @param pathname
	 *            目录名称
	 * @return
	 * @throws Exception
	 */
	public boolean makeDirectory(String pathname) throws Exception {

		FTPClient client = null;
		try {
			client = ftpClientPool.borrowObject();
			return client.makeDirectory(pathname);
		} finally {
			ftpClientPool.returnObject(client);
		}
	}
	
	/**
	 * 创建目录 单个不可递归
	 * 
	 * @param pathname
	 *            目录名称
	 * @return
	 * @throws Exception
	 */
	public boolean haveDirectory(String pathname) throws Exception {
		
		FTPClient client = null;
		try {
			client = ftpClientPool.borrowObject();
			// 检查上传路径是否存在 如果不存在返回false
			boolean isChangeWork = client.changeWorkingDirectory(pathname);
			if (!isChangeWork) {
				// 创建上传的路径 该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
				boolean isMade = client.makeDirectory(pathname);
				if (!isMade) {
					return isChangeWork;
				}
				isChangeWork = client.changeWorkingDirectory(pathname);
			}
			return isChangeWork;
		} finally {
			ftpClientPool.returnObject(client);
		}
	}

	/**
	 * 删除目录，单个不可递归
	 * 
	 * @param pathname
	 * @return
	 * @throws IOException
	 */
	public boolean removeDirectory(String pathname) throws Exception {
		FTPClient client = null;
		try {
			client = ftpClientPool.borrowObject();
			return client.removeDirectory(pathname);
		} finally {
			ftpClientPool.returnObject(client);
		}
	}

	/**
	 * 删除文件 单个 ，不可递归
	 * 
	 * @param pathname
	 * @return
	 * @throws Exception
	 */
	public boolean deleteFile(String pathname) throws Exception {

		FTPClient client = null;
		try {
			client = ftpClientPool.borrowObject();
			return client.deleteFile(pathname);
		} finally {
			ftpClientPool.returnObject(client);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param remote
	 * @param local
	 * @return
	 * @throws Exception
	 */
	public boolean storeFile(String remote, InputStream local) throws Exception {
		FTPClient client = null;
		try {
			client = ftpClientPool.borrowObject();
			return client.storeFile(remote, local);
		} finally {
			ftpClientPool.returnObject(client);
		}
	}

}
