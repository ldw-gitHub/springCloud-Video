package com.itcast.dw.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;

public class Priority {
	
	private static volatile boolean notStart = true;
	private static volatile boolean notEnd = true;
	
	public static void main(String[] args) {
		List<Job> jobs = new ArrayList<Job>();
		for(int i = 0; i < 10 ;i++){
			
		}
	}

}
