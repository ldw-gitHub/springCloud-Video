/*package com.itcast.dw;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import com.itcast.dw.model.VideoRepository;
import com.itcast.dw.model.VideoVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotsApplicationTests {
	
	@Autowired
	VideoRepository videoRepository;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Test
	public void contextLoads(){
		Sort sort = new Sort(Sort.Direction.DESC,"id");//以id值为准，降序排列
		Pageable pageable = new PageRequest(0,10,sort);
        //按标题和描述进行搜索
       // QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "test");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.termQuery("click", "0"))
                .should(QueryBuilders.termQuery("title", "tet"))
                .should(QueryBuilders.termQuery("description", "测试"));
		
		//模糊匹配
		WildcardQueryBuilder queryBuilder1 = QueryBuilders.wildcardQuery(  
	            "title", "*tet*");//搜索名字中含有tet的文档  
		WildcardQueryBuilder queryBuilder2 = QueryBuilders.wildcardQuery(  
				"description", "*测试*");//搜索名字中含有tet的文档  
		WildcardQueryBuilder queryBuilder3 = QueryBuilders.wildcardQuery(  
				"click", 1 + "");//搜索名字中含有tet的文档  
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();  
		//name中含有jack或者interest含有read，相当于or  
		boolQueryBuilder.should(queryBuilder1);  
		boolQueryBuilder.should(queryBuilder2);  
		boolQueryBuilder.should(queryBuilder3);  
        //高亮处理
		
		QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("music",  
		           "name", "interest");
        
        
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
        		                  .withQuery(queryBuilder)//自定义查询
        		                  .withPageable(pageable)//自定义分页
        		                  .build();
        Page<VideoVo> sampleEntities = elasticsearchTemplate.queryForPage(searchQuery, VideoVo.class);
        
        List<VideoVo> content = sampleEntities.getContent();
        
        System.out.println("页数" + sampleEntities.getTotalPages());
        System.out.println("行数" + sampleEntities.getTotalElements());
        System.out.println("大小" + sampleEntities.getSize());
        System.out.println("当前第几页" + sampleEntities.getNumber());
        System.out.println("当前页的数量"+sampleEntities.getNumberOfElements());
        System.out.println("List<Book>:"+content);
	}

}
*/