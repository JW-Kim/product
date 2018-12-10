package com.product.luffy.po;

public class Disease {
	private String diseaseId;
	private String diaryId;
	private String diseaseNm;
	private String symptom;
	private String hospitalNm;
	private String prescription;
	public String getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}
	public String getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
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
	
	@Override
	public String toString() {
		return "Disease [diseaseId=" + diseaseId + ", diaryId=" + diaryId + ", diseaseNm=" + diseaseNm + ", symptom="
				+ symptom + ", hospitalNm=" + hospitalNm + ", prescription=" + prescription + "]";
	}
	
	
}
