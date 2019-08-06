package com.product.luffy.po;

public class File {
    private String fileId;
    private String fileNm;
    private String fileExtNm;
    private String fileSize;
    private String filePath;
    private String regDtm;
    private String fileAuthCd;
    private Boolean fileAuthYn;

    public String getFileAuthCd() {
        return fileAuthCd;
    }

    public void setFileAuthCd(String fileAuthCd) {
        this.fileAuthCd = fileAuthCd;
    }

    public Boolean getFileAuthYn() {
        return fileAuthYn;
    }

    public void setFileAuthYn(Boolean fileAuthYn) {
        this.fileAuthYn = fileAuthYn;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileNm() {
        return fileNm;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    public String getFileExtNm() {
        return fileExtNm;
    }

    public void setFileExtNm(String fileExtNm) {
        this.fileExtNm = fileExtNm;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRegDtm() {
        return regDtm;
    }

    public void setRegDtm(String regDtm) {
        this.regDtm = regDtm;
    }

    @Override
    public String toString() {
        return "File [fileId=" + fileId + ", fileNm=" + fileNm + ", fileExtNm=" + fileExtNm + ", fileSize=" + fileSize
                + ", filePath=" + filePath + ", regDtm=" + regDtm + "]";
    }


}
