package com.product.luffy.controller;


import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.product.luffy.po.Note;
import com.product.luffy.service.NoteService;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.response.ResponseObject;
import com.product.luffy.utils.IdGen;
import com.product.luffy.utils.UserContext;


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

	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseObject<String>insertNote(@RequestBody Map<String, String> requestParams){
		ResponseObject<String> responseObject = new ResponseObject<String>();
		int rtn = 0;
		
		Map<String, String> paramMap = checkInsertNoteParam(requestParams);
		
		rtn = noteService.insertNote(paramMap);

		responseObject.setData(rtn);
		responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		
		return responseObject;
	}

	@RequestMapping(method=RequestMethod.POST, value="/{noteId}")
	public @ResponseBody ResponseObject<String>updateNote(@PathVariable("noteId") String noteId,
												@RequestBody Map<String, String> requestParams){
		ResponseObject<String> responseObject = new ResponseObject<String>();
		int rtn = 0;
			
		if( (requestParams.get("noteNm") == null || "".equals(requestParams.get("noteNm"))) && 
			(requestParams.get("sex") == null || "".equals(requestParams.get("sex"))) && 
			(requestParams.get("birthDt") == null || "".equals(requestParams.get("birthDt")))  
		){
			throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "피라미터 정보가 올바르지 않습니다.");
		}		
		requestParams.put("noteId", noteId);

		rtn = noteService.insertNote(paramMap);

		responseObject.setData(rtn);
		responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		
		return responseObject;
	}


	private Map<String, String> checkInsertNoteParam(Map<String, String> params){
		if(params.get("noteNm") == null || params.get("sex") == null || params.get("birthDt") == null ){
			throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "피라미터 정보가 올바르지 않습니다.");
		}
			
		params.put("noteId", IdGen.getNextId());
		params.put("userId", UserContext.getUserId());
		
		return params;
	
	}

}
