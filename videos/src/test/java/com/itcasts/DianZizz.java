package com.itcasts;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * @description
 * @author: liudawei
 * @date: 2020/8/25 9:49
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class DianZizz {
  public static void main(String[] args) throws Exception {
    // 登入获取access_token
    JSONObject jsonObject = dianZzzLogin();

    // 根据用证码提取一个电子证照信息
//    dianZzzGetLicense(jsonObject.get("access_token") + "", "20200825092459451NC010223749_440300202000082VM3");

    // 依职能查验用证
    dianZzzAuth(jsonObject.get("access_token") + "");

    // 获取当前登入用户信息
//    dianZzzGetcurrentUser(jsonObject.get("access_token") + "");
  }

  /**
   * @description: 根据用证码提取一个电子证照信息
   * @author: liudawei
   * @date: 2020/8/25 14:20
   * @param: access_token
   * @param: authCode  电子证照用证码
   * @return: com.alibaba.fastjson.JSONObject
   */
  public static JSONObject dianZzzGetLicense(String access_token, String authCode) throws Exception {
    String url = "http://www.szreorc.com:8090/license-app/v1/license/get_license?access_token=" + access_token + "&auth_code=" + authCode;

    JSONObject json = restTemplate().getForObject(url, JSONObject.class);

    System.out.println(json.toJSONString());

    return json;

    //{"access_token":"8287065d-2600-4a21-9c95-dbc6c906db65","response_id":"5143d478-ee92-4dca-af15-1f87be756e86","ack_code":"SUCCESS","correlation_id":"e3cd0676-cd6e-425e-a98c-dd67af52b3bd","errors":[]}
  }

  /**
   * @description: 获取当前登入用户信息
   * @author: liudawei
   * @date: 2020/8/25 14:21
   * @param: access_token
   * @return: com.alibaba.fastjson.JSONObject
   */
  public static JSONObject dianZzzGetcurrentUser(String access_token) throws Exception {
    String url = "http://www.szreorc.com:8090/license-app/v1/security/get_current_user?access_token=" + access_token;

    JSONObject json = restTemplate().getForObject(url, JSONObject.class);

    System.out.println(json.toJSONString());

    return json;

    //{"access_token":"8287065d-2600-4a21-9c95-dbc6c906db65","response_id":"5143d478-ee92-4dca-af15-1f87be756e86","ack_code":"SUCCESS","correlation_id":"e3cd0676-cd6e-425e-a98c-dd67af52b3bd","errors":[]}
  }

  /**
   * @description: 依职能查验用证
   * @author: liudawei
   * @date: 2020/8/25 14:21
   * @param: access_token
   * @return: com.alibaba.fastjson.JSONObject
   */
  public static JSONObject dianZzzAuth(String access_token) throws Exception {
    JSONObject params = new JSONObject();
    params.put("identity_number", "441481198107250878");
    params.put("service_item_code", "90000500169555062213440300");
    params.put("service_item_name", "不动产登记资料查询");
    JSONObject userinfo = new JSONObject();
    userinfo.put("account", "sz_bdc_szsbdcdjxxcxappxt");
    userinfo.put("name", "栾石凯");
    userinfo.put("division", "广东省深圳市");
    userinfo.put("division_code", "440300");
    userinfo.put("service_org", "深圳市不动产登记中心");
    userinfo.put("role", "深圳市不动产登记信息查询APP系统");
    userinfo.put("identity_num", "440301198202132310");
    params.put("operator", userinfo);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.add("Content-Type", "application/json");

    String url = "http://www.szreorc.com:8090/license-app/v1/license/auth?access_token=" + access_token;

    System.out.println(params.toJSONString());
    JSONObject json = restTemplate().postForObject(url, new HttpEntity<>(params.toJSONString(), requestHeaders), JSONObject.class);

    System.out.println(json.toJSONString());

    return json;

    //{"access_token":"8287065d-2600-4a21-9c95-dbc6c906db65","response_id":"5143d478-ee92-4dca-af15-1f87be756e86","ack_code":"SUCCESS","correlation_id":"e3cd0676-cd6e-425e-a98c-dd67af52b3bd","errors":[]}
  }

  /**
   * @description: 登入
   * @author: liudawei
   * @date: 2020/8/25 14:22
   * @param:
   * @return: com.alibaba.fastjson.JSONObject
   */
  public static JSONObject dianZzzLogin() throws Exception {
    JSONObject params = new JSONObject();
    params.put("account", "sz_bdc_szsbdcdjxxcxappxt");
    params.put("password", "DzWHlaodTKbJeCz");
    params.put("app_key", "TqqEpzSqhwhbBEC");
    params.put("app_secret", "zWBSuzZmqfYEnJY");
    params.put("org_code", "000000000");
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.add("Content-Type", "application/json");

    String url = "http://www.szreorc.com:8090/license-app/v1/security/login";
//    String url = "https://10.248.77.226:8443/license-app/v1/security/login";

    JSONObject json = restTemplate().postForObject(url, new HttpEntity<>(params.toJSONString(), requestHeaders), JSONObject.class);

    System.out.println(json.toJSONString());

    return json;

    //{"access_token":"8287065d-2600-4a21-9c95-dbc6c906db65","response_id":"5143d478-ee92-4dca-af15-1f87be756e86","ack_code":"SUCCESS","correlation_id":"e3cd0676-cd6e-425e-a98c-dd67af52b3bd","errors":[]}
  }


  public static RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
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
