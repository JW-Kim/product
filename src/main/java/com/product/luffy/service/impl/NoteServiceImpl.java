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
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.response.HttpResultCode;


@Service("com.product.luffy.service.impl.NoteService")
public class NoteServiceImpl implements NoteService{
	
	@Resource(name="com.product.luffy.mapper.NoteMapper")
	private NoteMapper noteMapper;
	
	public List<Note> selectNoteListByUserId(){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", UserContext.getUserId());
		
		return noteMapper.selectNoteListByUserId(paramMap);
	}

	public int insertNote(Map<String, String> paramMap){
		int rtn = 0;
		
		rtn = noteMapper.insertNote(paramMap);
		if(rtn != 1) throw new ProductRuntimeException(HttpResultCode.PRODUCT_INTERNAL_SERVER_EXCEPTION, "노트 등록이 정상적으로 되지 않았습니다.");

		rtn = noteMapper.insertUserNote(paramMap);
		if(rtn != 1) throw new ProductRuntimeException(HttpResultCode.PRODUCT_INTERNAL_SERVER_EXCEPTION, "사용자 노트 등록이 정상적으로 되지 않았습니다.");

		return rtn;
	
	}
	
}
