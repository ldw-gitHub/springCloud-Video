package com.itcast.dw.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itcast.dw.info.ResultInfo;
import com.itcast.dw.vo.VideoVo;

@FeignClient(value = "service-elastic", fallback = ESFeignFallBack.class)
public interface ESFeign {


	@PostMapping("/esc/save")
	ResultInfo<Object> saveVideoToES(@RequestParam("videoVo") VideoVo videoVo);
	
/*	@PostMapping("/esc/search")
	ResultInfo<Object> search(@RequestParam("title") String title,
			@RequestParam("pageSize") Integer pageSize,
			@RequestParam("pageNum") Integer pageNum);*/
	
	@PostMapping("/esc/delete")
	ResultInfo<Object> delete(@RequestParam("id") String id);

}

@Component
class ESFeignFallBack implements ESFeign {

	@Override
	public ResultInfo<Object> saveVideoToES(VideoVo videoVo) {
		return new ResultInfo<>(ResultInfo.ERROR, "保存到es失败！");
	}

/*	@Override
	public ResultInfo<Object> search(String title, Integer pageSize, Integer pageNum) {
		return new ResultInfo<>(ResultInfo.ERROR, "查询es失败！");
	}*/

	@Override
	public ResultInfo<Object> delete(String id) {
		return new ResultInfo<>(ResultInfo.ERROR, "删除es失败！");
	}

	
}


