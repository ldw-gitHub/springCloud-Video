package com.itcast.dw.service;

import com.itcast.dw.model.FileModel;
import com.itcast.dw.model.FileModelVo;
import com.itcast.dw.page.PageBean;
import com.itcast.dw.page.PageModel;

public interface FileService {
	
    void saveFileModel(FileModel fm);
	
	PageBean<FileModelVo> getFilesByUserId(PageModel page,int userId);
	

}
