package com.product.luffy.service;

import java.util.List;
import java.util.Map;


import com.product.luffy.po.Note;

public interface NoteService {
	public List<Note> selectNoteListByUserId();

	public int insertNote(Map<String, String> paramMap);

	public int updateNote(Map<String, String> paramMap);
	
}
