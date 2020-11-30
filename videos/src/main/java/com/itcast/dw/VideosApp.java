package com.itcast.dw;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
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
/*    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //缓存路径
       //factory.setLocation("E:/uploadIoTmp");
        File file = new File("E:/uploadIoTmp");
        file.setWritable(true,false);
        if(!file.exists()){
        	file.mkdirs();
        }
        factory.setLocation("E:/uploadIoTmp");
        //linux
        //单个文件最大
        factory.setMaxFileSize(DataSize.parse("2097152KB"));
        //KB,MB   2G
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("2097152KB"));
        //当文件大于10M磁盘写入
        factory.setFileSizeThreshold(DataSize.parse("10240KB"));
        return factory.createMultipartConfig();
    }*/


  /**
   * 大文件上传
   *
   * @return
   */
/*    @Bean
    public SimpleClientHttpRequestFactory httpClientFactory() {
    	SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
    	httpRequestFactory.setBufferRequestBody(false);
    	return httpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory httpClientFactory) {
           RestTemplate restTemplate = new RestTemplate(httpClientFactory);
             return restTemplate;
    }    */
  @Bean
  public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
      .loadTrustMaterial(null, acceptingTrustStrategy)
      .build();

    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

    CloseableHttpClient httpClient = HttpClients.custom()
      .setSSLSocketFactory(csf)
      .build();

    HttpComponentsClientHttpRequestFactory requestFactory =
      new HttpComponentsClientHttpRequestFactory();

    requestFactory.setHttpClient(httpClient);
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    return restTemplate;
  }


}
