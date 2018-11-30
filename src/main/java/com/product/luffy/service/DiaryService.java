package com.product.luffy.service;

import java.util.List;
import java.util.Map;

import com.product.luffy.po.Diary;

public interface DiaryService {
	
	public List<Diary> selectDiaryList(String noteId);
	
	public List<Diary> selectMonthDiaryList(Map<String, String> paramMap);
	
	public Diary selectDiary(String diaryId);
	
	public int insertDiary(Diary diary);
	
	public int updateDiary(Diary diary);
}
