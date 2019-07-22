package com.itcast.dw.test.netty;

import java.nio.ByteBuffer;

public class ByteBufTest {
	
	/**
	 * netty
	 * ByteBuf、Channel、Pipeline、Event
	 * 
	 * 
	 */
	
	private static ByteBuffer byteBuffer;
	
	private static int byteSize = 1000;
	
	public static void main(String[] args) {
		byteBuffer = ByteBuffer.allocate(byteSize);
		
		
	}

}
