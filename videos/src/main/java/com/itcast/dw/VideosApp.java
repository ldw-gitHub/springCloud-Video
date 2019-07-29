package com.itcast.dw;

import java.io.File;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.itcast.dw.dao")
@SpringBootApplication()
public class VideosApp {
	public static void main(String[] args) {
		SpringApplication.run(VideosApp.class, args);	
	}
	
	/**  
     * 文件上传配置  
     * @return  
     */  
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //缓存路径
       //factory.setLocation("E:/uploadIoTmp");
        File file = new File("E:/uploadIoTmp");
        file.setWritable(true,false); 
        if(!file.exists()){
        	file.mkdirs();
        }
        factory.setLocation("E:/uploadIoTmp");//linux
        //单个文件最大  
        factory.setMaxFileSize("2097152KB"); //KB,MB   2G
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("2097152KB");
        //当文件大于10M磁盘写入
        factory.setFileSizeThreshold("10240KB");
        return factory.createMultipartConfig();  
    } 
    
    
    /**
     * 大文件上传
     * @return
     */
 /*   @Bean
    public SimpleClientHttpRequestFactory httpClientFactory() {
    	SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
    	httpRequestFactory.setBufferRequestBody(false);
    	return httpRequestFactory;
    }
    
    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory httpClientFactory) {
           RestTemplate restTemplate = new RestTemplate(httpClientFactory);
             return restTemplate;
    }*/
    
    
    
    
}
