package com.product.luffy.controller;


import com.product.luffy.po.Note;
import com.product.luffy.po.NoteCfg;
import com.product.luffy.po.User;
import com.product.luffy.service.NoteService;
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.IdGen;
import com.product.luffy.utils.ProductUtil;
import com.product.luffy.utils.UserContext;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @RequestMapping(value = "/cfg", method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<NoteCfg> selectNoteCfgList(@RequestParam("noteId") String noteId) {
        GridResponseObject<NoteCfg> gridResponseObject = new GridResponseObject<NoteCfg>();
        if(!"".equals(noteId) && noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        gridResponseObject.setData(noteService.selectNoteCfgList(noteId));
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return gridResponseObject;
    }

    @RequestMapping(value = "/{noteId}/share/user", method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<User> selectShareUserList(@PathVariable("noteId") String noteId) {
        GridResponseObject<User> gridResponseObject = new GridResponseObject<User>();
        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

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

        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        params.put("noteId", noteId);
        params.put("regUserId", UserContext.getUserId());

        responseObject.setData(noteService.insertShareUser(params));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }
    @RequestMapping(value = "/{noteId}/share/userList", method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<Boolean> insertShareUserList(@PathVariable("noteId") String noteId,
                                            @RequestBody Map<String, Object> params) {
        ResponseObject<Boolean> responseObject = new ResponseObject<Boolean>();
        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        if (params.get("userIdList") == null) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "피라미터 정보가 올바르지 않습니다.");
        }

        params.put("noteId", noteId);
        params.put("regUserId", UserContext.getUserId());

        responseObject.setData(noteService.insertShareUserList(params));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/{noteId}/share/user", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseObject<Boolean> deleteShareUser(@PathVariable("noteId") String noteId,
                                            @RequestParam String userId) {
        ResponseObject<Boolean> responseObject = new ResponseObject<Boolean>();
        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

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
        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        responseObject.setData(noteService.deleteShareUser(noteId, UserContext.getUserId()));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<String> insertNote(@RequestBody Map<String, Object> requestParams) {
        ResponseObject<String> responseObject = new ResponseObject<String>();
        int rtn = 0;

        Map<String, Object> paramMap = checkInsertNoteParam(requestParams);

        rtn = noteService.insertNote(paramMap);

        responseObject.setData(paramMap.get("noteId"));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{noteId}")
    public @ResponseBody
    ResponseObject<String> updateNote(@PathVariable("noteId") String noteId,
                                      @RequestBody Map<String, Object> requestParams) {
        ResponseObject<String> responseObject = new ResponseObject<String>();
        int rtn = 0;
        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        if ((requestParams.get("noteNm") == null || "".equals(requestParams.get("noteNm"))) &&
                (requestParams.get("sex") == null || "".equals(requestParams.get("sex"))) &&
                (requestParams.get("birthDt") == null || "".equals(requestParams.get("birthDt")))
                ) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "피라미터 정보가 올바르지 않습니다.");
        }
        requestParams.put("noteId", noteId);
        requestParams.put("noteNm", ProductUtil.cleanXss(requestParams.get("noteNm")+""));
        requestParams.put("noteCfgList", (List<Map<String, String>>) requestParams.get("noteCfgList"));

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
        if(noteService.selectUserNote(noteId)){
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "no Auth");
        }

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("noteId", noteId);

        rtn = noteService.deleteNote(paramMap);

        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    private Map<String, Object> checkInsertNoteParam(Map<String, Object> params) {
        if (params.get("noteNm") == null || params.get("sex") == null || params.get("birthDt") == null) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "피라미터 정보가 올바르지 않습니다.");
        }

        params.put("noteId", IdGen.getNextId());
        params.put("regUserId", UserContext.getUserId());
        params.put("noteNm", ProductUtil.cleanXss(params.get("noteNm")+""));

        List<Map<String, String>> shareList = (List<Map<String, String>>) params.get("shareList");
        List<Map<String, String>> noteCfgList = (List<Map<String, String>>) params.get("noteCfgList");

        Map<String, String> map = new HashMap();
        map.put("userId", UserContext.getUserId());
        shareList.add(map);
        params.put("userIdList", shareList);
        params.put("noteCfgList", noteCfgList);

        return params;

    }

}
