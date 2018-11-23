package com.product.luffy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.luffy.po.Note;
import com.product.luffy.service.NoteService;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;

@Controller
@RequestMapping("/note")
public class NoteController {
	
	@Resource(name="com.product.luffy.service.impl.NoteService")
	NoteService noteService;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody GridResponseObject<Note> selectNoteListByUserId(){
		GridResponseObject<Note> gridResponseObject = new GridResponseObject<Note>();

		gridResponseObject.setData(noteService.selectNoteListByUserId());
		gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		
		return gridResponseObject;
	}
}
