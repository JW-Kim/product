package com.product.luffy.po;

public class Note {
    public String noteId;
    public String noteNm;
    public String noteStatCd;
    public String sex;
    public String birthDt;
    public String fileId;
    public String regUserId;
    public String regDtm;
    public String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNoteStatCd() {
        return noteStatCd;
    }

    public void setNoteStatCd(String noteStatCd) {
        this.noteStatCd = noteStatCd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDt() {
        return birthDt;
    }

    public void setBirthDt(String birthDt) {
        this.birthDt = birthDt;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteNm() {
        return noteNm;
    }

    public void setNoteNm(String noteNm) {
        this.noteNm = noteNm;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getRegUserId() {
        return regUserId;
    }

    public void setRegUserId(String regUserId) {
        this.regUserId = regUserId;
    }

    public String getRegDtm() {
        return regDtm;
    }

    public void setRegDtm(String regDtm) {
        this.regDtm = regDtm;
    }

    @Override
    public String toString() {
        return "Note [noteId=" + noteId + ", noteNm=" + noteNm + ", regUserId=" + regUserId + ", regDtm=" + regDtm
                + "]";
    }

}
