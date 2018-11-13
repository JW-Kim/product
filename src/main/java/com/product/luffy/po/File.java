package com.product.luffy.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.product.luffy.po.File;
import com.product.luffy.service.FileService;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;

@Controller
@RequestMapping("/file")
public class FileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
	
	@Resource(name = "com.product.luffy.service.impl.FileService")
	private FileService fileService;
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public @ResponseBody ResponseObject<File> uploadFile(@RequestParam("image") MultipartFile multipartFile){
		ResponseObject<File> responseObject = new ResponseObject<File>();
		LOGGER.debug(">>>>>>>>>> uploadFile 시작 "+ multipartFile.getName()+", "+ multipartFile.getOriginalFilename());
		
		File file = fileService.insertFile(multipartFile);
		responseObject.setData(file);
		responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		
		return responseObject;
	}
}
