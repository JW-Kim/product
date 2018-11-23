package com.product.luffy.po;

public class Note {
	public String noteId;
	public String noteNm;
	public String regUserId;
	public String regDtm;
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
