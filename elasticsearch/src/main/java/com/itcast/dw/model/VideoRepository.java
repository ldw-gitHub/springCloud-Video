package com.itcast.dw.model;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface VideoRepository  extends ElasticsearchRepository<VideoVo, Integer>{
	
	VideoVo findAllByTitle(String title);

}
