package com.product.luffy.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.product.luffy.po.File;

@Repository("com.product.luffy.mapper.FileMapper")
public interface FileMapper {
	public int insertFile(File file);
	
	public File selectFile(Map<String, Object> paramMap);
}
