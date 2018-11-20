package com.product.luffy.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.luffy.mapper.FileMapper;
import com.product.luffy.po.File;
import com.product.luffy.service.FileService;
import com.product.luffy.utils.IdGen;

@Service("com.product.luffy.service.impl.FileService")
public class FileServiceImpl implements FileService{
	private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

	@Resource(name="com.product.luffy.mapper.FileMapper")
	private FileMapper fileMapper;
	
	public File insertFile(MultipartFile multipartFile) {
		LOGGER.debug("multipartFile : "+ multipartFile.getOriginalFilename() );
		FileOutputStream fos = null;
		String path = "";
		String str ="";
		try {
			Calendar cal = Calendar.getInstance();

			int year = cal.get ( cal.YEAR );
			int month = cal.get ( cal.MONTH ) + 1 ;

			LOGGER.debug("year : "+ year + ", month : "+ month);
			path = "D:\\photo\\"+year+"\\"+month;
			java.io.File f = new java.io.File(path);
			f.mkdirs();
			
			long time = System.currentTimeMillis(); 
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyymmddhhmmss");
			str = dayTime.format(new Date(time));
			path = path+"\\"+ str + ".jpg";
			
			byte fileData [] = multipartFile.getBytes();
			
			fos = new FileOutputStream(path);
			fos.write(fileData);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		LOGGER.debug("multipartFile.getOriginalFilename() : "+ multipartFile.getOriginalFilename());
		File file = new File();
		file.setFileId(IdGen.getNextId());
		file.setFileNm(str);
		file.setFileExtNm("jpg");
		file.setFileSize(multipartFile.getSize()+"");
		file.setFilePath(path);
		LOGGER.debug("file : "+ file.toString());
		int rtn = fileMapper.insertFile(file);
		
		return file;
	}
	
	public File selectFile(Map<String, Object> paramMap) {
		return fileMapper.selectFile(paramMap);
	}
}
