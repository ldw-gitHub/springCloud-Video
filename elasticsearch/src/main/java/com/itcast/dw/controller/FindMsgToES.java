package com.itcast.dw.controller;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.itcast.dw.model.NoticeRepository;
import com.itcast.dw.model.TotalPopulation;

@RestController
@RequestMapping("/es")
public class FindMsgToES {
	
	@Autowired
	private NoticeRepository noticeRespository;
	
	  /**
     * @param title   搜索标题
     * @param pageable page = 第几页参数, value = 每页显示条数
     */
    @GetMapping("search")
    public List<TotalPopulation> search(String title,@PageableDefault(page = 1, value = 10) Pageable pageable){

        //按标题进行搜索
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);

        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Iterable<TotalPopulation> listIt =  noticeRespository.search(queryBuilder,pageable);
        
        //Iterable转list
        List<TotalPopulation> list= Lists.newArrayList(listIt);
        
        return list;
    }

}
