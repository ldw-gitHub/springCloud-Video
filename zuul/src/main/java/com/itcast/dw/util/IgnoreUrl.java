package com.itcast.dw.util;

import java.util.HashMap;
import java.util.Map;

public class IgnoreUrl {
	
	/**
	 * 所有的接口，是否需要验证登入
	 * 后续可以存入数据库中
	 */
	public static Map<String,Object> filterUrl = new HashMap<String,Object>();
	static{
		filterUrl.put("logout", true);
		filterUrl.put("login", false);
		filterUrl.put("saveFile", true);
		filterUrl.put("getFilesByUserId", true);
		filterUrl.put("uploadFile", false);
		filterUrl.put("deleteFile", true);
		filterUrl.put("saveMedia", true);
		
		filterUrl.put("findAllVideos", true);
		filterUrl.put("findMyOwnVideos", true);
		filterUrl.put("findVideosByType", false);
		filterUrl.put("indexFindVideosByType", false);
		filterUrl.put("findRelateVideos", false);
		filterUrl.put("findVideosById", false);
		filterUrl.put("getVideoCommentsByid", false);
		filterUrl.put("saveVideoComments", true);
	}

}
