package com.itcast.dw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.dw.model.ResultInfo;
import com.itcast.dw.model.VideoVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * 通过elasticsearchTemplate进行es操作
 * @author liudawei
 */
@RestController
@RequestMapping("/esc")
public class ESTemplateController {
	
	private Logger logger = Logger.getLogger(ESTemplateController.class);
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@HystrixCommand(fallbackMethod = "saveError")
    @GetMapping("save")
    public ResultInfo<?> save(HttpServletRequest request,HttpServletResponse response,VideoVo videoVo) throws Exception{
		IndexQuery indexQuery = new IndexQueryBuilder()
				                .withIndexName("video")
				                .withType("videoInfo")
				                .withId(videoVo.getId() + "")
				                .withObject(videoVo)
				                .build();
		elasticsearchTemplate.index(indexQuery);
		return new ResultInfo<>(ResultInfo.SUCCESS,ResultInfo.MSG_SUCCESS);
    }
	
	public ResultInfo<?> saveError(HttpServletRequest request,HttpServletResponse response,VideoVo videoVo) throws Exception {
		logger.info("====================ES保存失败=======================");
		return new ResultInfo<>(ResultInfo.ERROR, "fallbackMethod-->ES保存失败");
	}
	
	
	/**
	 * 组合查询
     * must(QueryBuilders) :   AND
     * mustNot(QueryBuilders): NOT
     * should:                  : OR
     * 
	 * @param title
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @throws Exception 
	 * @date 2019年5月6日
	 * @author liudawei
	 */
	@SuppressWarnings("deprecation")
	@HystrixCommand(fallbackMethod = "searchError")
    @PostMapping("search")
    public ResultInfo<?> search(String title,@RequestParam(defaultValue = "10") Integer pageSize,
    		@RequestParam(defaultValue = "1") Integer pageNum) throws Exception{

		Sort sort = new Sort(Sort.Direction.DESC,"id");//以id值为准，降序排列
		Pageable pageable = new PageRequest(pageNum,pageSize,sort);
        //按标题和描述进行搜索
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);
        
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
        		                  .withQuery(queryBuilder)//自定义查询
        		                  .withPageable(pageable)//自定义分页
        		                  .build();
        
        Page<VideoVo> videoEntities = elasticsearchTemplate.queryForPage(searchQuery, VideoVo.class);
        
        Map<String,Object> rtMap = new HashMap<String,Object>();
        
        rtMap.put("pageNum", videoEntities.getTotalPages());
        rtMap.put("pageSize", videoEntities.getTotalElements());
        rtMap.put("size", videoEntities.getSize());
        rtMap.put("nowPage", videoEntities.getNumber());
        rtMap.put("newPageNum", videoEntities.getNumberOfElements());
        rtMap.put("list", videoEntities.getContent());
        
        return new ResultInfo<>(ResultInfo.SUCCESS,ResultInfo.MSG_SUCCESS,rtMap);
    }
	
	public ResultInfo<?> searchError(String title,@PageableDefault(page = 1, value = 10) Pageable pageable) throws Exception {
		logger.info("====================ES查询失败=======================");
		return new ResultInfo<>(ResultInfo.ERROR, "fallbackMethod-->ES查询失败");
	}
	
	@HystrixCommand(fallbackMethod = "deleteError")
    @PostMapping("delete")
    public ResultInfo<?> delete(String id) throws Exception{
        String rt = elasticsearchTemplate.delete("video","videoInfo", id);
        logger.info("es 删除id = " + id + ":return" + rt);
        return new ResultInfo<>(ResultInfo.SUCCESS,ResultInfo.MSG_SUCCESS);
    }
	
	public ResultInfo<?> deleteError(String id) throws Exception {
		logger.info("====================ES删除失败=======================");
		return new ResultInfo<>(ResultInfo.ERROR, "fallbackMethod-->ES删除失败");
	}
	

}
