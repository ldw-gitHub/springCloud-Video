package com.itcast.dw;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableFeignClients
@ComponentScan("com.itcast.dw")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
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
        factory.setLocation("E:/uploadIoTmp");
        //单个文件最大  
        factory.setMaxFileSize("2097152KB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("2097152KB");  
        return factory.createMultipartConfig();  
    } 
    
    /**
     * 大文件上传
     * @return
     */
    @Bean
    public SimpleClientHttpRequestFactory httpClientFactory() {
    	SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
    	httpRequestFactory.setBufferRequestBody(false);
    	return httpRequestFactory;
    }
    
    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory httpClientFactory) {
           RestTemplate restTemplate = new RestTemplate(httpClientFactory);
             return restTemplate;
    }
    
    
    
    
}
