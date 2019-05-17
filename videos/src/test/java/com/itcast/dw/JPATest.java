package com.itcast.dw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itcast.dw.model.VideoInfo;
import com.itcast.dw.service.VideoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {
	
	@Autowired
	VideoService videoService;
	
	@Test
	public void getVideo() throws Exception{
		VideoInfo selectOne = videoService.selectOne(null);
		System.out.println(selectOne.getCreatetime());
	}
	
	

}
