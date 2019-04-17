package com.itcast.dw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itcast.dw.baseDao.BaseMapper;
import com.itcast.dw.model.FileModel;
import com.itcast.dw.model.FileModelVo;

public interface FileMapper extends BaseMapper<FileModel>{
	
	void saveFileModel(FileModel fm);
	
	List<FileModelVo> getFilesByUserId(@Param("userId") int userId);

}
