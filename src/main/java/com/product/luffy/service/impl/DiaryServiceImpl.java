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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시mm분", Locale.KOREA);
		
		List<Diary> diaryList = diaryMapper.selectDiaryList();
		for(int i=0; i<diaryList.size(); i++) {
			diaryList.get(i).setChgRegDtm(simpleDateFormat.format(diaryList.get(i).getRegDtm()));
			diaryList.get(i).setHeaderTitle(new SimpleDateFormat("yyyy.MM.dd (E)", Locale.KOREA).format(diaryList.get(i).getRegDtm()));
		}
		
		return diaryList;
	}
	
	public Diary selectDiary(String diaryId) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시mm분", Locale.KOREA);
		
		Diary diary = diaryMapper.selectDiary(diaryId);
		diary.setChgRegDtm(simpleDateFormat.format(diary.getRegDtm()));
		
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
