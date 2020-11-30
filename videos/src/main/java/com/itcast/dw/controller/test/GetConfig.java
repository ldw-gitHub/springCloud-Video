package com.itcast.dw.controller.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @description
 * @author: liudawei
 * @date: 2020/8/13 14:14
 */
@RestController
public class GetConfig {

  /*  @Autowired
    ConfigModel configModel;*/
  @Autowired
  RestTemplate restTemplate;

  @GetMapping(value = "/hi")
  public String hi() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    System.setProperty("http.proxyHost", "127.0.0.1");
    System.setProperty("https.proxyHost", "127.0.0.1");
    System.setProperty("http.proxyPort", "8888");
    System.setProperty("https.proxyPort", "8888");


    JSONObject params = new JSONObject();
    params.put("account", "sz_bdc_szsbdcdjxxcxappxt");
    params.put("password", "DzWHlaodTKbJeCz");
    params.put("app_key", "TqqEpzSqhwhbBEC");
    params.put("app_secret", "zWBSuzZmqfYEnJY");
    params.put("org_code", "000000000");
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.add("Content-Type", "application/json");
//    JSONObject json = getRestTemplate().postForObject("https://10.248.77.226:8443/license-app/v1/security/login",
//      new HttpEntity<String>(params.toJSONString(), requestHeaders), JSONObject.class);

    ResponseEntity<JSONObject> json = getRestTemplate().exchange("https://10.248.77.226:8443/license-app/v1/security/login", HttpMethod.POST,
      new HttpEntity<String>(params.toJSONString(), requestHeaders), JSONObject.class,"");

    System.out.println(json.getBody().toJSONString());

//    return "my name is " + configModel.getName() + " ,age is " + configModel.getAge();
    return json.getBody().toJSONString();
  }


  @GetMapping(value = "/doGetHttps")
  public String doGetHttps() throws Exception {
    System.setProperty("http.proxyHost", "127.0.0.1");
    System.setProperty("https.proxyHost", "127.0.0.1");
    System.setProperty("http.proxyPort", "8888");
    System.setProperty("https.proxyPort", "8888");

    String url = "https://free-api.heweather.com/v5/forecast?city=CN101080101&key=5c043b56de9f4371b0c7f8bee8f5b75e";
    String resp = restTemplate.getForObject(url, String.class);
    System.out.println(resp);

    return resp;
  }

  @GetMapping(value = "/doGetHttp")
  public String doGetHttp() throws Exception {
    System.setProperty("http.proxyHost", "127.0.0.1");
    System.setProperty("https.proxyHost", "127.0.0.1");
    System.setProperty("http.proxyPort", "8888");
    System.setProperty("https.proxyPort", "8888");

    String url = "https://free-api.heweather.com/v5/forecast?city=CN101080101&key=5c043b56de9f4371b0c7f8bee8f5b75e";
    String resp = new RestTemplate().getForObject(url, String.class);
    System.out.println(resp);

    return resp;
  }


  public static RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    System.setProperty("http.proxyHost", "127.0.0.1");
    System.setProperty("https.proxyHost", "127.0.0.1");
    System.setProperty("http.proxyPort", "8888");
    System.setProperty("https.proxyPort", "8888");
    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
      @Override
      public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        return true;
      }
    }).build();

    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,
      new String[]{"TLSv1"},
      null,
      NoopHostnameVerifier.INSTANCE);

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
