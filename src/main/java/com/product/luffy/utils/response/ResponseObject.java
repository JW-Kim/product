/**
 * <pre>
 * Copyright (c) 2014 SamsungSDS, Inc.
 * All right reserved.
 * 
 * This software is the confidential and proprietary information of Samsung
 * SDS, Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SamsungSDS.
 * 
 * Author : YongWoon Lee
 * Date : 2014. 4. 26.
 * Description : 
 * </pre>
*/
package com.product.luffy.utils.response;

public class ResponseObject<T> {

	private ResultCode resultCode = HttpResultCode.PRODUCT_SUCCESS;
	private String message;
	private Object data;
	private int statusMsgCd;


	public Integer getResultCode() {
		return resultCode.getCode();
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object object) {
		this.data = object;
	}

    public int getStatusMsgCd() {
	    return statusMsgCd;
    }

    public void setStatusMsgCd(int statusMsgCd) {
	    this.statusMsgCd = statusMsgCd;
    }
}
