package com.itcast.dw.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http请求
 * 
 * @author sky_luan
 */
public class HttpClientUtils {
	
	protected static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	private static final String CHARSET = "utf-8";
	private static final Integer SOCKET_TIME_OUT = 10000;
	private static final Integer CONNECT_TIME_OUT = 10000;
	
	/**
	 * POST请求
	 * @param headers 请求头
	 * @param params 参数列表
	 * @param url 请求URL
	 * @return String
	 * @throws Exception 
	 * @date 2018年6月7日
	 * @author sky_luan
	 */
	public static String Post(Map<String, String> headers,Map<String, String> params, String url) {
		try {
			HttpPost httpPost = new HttpPost(url);
			//设置连接和读取超时时间
			RequestConfig rquestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT).setConnectTimeout(CONNECT_TIME_OUT).build();
			httpPost.setConfig(rquestConfig);
			
			httpPost.setHeader("accept","*/*");
			httpPost.setHeader("connection","Keep-Alive");
			httpPost.setHeader("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			//设置header
			if(null != headers){
				for (Entry<String, String> entry : headers.entrySet()) {
					httpPost.setHeader(entry.getKey(),entry.getValue());
				}
			}
			//设置body
			List<NameValuePair> paramList = new LinkedList<NameValuePair>();
			for (Entry<String, String> entry : params.entrySet()) {
				paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramList, CHARSET);
			httpPost.setEntity(formEntity);
			
			HttpClient httpCient = HttpClients.createDefault();
			HttpResponse httpResponse = httpCient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			
			return EntityUtils.toString(httpEntity, CHARSET);
		} catch (ClientProtocolException e) {
			logger.error("http-post请求失败，uri == " + url + e.getMessage());
		} catch (IOException e) {
			logger.error("http-post请求失败，uri == " + url + e.getMessage());
		}
		return null;
	}
	
	/**
	 * GET请求
	 * @param headers 请求头
	 * @param params 参数列表
	 * @param url 请求URL
	 * @return String
	 * @throws Exception 
	 * @date 2018年6月7日
	 * @author sky_luan
	 */
	public static byte[] Get(Map<String, String> headers,Map<String, String> params, String url) {
		if(null != params){
			StringBuffer sb = new StringBuffer();
			for (Entry<String, String> entry : params.entrySet()) {
				sb.append("&"+entry.getKey()+"="+entry.getValue());
			}
			url = url + "?" + sb.substring(1);
		}
		HttpGet httpGet = new HttpGet(url);
		if(null != headers){
			for (Entry<String, String> entry : headers.entrySet()) {
				httpGet.setHeader(entry.getKey(),entry.getValue());
			}
		}
		HttpClient httpCient = HttpClients.createDefault();
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpCient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			
			return EntityUtils.toByteArray(httpEntity);
			
//			return EntityUtils.toString(httpEntity, CHARSET);
		} catch (ClientProtocolException e) {
			System.out.println("http-get请求失败，uri{},exception{}");
		} catch (IOException e) {
			System.out.println("http-get请求失败，uri{},exception{}");
		}
		return null;
	}
	

}