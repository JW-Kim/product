package com.product.luffy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.luffy.po.Diary;
import com.product.luffy.service.DiaryService;
import com.product.luffy.utils.IdGen;
import com.product.luffy.utils.UserContext;
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DiaryController.class);
	
	@Resource(name = "com.product.luffy.service.impl.DiaryService")
	private DiaryService diaryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody GridResponseObject<Diary> selectDiaryList(@RequestParam("noteId") String noteId){
		GridResponseObject<Diary> gridResponseObject = new GridResponseObject<Diary>();
		LOGGER.debug(">>>>>>>>>>>>>> selectDiaryList 시작 {} : "+ noteId);
		List<Diary> diaryList = diaryService.selectDiaryList(noteId);

		LOGGER.debug(">>>>>>>>>>>>>> selectDiaryList 끝 {} : "+ diaryList);
		
		gridResponseObject.setData(diaryList);
		gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		
		return gridResponseObject;
	}
	
	@RequestMapping(value="/month", method = RequestMethod.GET)
	public @ResponseBody GridResponseObject<Diary> selectMonthDiaryList(@RequestParam("diaryMonth") String diaryMonth,
																		@RequestParam("noteId") String noteId){
		
		GridResponseObject<Diary> gridResponseObject = new GridResponseObject<Diary>();
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("diaryMonth", diaryMonth);
		paramMap.put("noteId", noteId);
		List<Diary> diaryList = diaryService.selectMonthDiaryList(paramMap);
		
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
		
		Diary diary = setDiary(null, requestParams);		
		
		int rtn = diaryService.insertDiary(diary);
		responseObject.setData(rtn);
		responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		return responseObject;
	}
	
	@RequestMapping(value="/{diaryId}", method=RequestMethod.POST)
	public @ResponseBody ResponseObject<String> updateDiary(@PathVariable("diaryId") String diaryId,
														    @RequestBody Map<String, Object> requestParams){
		LOGGER.debug(">>>>>>>>>>> updateDiary 시작 :"+ diaryId+ " : "+requestParams);
		ResponseObject<String> responseObject = new ResponseObject<String>();
		
		Diary diary = setDiary(diaryId, requestParams);
		
		int rtn = diaryService.updateDiary(diary);
		responseObject.setData(rtn);
		responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		return responseObject;
	}
	
	private Diary setDiary(String diaryId, Map<String, Object> params) {
		Diary diary = new Diary();
		diaryId = diaryId == null ? IdGen.getNextId() : diaryId;
		String title = params.get("title") == null ? null : (String) params.get("title");
		String noteId = params.get("noteId") == null ? null : (String) params.get("noteId");
		String content = params.get("content") == null ? null : (String) params.get("content");
		String fileId = params.get("fileId") == null ? null : (String) params.get("fileId");
		String diaryDt = "";
		String feelingCd = params.get("feelingCd") == null ? "" : (String) params.get("feelingCd");
		String healthCd = params.get("healthCd") == null ? "" : (String) params.get("healthCd");
		String feverCd = params.get("feverCd") == null ? "" : (String) params.get("feverCd");
		String breakfastCd = params.get("breakfastCd") == null ? "" : (String) params.get("breakfastCd");
		String lunchCd = params.get("lunchCd") == null ? "" : (String) params.get("lunchCd");
		String dinnerCd = params.get("dinnerCd") == null ? "" : (String) params.get("dinnerCd");
		String shitCd = params.get("shitCd") == null ? "" : (String) params.get("shitCd");
		String shitCnt = params.get("shitCnt") == null ? "" : (String) params.get("shitCnt");
		String shitDesc = params.get("shitDesc") == null ? "" : (String) params.get("shitDesc");
		String sleepStartTime = params.get("sleepStartTime") == null ? null : (String) params.get("sleepStartTime");
		String sleepEndTime = params.get("sleepEndTime") == null ? null : (String) params.get("sleepEndTime");
		
		if(params.get("diaryDt") == null) {
			Date date = new Date();
			diaryDt= new SimpleDateFormat("yyyy-MM-dd").format(date);
		}else {
			diaryDt =  (String) params.get("diaryDt");
		}
		
		if(title == null || content == null) throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "타이틀, 내용에 등록된 내용이 없습니다.");
		
		diary.setDiaryId(diaryId);
		diary.setNoteId(noteId);
		diary.setTitle(title);
		diary.setContent(content);
		diary.setRegUserId(UserContext.getUserId());
		diary.setFileId(fileId);
		diary.setDiaryDt(diaryDt);
		diary.setStateId(IdGen.getNextId());
		diary.setFeelingCd(feelingCd);
		diary.setHealthCd(healthCd);
		diary.setFeverCd(feverCd);
		diary.setBreakfastCd(breakfastCd);
		diary.setLunchCd(lunchCd);
		diary.setDinnerCd(dinnerCd);
		diary.setShitCd(shitCd);
		diary.setShitCnt(shitCnt);
		diary.setShitDesc(shitDesc);
		diary.setSleepStartTime(sleepStartTime);
		diary.setSleepEndTime(sleepEndTime);
		
		return diary;
	}
}
