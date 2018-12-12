package com.product.luffy.po;

import java.sql.Date;

public class Diary {
	private String diaryId;
	private String noteId;
	private String title;
	private String content;
	private String regUserId;
	private String fileId;
	private String diaryDt;
	private String stateId;
	private String feelingCd;
	private String healthCd;
	private String feverCd;
	private String breakfastCd;
	private String lunchCd;
	private String dinnerCd;
	private String shitCd;
	private String shitCnt;
	private String shitDesc;
	private String sleepStartTime;
	private String sleepEndTime;
	private String regDtm;
	private String chgRegDtm;
	private String headerTitle;
	private int height;
	private float weight;
	private String diseaseId;
	private String diseaseNm;
	private String symptom;
	private String hospitalNm;
	private String prescription;
	
	public String getDiaryId() {
		return diaryId;
	}




	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}




	public String getNoteId() {
		return noteId;
	}




	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}

	




	public String getDiaryDt() {
		return diaryDt;
	}




	public void setDiaryDt(String diaryDt) {
		this.diaryDt = diaryDt;
	}




	public String getRegUserId() {
		return regUserId;
	}




	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}




	public String getFileId() {
		return fileId;
	}




	public void setFileId(String fileId) {
		this.fileId = fileId;
	}




	public String getStateId() {
		return stateId;
	}




	public void setStateId(String stateId) {
		this.stateId = stateId;
	}




	public String getFeelingCd() {
		return feelingCd;
	}




	public void setFeelingCd(String feelingCd) {
		this.feelingCd = feelingCd;
	}




	public String getHealthCd() {
		return healthCd;
	}




	public void setHealthCd(String healthCd) {
		this.healthCd = healthCd;
	}




	public String getFeverCd() {
		return feverCd;
	}




	public void setFeverCd(String feverCd) {
		this.feverCd = feverCd;
	}




	public String getBreakfastCd() {
		return breakfastCd;
	}




	public void setBreakfastCd(String breakfastCd) {
		this.breakfastCd = breakfastCd;
	}




	public String getLunchCd() {
		return lunchCd;
	}




	public void setLunchCd(String lunchCd) {
		this.lunchCd = lunchCd;
	}




	public String getDinnerCd() {
		return dinnerCd;
	}




	public void setDinnerCd(String dinnerCd) {
		this.dinnerCd = dinnerCd;
	}




	public String getShitCd() {
		return shitCd;
	}




	public void setShitCd(String shitCd) {
		this.shitCd = shitCd;
	}





	public String getShitCnt() {
		return shitCnt;
	}




	public void setShitCnt(String shitCnt) {
		this.shitCnt = shitCnt;
	}




	public String getShitDesc() {
		return shitDesc;
	}




	public void setShitDesc(String shitDesc) {
		this.shitDesc = shitDesc;
	}







	public String getSleepStartTime() {
		return sleepStartTime;
	}




	public void setSleepStartTime(String sleepStartTime) {
		this.sleepStartTime = sleepStartTime;
	}




	public String getSleepEndTime() {
		return sleepEndTime;
	}




	public void setSleepEndTime(String sleepEndTime) {
		this.sleepEndTime = sleepEndTime;
	}






	public String getRegDtm() {
		return regDtm;
	}




	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}




	public String getChgRegDtm() {
		return chgRegDtm;
	}




	public void setChgRegDtm(String chgRegDtm) {
		this.chgRegDtm = chgRegDtm;
	}




	public String getHeaderTitle() {
		return headerTitle;
	}




	public void setHeaderTitle(String headerTitle) {
		this.headerTitle = headerTitle;
	}


	@Override
	public String toString() {
		return "Diary{" +
				"diaryId='" + diaryId + '\'' +
				", noteId='" + noteId + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", regUserId='" + regUserId + '\'' +
				", fileId='" + fileId + '\'' +
				", diaryDt='" + diaryDt + '\'' +
				", stateId='" + stateId + '\'' +
				", feelingCd='" + feelingCd + '\'' +
				", healthCd='" + healthCd + '\'' +
				", feverCd='" + feverCd + '\'' +
				", breakfastCd='" + breakfastCd + '\'' +
				", lunchCd='" + lunchCd + '\'' +
				", dinnerCd='" + dinnerCd + '\'' +
				", shitCd='" + shitCd + '\'' +
				", shitCnt='" + shitCnt + '\'' +
				", shitDesc='" + shitDesc + '\'' +
				", sleepStartTime='" + sleepStartTime + '\'' +
				", sleepEndTime='" + sleepEndTime + '\'' +
				", regDtm='" + regDtm + '\'' +
				", chgRegDtm='" + chgRegDtm + '\'' +
				", headerTitle='" + headerTitle + '\'' +
				", height=" + height +
				", weight=" + weight +
				", diseaseId='" + diseaseId + '\'' +
				", diseaseNm='" + diseaseNm + '\'' +
				", symptom='" + symptom + '\'' +
				", hospitalNm='" + hospitalNm + '\'' +
				", prescription='" + prescription + '\'' +
				'}';
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}




	public void setWeight(float weight) {
		this.weight = weight;
	}




	public String getDiseaseId() {
		return diseaseId;
	}




	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}




	public String getDiseaseNm() {
		return diseaseNm;
	}




	public void setDiseaseNm(String diseaseNm) {
		this.diseaseNm = diseaseNm;
	}




	public String getSymptom() {
		return symptom;
	}




	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}




	public String getHospitalNm() {
		return hospitalNm;
	}




	public void setHospitalNm(String hospitalNm) {
		this.hospitalNm = hospitalNm;
	}




	public String getPrescription() {
		return prescription;
	}




	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}


}
