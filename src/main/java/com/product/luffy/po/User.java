package com.product.luffy.po;

import java.util.Date;

public class User {
	
	private String userId;
	
	private String userNm;
	
	private Date regDtm;
	
    private Integer id;

    private String username;

    private String sex;

    private Date birthday;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public Date getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userNm=" + userNm + ", regDtm=" + regDtm + ", id=" + id + ", username="
				+ username + ", sex=" + sex + ", birthday=" + birthday + ", address=" + address + "]";
	}
    
}
