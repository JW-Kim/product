package product.model;

import java.util.Date;

public class Auth {
    public String userId;
    public String phone;
    public Long regUserId;
    public Date regDtm;
    public Long modUserId;
    public Date modDtm;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRegUserId() {
        return regUserId;
    }

    public void setRegUserId(Long regUserId) {
        this.regUserId = regUserId;
    }

    public Date getRegDtm() {
        return regDtm;
    }

    public void setRegDtm(Date regDtm) {
        this.regDtm = regDtm;
    }

    public Long getModUserId() {
        return modUserId;
    }

    public void setModUserId(Long modUserId) {
        this.modUserId = modUserId;
    }

    public Date getModDtm() {
        return modDtm;
    }

    public void setModDtm(Date modDtm) {
        this.modDtm = modDtm;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                ", regUserId=" + regUserId +
                ", regDtm=" + regDtm +
                ", modUserId=" + modUserId +
                ", modDtm=" + modDtm +
                '}';
    }
}
