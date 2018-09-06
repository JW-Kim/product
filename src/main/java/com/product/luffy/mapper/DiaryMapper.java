package com.product.luffy.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.product.luffy.po.Diary;

@Repository("com.product.luffy.mapper.DiaryMapper")
public interface DiaryMapper {
	public List<Diary> selectDiaryList();
}
