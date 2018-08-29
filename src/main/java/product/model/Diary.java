package product.model;

import java.util.Date;

public class Diary {
    private String diaryId;
    private String title;
    private String content;
    private String regUserId;
    private String fileId;
    private Date regDtm;
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
    private Date sleepStartDtm;
    private Date sleepEndDtm;

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
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

    public Date getRegDtm() {
        return regDtm;
    }

    public void setRegDtm(Date regDtm) {
        this.regDtm = regDtm;
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

    public Date getSleepStartDtm() {
        return sleepStartDtm;
    }

    public void setSleepStartDtm(Date sleepStartDtm) {
        this.sleepStartDtm = sleepStartDtm;
    }

    public Date getSleepEndDtm() {
        return sleepEndDtm;
    }

    public void setSleepEndDtm(Date sleepEndDtm) {
        this.sleepEndDtm = sleepEndDtm;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "diaryId='" + diaryId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", regUserId='" + regUserId + '\'' +
                ", fileId='" + fileId + '\'' +
                ", regDtm=" + regDtm +
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
                ", sleepStartDtm=" + sleepStartDtm +
                ", sleepEndDtm=" + sleepEndDtm +
                '}';
    }
}
