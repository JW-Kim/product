package com.product.luffy.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.product.luffy.po.Note;

@Repository("com.product.luffy.mapper.NoteMapper")
public interface NoteMapper {
	
	public List<Note> selectNoteListByUserId(Map<String, String> paramMap);
	
	public int insertNote(Map<String, String> paramMap);

	public int insertUserNote(Map<String, String> paramMap);

	public int updateNote(Map<String, String> paramMap);

}
