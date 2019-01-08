package com.product.luffy.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.product.luffy.po.Diary;
import com.product.luffy.po.Disease;

@Repository("com.product.luffy.mapper.DiaryMapper")
public interface DiaryMapper {
	public List<Diary> selectDiaryList(Map<String, String> paramMap);
	
	public List<Diary> selectMonthDiaryList(Map<String, String> paramMap);
	
	public Diary selectDiary(Map<String, String> paramMap );
	
	public int insertDiary(Diary diary);
	
	public int insertState(Diary diary);
	
	public int insertDisease(Disease disease);
	
	public int updateDiary(Diary diary);
	
	public int updateState(Diary diary);
	
	public int updateDisease(Disease disease);

	public List<Disease> selectDiseaseMonth(Map<String, String> paramMap);
}
