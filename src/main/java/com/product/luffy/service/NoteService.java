package com.product.luffy.service;

import java.util.List;
import java.util.Map;


import com.product.luffy.po.Note;
import com.product.luffy.po.User;

public interface NoteService {
	public List<Note> selectNoteListByUserId();

	public int insertNote(Map<String, String> paramMap);

	public int updateNote(Map<String, String> paramMap);

	public int deleteNote(Map<String, String> paramMap);

	public Note selectNote(String noteId);

	public List<User> selectShareUserList(String noteId);

	public int insertShareUser(Map<String, String> paramMap);

	public int deleteShareUser(String noteId, String userId);

	public List<Note> selectShareNoteList();
	
}
