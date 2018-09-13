package com.itcast.dw.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.itcast.dw.model.FileModel;
import com.itcast.dw.model.FileModelVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;

@FeignClient(value = "service-db")
public interface FileService {
	
	@PostMapping(value = "/saveFileModel")
    void saveFileModel(@RequestBody FileModel fm);
	
	
	@PostMapping(value = "/getFilesByUserId")
	PageBean<FileModelVo> getFilesByUserId(@RequestBody PageModel page,@RequestParam("userId") int userId);
	

}
