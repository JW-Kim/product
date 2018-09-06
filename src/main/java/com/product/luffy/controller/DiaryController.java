package com.product.luffy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.luffy.po.Diary;
import com.product.luffy.service.DiaryService;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;

@Controller
@RequestMapping("diary")
public class DiaryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DiaryController.class);
	
	@Resource(name="com.product.luffy.service.impl.DiaryService")
	private DiaryService diaryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody GridResponseObject<Diary> selectDiaryList(){
		GridResponseObject<Diary> gridResponseObject = new GridResponseObject<Diary>();
		
		List<Diary> diaryList = diaryService.selectDiaryList();
		
		LOGGER.debug(">>>>>>>>>>>>>> diaryList {} : "+ diaryList);
		
		gridResponseObject.setData(diaryList);
		gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		
		return gridResponseObject;
	}
	
}
