package com.product.luffy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.product.luffy.po.Diary;
import com.product.luffy.po.Disease;

@Repository("com.product.luffy.mapper.DiaryMapper")
public interface DiaryMapper {
    public List<Diary> selectDiaryList(Map<String, String> paramMap);

    public List<Diary> selectMonthDiaryList(Map<String, String> paramMap);

    public Diary selectDiary(Map<String, String> paramMap);

    public Diary selectPreDiaryInfo(Map<String, String> paramMap);

    public int insertDiary(Diary diary);

    public int insertState(Diary diary);

    public int deleteDiary(Map<String, String> paramMap);

    public int deleteState(Map<String, String> paramMap);

    public int insertDisease(Disease disease);

    public int updateDiary(Diary diary);

    public int updateState(Diary diary);

    public int updateDisease(Disease disease);

    public int deleteDisease(Map<String, String> paramMap);

    public List<Disease> selectDiseaseMonth(Map<String, String> paramMap);

    public Disease selectDisease(Map<String, String> paramMap);

    public Diary selectDiaryAuth(@Param("diaryId") String diaryId, @Param("userId") String userId);

    public Disease selectDiseaseAuth(@Param("diseaseId") String diseaseId, @Param("userId") String userId);
}
