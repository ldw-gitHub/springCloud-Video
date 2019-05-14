package com.itcast.dw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.itcast.dw.model.ResultInfo;
import com.itcast.dw.model.VideoRepository;
import com.itcast.dw.model.VideoVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * 通过repository执行es操作
 * @author liudawei
 */
@RestController
@RequestMapping("/es")
public class FindMsgToES {
	
	Logger logger = Logger.getLogger(FindMsgToES.class);
	
	@Autowired
	private VideoRepository videoRepository;
	
	  /**
     * @param context   搜索视频信息
     * @param pageable page = 第几页参数, value = 每页显示条数
     */
	@HystrixCommand(fallbackMethod = "searchError")
    @PostMapping("search")
    public ResultInfo<?> search(String title,@PageableDefault(page = 1, value = 10) Pageable pageable) throws Exception{

        //按标题和描述进行搜索
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);

        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Iterable<VideoVo> listIt =  videoRepository.search(queryBuilder,pageable);
        
        //Iterable转list
        List<VideoVo> list= Lists.newArrayList(listIt);
        
        return new ResultInfo<>(ResultInfo.SUCCESS,ResultInfo.MSG_SUCCESS,list);
    }
	
	public ResultInfo<?> searchError(String title,@PageableDefault(page = 1, value = 10) Pageable pageable) throws Exception {
		logger.info("====================ES查询失败=======================");
		return new ResultInfo<>(ResultInfo.ERROR, "fallbackMethod-->ES查询失败");
	}
    
	/**
	 * 
	 * 保存
	 * @param request
	 * @param response
	 * @param videoVo
	 * @return
	 * @throws Exception 
	 * @date 2019年5月6日
	 * @author liudawei
	 */
	@HystrixCommand(fallbackMethod = "saveError")
    @PostMapping("save")
    public ResultInfo<?> save(HttpServletRequest request,HttpServletResponse response,VideoVo videoVo) throws Exception{
		videoRepository.index(videoVo);
		return new ResultInfo<>(ResultInfo.SUCCESS,ResultInfo.MSG_SUCCESS);
    }
	
	public ResultInfo<?> saveError(HttpServletRequest request,HttpServletResponse response,VideoVo videoVo) throws Exception {
		logger.info("====================ES保存失败=======================");
		return new ResultInfo<>(ResultInfo.ERROR, "fallbackMethod-->ES保存失败");
	}
	
	
	/**
	 * 
	 * 删除
	 * @param request
	 * @param response
	 * @param videoVo
	 * @return
	 * @throws Exception 
	 * @date 2019年5月6日
	 * @author liudawei
	 */
	@HystrixCommand(fallbackMethod = "deleteError")
    @PostMapping("delete")
    public ResultInfo<?> delete(HttpServletRequest request,HttpServletResponse response,VideoVo videoVo) throws Exception{
		videoRepository.delete(videoVo);
		return new ResultInfo<>(ResultInfo.SUCCESS,ResultInfo.MSG_SUCCESS);
    }
	
	public ResultInfo<?> deleteError(HttpServletRequest request,HttpServletResponse response,VideoVo videoVo) throws Exception {
		logger.info("====================ES删除失败=======================");
		return new ResultInfo<>(ResultInfo.ERROR, "fallbackMethod-->ES删除失败");
	}
	
	/**
	 * 
	 * 修改
	 * @param request
	 * @param response
	 * @param videoVo
	 * @return
	 * @throws Exception 
	 * @date 2019年5月6日
	 * @author liudawei
	 */
	@HystrixCommand(fallbackMethod = "updateError")
    @PostMapping("update")
    public ResultInfo<?> update(HttpServletRequest request,HttpServletResponse response,VideoVo videoVo) throws Exception{
		videoRepository.save(videoVo);//对于同一个对象，id相同执行update操作
		return new ResultInfo<>(ResultInfo.SUCCESS,ResultInfo.MSG_SUCCESS);
    }
	
	public ResultInfo<?> updateError(HttpServletRequest request,HttpServletResponse response,VideoVo videoVo) throws Exception {
		logger.info("====================ES修改失败=======================");
		return new ResultInfo<>(ResultInfo.ERROR, "fallbackMethod-->ES修改失败");
	}



}
