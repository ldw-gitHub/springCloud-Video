package com.itcast.dw.text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) throws IOException {
	   String path = "video/VID_20181122_192905";
	   System.out.println(path.substring(path.indexOf("/")+1, path.length()));
		
	}
}
