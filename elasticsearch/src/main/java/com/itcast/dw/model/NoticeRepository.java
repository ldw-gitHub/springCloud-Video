package com.itcast.dw.model;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface NoticeRepository extends ElasticsearchRepository<TotalPopulation, Long>{

}
