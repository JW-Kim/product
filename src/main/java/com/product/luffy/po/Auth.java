package com.product.luffy.po;

public class Auth {
    //User
    private String userId;
    private String userLoginId;
    private String userNm;
    private String userRole;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Auth [userId=" + userId + ", userLoginId=" + userLoginId + ", userNm=" + userNm + ", userRole="
                + userRole + "]";
    }

}
