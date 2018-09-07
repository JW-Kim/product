package com.product.luffy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.luffy.po.Diary;
import com.product.luffy.service.DiaryService;
import com.product.luffy.utils.IdGen;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;

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

		LOGGER.debug(">>>>>>>>>>>>>> selectDiaryList 시작 {} : "+ diaryList);
		
		gridResponseObject.setData(diaryList);
		gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		
		return gridResponseObject;
	}
	
	@RequestMapping(value="/{diaryId}", method=RequestMethod.GET)
	public @ResponseBody ResponseObject<Diary> selectDiary(@PathVariable("diaryId") String diaryId){
		ResponseObject<Diary> responseObject = new ResponseObject<Diary>();
		LOGGER.debug(">>>>>>>>>> selectDiary 시작 : "+ diaryId);
		
		Diary diary = diaryService.selectDiary(diaryId);
		responseObject.setData(diary);
		responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		
		return responseObject;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseObject<String> insertDiary(@RequestBody Map<String, Object> requestParams){
		LOGGER.debug(">>>>>>>>>>> insertDiary 시작 :"+ requestParams);
		ResponseObject<String> responseObject = new ResponseObject<String>();
		Diary diary = new Diary();
		
		LOGGER.debug(">>>>>> IdGen : "+ IdGen.getNextId());
		
		int rtn = diaryService.insertDiary(diary);
		responseObject.setData(rtn);
		responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		return responseObject;
	}
}
