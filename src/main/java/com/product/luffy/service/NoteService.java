package com.product.luffy.service;

import java.util.List;

import com.product.luffy.po.Note;

public interface NoteService {
	public List<Note> selectNoteListByUserId();
}
