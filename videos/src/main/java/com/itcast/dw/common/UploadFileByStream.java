package com.itcast.dw.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class UploadFileByStream {
	
	
	public static boolean saveFile(InputStream in,String filePath,String fileName){
		boolean flag = false;
		File fileDir = new File(filePath);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(filePath + File.separator + fileName));
			byte[] tempbytes = new byte[1024];
			int byteread = 0;
			while((byteread = in.read(tempbytes)) != -1){
				out.write(tempbytes);
			}
			
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.flush();
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 文件合并
	 * @param filePath
	 * @param fileName
	 * @param count
	 * @param saveFile
	 * @throws IOException 
	 * @date 2019年7月17日
	 * @author liudawei
	 */
	public static boolean mergeFile(String filePath,String fileName,String savePath){
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(new File(savePath +File.separator + fileName));
			
			File allFile = new File(filePath);
			File[] listFiles = allFile.listFiles();
			
			if(listFiles.length > 0){
				for(int i = 0;i < listFiles.length;i++){
					FileInputStream fi = new FileInputStream(listFiles[i]);
					byte[] bytes = new byte[(int) listFiles[i].length()];
					fi.read(bytes);
					fo.write(bytes);
					fo.flush();
					fi.close();
					listFiles[i].delete();
				}
			}
			allFile.delete();
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return false;
	}
	
	/**
	 * 
	 * 文件分割
	 * @param savePath
	 * @param saveName
	 * @param filePath
	 * @return 
	 * @date 2019年7月17日
	 * @author liudawei
	 */
	public static int cutFile(String savePath, String saveName, String filePath) {
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}

		File file = new File(filePath);

		FileOutputStream fo = null;
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream(file);
			FileChannel channel = fileIn.getChannel();
			double size = channel.size() / (1024 * 1024);
			int count = (int) Math.ceil(size / 100);
			System.out.println("分片大小 = " + count);
			System.out.println("file Total Space = " + size + "M");

			int mergeSize = 100 * 1024 * 1024;
			ByteBuffer byteBuf = ByteBuffer.allocate(mergeSize);
			byte[] bytes = new byte[mergeSize];

			for (int i = 0; i < count; i++) {
				int read = -1;
				if( i == count - 1){
					byteBuf = ByteBuffer.allocate((int) (channel.size() - (mergeSize*(count -1))));
					bytes = new byte[(int) (channel.size() - (mergeSize*(count -1)))];
					read = channel.read(byteBuf);
				}else{
					read = channel.read(byteBuf);
				}
				if (read != -1) {
					fo = new FileOutputStream(new File(savePath + File.separator + saveName + i + ".mp4"));
					byteBuf.flip();
					byteBuf.get(bytes);
					byteBuf.clear();
					fo.write(bytes);
					fo.flush();
				}
			}
			
			return count;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fo.close();
				fileIn.close();
				System.out.println("关闭流");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

}
