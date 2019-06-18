package com.product.luffy.controller;


import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;

import com.product.luffy.po.User;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Resource(name = "com.product.luffy.service.impl.NoteService")
    NoteService noteService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<Note> selectNoteListByUserId() {
        GridResponseObject<Note> gridResponseObject = new GridResponseObject<Note>();

        gridResponseObject.setData(noteService.selectNoteListByUserId());
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return gridResponseObject;
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<Note> selectMyNoteListByUserId() {
        GridResponseObject<Note> gridResponseObject = new GridResponseObject<Note>();

        gridResponseObject.setData(noteService.selectMyNoteListByUserId());
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return gridResponseObject;
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseObject<Note> selectNote(@PathVariable("noteId") String noteId) {
        ResponseObject<Note> responseObject = new ResponseObject<Note>();

        responseObject.setData(noteService.selectNote(noteId));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/{noteId}/share/user", method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<User> selectShareUserList(@PathVariable("noteId") String noteId) {
        GridResponseObject<User> gridResponseObject = new GridResponseObject<User>();

        gridResponseObject.setData(noteService.selectShareUserList(noteId));
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return gridResponseObject;
    }

    @RequestMapping(value = "/{noteId}/share/user", method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<Boolean> insertShareUser(@PathVariable("noteId") String noteId,
                                            @RequestBody Map<String, String> params) {
        ResponseObject<Boolean> responseObject = new ResponseObject<Boolean>();

        if (params.get("userId") == null) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "피라미터 정보가 올바르지 않습니다.");
        }

        params.put("noteId", noteId);
        params.put("regUserId", UserContext.getUserId());

        responseObject.setData(noteService.insertShareUser(params));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/{noteId}/share/user", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseObject<Boolean> deleteShareUser(@PathVariable("noteId") String noteId,
                                            @RequestParam String userId) {
        ResponseObject<Boolean> responseObject = new ResponseObject<Boolean>();

        responseObject.setData(noteService.deleteShareUser(noteId, userId));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<Note> selectShareNoteList() {
        GridResponseObject<Note> gridResponseObject = new GridResponseObject<Note>();

        gridResponseObject.setData(noteService.selectShareNoteList());
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return gridResponseObject;
    }

    @RequestMapping(value = "/share/{noteId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseObject<Boolean> deleteShareNote(@PathVariable("noteId") String noteId) {
        ResponseObject<Boolean> responseObject = new ResponseObject<Boolean>();

        responseObject.setData(noteService.deleteShareUser(noteId, UserContext.getUserId()));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<String> insertNote(@RequestBody Map<String, String> requestParams) {
        ResponseObject<String> responseObject = new ResponseObject<String>();
        int rtn = 0;

        Map<String, String> paramMap = checkInsertNoteParam(requestParams);

        rtn = noteService.insertNote(paramMap);

        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{noteId}")
    public @ResponseBody
    ResponseObject<String> updateNote(@PathVariable("noteId") String noteId,
                                      @RequestBody Map<String, String> requestParams) {
        ResponseObject<String> responseObject = new ResponseObject<String>();
        int rtn = 0;

        if ((requestParams.get("noteNm") == null || "".equals(requestParams.get("noteNm"))) &&
                (requestParams.get("sex") == null || "".equals(requestParams.get("sex"))) &&
                (requestParams.get("birthDt") == null || "".equals(requestParams.get("birthDt")))
                ) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "피라미터 정보가 올바르지 않습니다.");
        }
        requestParams.put("noteId", noteId);

        rtn = noteService.updateNote(requestParams);

        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{noteId}")
    public @ResponseBody
    ResponseObject<String> deleteNote(@PathVariable("noteId") String noteId) {
        ResponseObject<String> responseObject = new ResponseObject<String>();
        int rtn = 0;

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("noteId", noteId);

        rtn = noteService.deleteNote(paramMap);

        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    private Map<String, String> checkInsertNoteParam(Map<String, String> params) {
        if (params.get("noteNm") == null || params.get("sex") == null || params.get("birthDt") == null) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "피라미터 정보가 올바르지 않습니다.");
        }

        params.put("noteId", IdGen.getNextId());
        params.put("userId", UserContext.getUserId());

        return params;

    }

}
