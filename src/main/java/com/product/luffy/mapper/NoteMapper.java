package com.product.luffy.mapper;

import java.util.List;
import java.util.Map;

import com.product.luffy.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.product.luffy.po.Note;

@Repository("com.product.luffy.mapper.NoteMapper")
public interface NoteMapper {
	
	public List<Note> selectNoteListByUserId(Map<String, String> paramMap);
	
	public int insertNote(Map<String, String> paramMap);

	public int insertUserNote(Map<String, String> paramMap);

	public int updateNote(Map<String, String> paramMap);

	public int deleteNote(Map<String, String> paramMap);

	public Note selectNote(@Param("noteId") String noteId);

	public List<User> selectShareUserList(@Param("userId") String UserId, @Param("noteId") String noteId);

	public int insertShareUser(Map<String, String> paramMap);

	public int deleteShareUser(@Param("noteId") String noteId, @Param("userId") String userId);

	public List<Note> selectShareNoteList(@Param("userId") String userId);
}
