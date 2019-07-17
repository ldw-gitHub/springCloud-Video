package com.itcast.dw.test.sourceCode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.Character.UnicodeBlock;

public class InputStreamTest {
	
	/**
	 * 
	 * 一句话功能简述
	 * @param args 
	 * @date 2019年7月16日
	 * @author liudawei
	 * 
	 * InputStream implements Closeable
	 * 
	 * read()
	 * @throws IOException 
	 * 
	 * 
	 * 
	 */
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream in = null;
		try {
			in = new FileInputStream(new File("C:/Users/20190322/Desktop/应急管理局短信发送平台.docx"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedInputStream bi = new BufferedInputStream(in);
		
		byte[] buffer = new byte[3];
		
		int byteRead = 0;
		String code = "gb2312";
		while ((byteRead = bi.read(buffer)) != -1) {
			
			if(buffer[0] == -1 && buffer[1] == -2){
				code = "UTF-16";
			}else if(buffer[0] == -2 && buffer[1] == -1){
				code = "Unicode";
			}else if(buffer[0] == -17 && buffer[1] == -69 && buffer[2] == -65){
				code = "UTF-8";
			}
			String text = new String(buffer,0,byteRead,code);
			System.out.println(text);
		}
		
		bi.close();
		
		
	}
	
	public String readerFile(String filepath){
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return "";
	}

}
