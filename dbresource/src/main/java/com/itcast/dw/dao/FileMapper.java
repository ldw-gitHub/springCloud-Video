package com.itcast.dw.dao;

import java.util.List;

import com.itcast.dw.model.FileModel;
import com.itcast.dw.model.FileModelVo;

public interface FileMapper {
	
	void saveFileModel(FileModel fm);
	
	List<FileModelVo> getFilesByUserId(int userId);

}
