package com.product.luffy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.product.luffy.mapper.DiaryMapper;
import com.product.luffy.po.Diary;
import com.product.luffy.service.DiaryService;

@Service("com.product.luffy.service.impl.DiaryService")
public class DiaryServiceImpl implements DiaryService {

	@Resource(name="com.product.luffy.mapper.DiaryMapper")
	private DiaryMapper diaryMapper;
	
	public List<Diary> selectDiaryList() {
		return diaryMapper.selectDiaryList();
	}
	
	public Diary selectDiary(String diaryId) {
		return diaryMapper.selectDiary(diaryId);
	}
	
	public int insertDiary(Diary diary) {
		int rtn = 0;
		
		//insert DIARY_F
		rtn = diaryMapper.insertDiary(diary);
		
		//insert STATE_F
		rtn = diaryMapper.insertState(diary);
		
		return rtn;
	}
}
