package com.itcast.dw.text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) throws IOException {
		String path = "E:/ftp/ldw.txt";
		File files = new File(path);
	/*	if(!files.exists()){
			try {
				files.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		System.out.println(files.getAbsolutePath());
		try {
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(files));
			outputStream.write("test".getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
