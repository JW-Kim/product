package com.product.luffy.service.impl;

import com.product.luffy.mapper.NoteMapper;
import com.product.luffy.po.Note;
import com.product.luffy.po.NoteCfg;
import com.product.luffy.po.User;
import com.product.luffy.service.NoteService;
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.UserContext;
import com.product.luffy.utils.response.HttpResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("com.product.luffy.service.impl.NoteService")
public class NoteServiceImpl implements NoteService {

    @Resource(name = "com.product.luffy.mapper.NoteMapper")
    private NoteMapper noteMapper;

    public List<Note> selectNoteListByUserId() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userId", UserContext.getUserId());

        return noteMapper.selectNoteListByUserId(paramMap);
    }

    public List<Note> selectMyNoteListByUserId() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userId", UserContext.getUserId());

        return noteMapper.selectMyNoteListByUserId(paramMap);
    }

    public int insertNote(Map<String, Object> paramMap) {
        int rtn = 0;

        rtn = noteMapper.insertNote(paramMap);
        if (rtn != 1)
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INTERNAL_SERVER_EXCEPTION, "노트 등록이 정상적으로 되지 않았습니다.");

        rtn = noteMapper.insertShareUserList(paramMap);
        if (rtn != 1)
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INTERNAL_SERVER_EXCEPTION, "사용자 노트 등록이 정상적으로 되지 않았습니다.");

        noteMapper.insertNoteCfgList(paramMap);

        return rtn;

    }

    public int updateNote(Map<String, Object> paramMap) {
        int rtn = 0;
        rtn = noteMapper.updateNote(paramMap);

        noteMapper.deleteNoteCfgList(paramMap.get("noteId")+"");
        noteMapper.insertNoteCfgList(paramMap);
        return rtn;
    }

    public int deleteNote(Map<String, String> paramMap) {
        return noteMapper.deleteNote(paramMap);
    }

    public Note selectNote(String noteId) {
        return noteMapper.selectNote(noteId, UserContext.getUserId());
    }

    public List<User> selectShareUserList(String noteId) {
        return noteMapper.selectShareUserList(UserContext.getUserId(), noteId);
    }

    public int insertShareUser(Map<String, String> paramMap) {
        return noteMapper.insertShareUser(paramMap);
    }

    public int insertShareUserList(Map<String, Object> paramMap) {
        return noteMapper.insertShareUserList(paramMap);
    }

    public int deleteShareUser(String noteId, String userId) {
        return noteMapper.deleteShareUser(noteId, userId);
    }

    public List<Note> selectShareNoteList() {
        return noteMapper.selectShareNoteList(UserContext.getUserId());
    }

    public Boolean selectUserNote(String noteId) {
        return noteMapper.selectUserNote(noteId, UserContext.getUserId()) == null;
    }

    public List<NoteCfg> selectNoteCfgList(String noteId) {
        if("".equals(noteId)) {
            return noteMapper.selectNoteCfgDefaultList();
        } else {
            return noteMapper.selectNoteCfgList(noteId);
        }
    }
}
