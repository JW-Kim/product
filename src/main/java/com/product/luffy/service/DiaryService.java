package com.product.luffy.service;

import java.util.List;

import com.product.luffy.po.Diary;

public interface DiaryService {
	
	public List<Diary> selectDiaryList();
	
	public Diary selectDiary(String diaryId);
	
	public int insertDiary(Diary diary);
	
	public int updateDiary(Diary diary);
}
