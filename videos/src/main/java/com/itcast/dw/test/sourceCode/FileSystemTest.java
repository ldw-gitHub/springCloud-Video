package com.itcast.dw.test.sourceCode;

import java.io.File;

public class FileSystemTest {
	
	/**
	 * java得跨平台性，统一处理这些不同平台文件系统得差异
	 * 
	 * FileSystem
	 * 
	 * WinNTFileSystem
	 * 
	 * UnixFileSystem
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		String filePath = "";
		File file = new File(filePath);
	}
	
	/**
	 * 
	 * 判断是不是斜杠
	 * @param c
	 * @return 
	 * @date 2019年7月16日
	 * @author liudawei
	 */
	private boolean isSlash(char c){
		return (c == '\\') || (c == '/');
	}
	
	/**
	 * 
	 * 判断是不是字母
	 * @param c
	 * @return 
	 * @date 2019年7月16日
	 * @author liudawei
	 */
	private boolean isLetter(char c){
		return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
	}

}
