package com.product.luffy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.product.luffy.mapper.NoteMapper;
import com.product.luffy.po.Note;
import com.product.luffy.service.NoteService;
import com.product.luffy.utils.UserContext;

@Service("com.product.luffy.service.impl.NoteService")
public class NoteServiceImpl implements NoteService{
	
	@Resource(name="com.product.luffy.mapper.NoteMapper")
	private NoteMapper noteMapper;
	
	public List<Note> selectNoteListByUserId(){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", UserContext.getUserId());
		
		return noteMapper.selectNoteListByUserId(paramMap);
	}
}
