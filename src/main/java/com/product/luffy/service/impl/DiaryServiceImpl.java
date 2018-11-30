package com.product.luffy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.product.luffy.mapper.DiaryMapper;
import com.product.luffy.po.Diary;
import com.product.luffy.service.DiaryService;
import com.product.luffy.utils.UserContext;

@Service("com.product.luffy.service.impl.DiaryService")
public class DiaryServiceImpl implements DiaryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DiaryService.class);

	@Resource(name="com.product.luffy.mapper.DiaryMapper")
	private DiaryMapper diaryMapper;
	
	public List<Diary> selectDiaryList(String noteId) {
		LOGGER.debug(">>>>>>>>>>>>>> DiaryService selectDiaryList 시작 {} : ");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("noteId", noteId);
		List<Diary> diaryList = diaryMapper.selectDiaryList(paramMap);

		LOGGER.debug(">>>>>>>>>>>>>> DiaryService selectDiaryList 끝 {} : ", diaryList);
		return diaryList;
	}
	
	public List<Diary> selectMonthDiaryList(Map<String, String> paramMap){
		return diaryMapper.selectMonthDiaryList(paramMap);
	}
	
	public Diary selectDiary(String diaryId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("diaryId", diaryId);
		
		Diary diary = diaryMapper.selectDiary(paramMap);
	
		return diary;
	}
	
	public int insertDiary(Diary diary) {
		int rtn = 0;
		LOGGER.debug(">>>>>>>>>>>>>> DiaryService Diary  {} : ", diary.toString());
		//insert DIARY_F
		rtn = diaryMapper.insertDiary(diary);
		
		//insert STATE_F
		rtn = diaryMapper.insertState(diary);
		
		return rtn;
	}
	
	public int updateDiary(Diary diary) {
		int rtn = 0;
		
		rtn = diaryMapper.updateDiary(diary);
		
		rtn = diaryMapper.updateState(diary);
		
		return rtn;
	}

}
