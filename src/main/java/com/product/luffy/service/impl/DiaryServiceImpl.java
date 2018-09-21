package com.product.luffy.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.product.luffy.mapper.DiaryMapper;
import com.product.luffy.po.Diary;
import com.product.luffy.service.DiaryService;

@Service("com.product.luffy.service.impl.DiaryService")
public class DiaryServiceImpl implements DiaryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DiaryService.class);

	@Resource(name="com.product.luffy.mapper.DiaryMapper")
	private DiaryMapper diaryMapper;
	
	public List<Diary> selectDiaryList() {
		LOGGER.debug(">>>>>>>>>>>>>> DiaryService selectDiaryList 시작 {} : ");
		List<Diary> diaryList = diaryMapper.selectDiaryList();

		LOGGER.debug(">>>>>>>>>>>>>> DiaryService selectDiaryList 끝 {} : ", diaryList);
		return diaryList;
	}
	
	public Diary selectDiary(String diaryId) {
		Diary diary = diaryMapper.selectDiary(diaryId);
	
		return diary;
	}
	
	public int insertDiary(Diary diary) {
		int rtn = 0;
		
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
