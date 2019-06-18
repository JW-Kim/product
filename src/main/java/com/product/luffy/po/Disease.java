package com.product.luffy.po;

public class Disease {
    private String diseaseId;
    private String noteId;
    private String diseaseNm;
    private String diseaseDt;
    private String symptom;
    private String hospitalNm;
    private String prescription;

    public String getDiseaseId() {
        return diseaseId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseDt() {
        return diseaseDt;
    }

    public void setDiseaseDt(String diseaseDt) {
        this.diseaseDt = diseaseDt;
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
        return "Disease{" +
                "diseaseId='" + diseaseId + '\'' +
                ", noteId='" + noteId + '\'' +
                ", diseaseNm='" + diseaseNm + '\'' +
                ", diseaseDt='" + diseaseDt + '\'' +
                ", symptom='" + symptom + '\'' +
                ", hospitalNm='" + hospitalNm + '\'' +
                ", prescription='" + prescription + '\'' +
                '}';
    }
}
