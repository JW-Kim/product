package com.product.luffy.po;

public class Code {
	private String cdId;
	private String cdDtlId;
	private String cdVal;
	
	public String getCdId() {
		return cdId;
	}
	public void setCdId(String cdId) {
		this.cdId = cdId;
	}
	public String getCdDtlId() {
		return cdDtlId;
	}
	public void setCdDtlId(String cdDtlId) {
		this.cdDtlId = cdDtlId;
	}
	public String getCdVal() {
		return cdVal;
	}
	public void setCdVal(String cdVal) {
		this.cdVal = cdVal;
	}
	@Override
	public String toString() {
		return "code [cdId=" + cdId + ", cdDtlId=" + cdDtlId + ", cdVal=" + cdVal + "]";
	}
	
	
}
