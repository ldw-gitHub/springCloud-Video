package com.itcast.dw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableZuulProxy
@ComponentScan("com.itcast.dw")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZuulApp {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApp.class, args);
	}
	
	/**  
     * 文件上传配置  
     * @return  
     */  
/*    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        factory.setLocation("E:/uploadIoTmp");
        //单个文件最大  
        factory.setMaxFileSize("2097152KB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("2097152KB");  
        //当文件大于10M磁盘写入
        factory.setFileSizeThreshold("10240KB");
        return factory.createMultipartConfig();  
    } */
    
    /**
     * 大文件上传
     * @return
     */
/*    @Bean
    public SimpleClientHttpRequestFactory httpClientFactory() {
    	SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
    	httpRequestFactory.setBufferRequestBody(false);
    	httpRequestFactory.setChunkSize(10240);
    	return httpRequestFactory;
    }
    
    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory httpClientFactory) {
           RestTemplate restTemplate = new RestTemplate(httpClientFactory);
             return restTemplate;
    }*/
	
}
