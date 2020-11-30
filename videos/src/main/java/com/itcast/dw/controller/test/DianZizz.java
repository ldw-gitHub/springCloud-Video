package com.itcast.dw.controller.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @description
 * @author: liudawei
 * @date: 2020/8/25 9:49
 */
public class DianZizz {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping(value = "/loginDz")
  public String hi() throws Exception {

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

    JSONObject json = restTemplate.postForObject("https://10.248.77.226:8443/license-app/v1/security/login",
      new HttpEntity<>(params.toJSONString(), requestHeaders), JSONObject.class);

    System.out.println(json.toJSONString());

    return json.toJSONString();
  }
}
