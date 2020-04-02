/**
 * 
 * @date 2020年3月26日
 */
package com.itcast.dw.test.baidu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.itcast.dw.util.HttpClientUtils;

/**
 * 一句话功能简述
 * @author liudawei
 */

public class GetImage {
	
	static final String url = "http://api.map.baidu.com/staticimage/v2";
	static final String ak = "bM5ruDIPTF9qR7ELgvDRuSnZAAuQP5kH";
	
	static final Integer width = 800; // 400	图片宽度。取值范围：(0, 1024]。Scale=2,取值范围：(0, 512]。
	static final Integer height = 400; // 300	图片高度。取值范围：(0, 1024]。Scale=2,取值范围：(0, 512]。
	static final String center = "113.9360519600,22.5417578800"; // 北京	地图中心点位置，参数可以为经纬度坐标或名称。坐标格式：lng<经度>，lat<纬度>，例如116.43213,38.76623。
	static final Integer zoom = 11; // 11	地图级别。高清图范围[3, 18]；低清图范围[3,19]
	static final String markers = "113.9360519600,22.5417578800"; // 标注，可通过经纬度或地址/地名描述；多个标注之间用竖线分隔。
	
	static final String file_url = "E:\\cesi\\tupian.png"; // 11	地图级别。高清图范围[3, 18]；低清图范围[3,19]
	
	public static void main(String[] args) throws Exception {
		Map<String,String> headers = new HashMap<String,String>();
		Map<String,String> params = new HashMap<>();
		
		params.put("width", width + "");
		params.put("height", height + "");
		params.put("center", center);
		params.put("zoom", zoom + "");
		params.put("ak", ak);
		params.put("markers", markers);
		
		byte[] rt = HttpClientUtils.Get(headers, params, url);
		
		FileOutputStream fo = new FileOutputStream(new File(file_url));
		fo.write(rt);
		fo.flush();
		fo.close();
		
	}

}
