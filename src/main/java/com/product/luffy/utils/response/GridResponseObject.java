/**
 * <pre>
 * Copyright (c) 2018 SamsungSDS, Inc.
 * All right reserved.
 * 
 * This software is the confidential and proprietary information of Samsung
 * SDS, Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SamsungSDS.
 * 
 * </pre>
*/
package com.product.luffy.utils.response;


import java.util.ArrayList;
import java.util.Collection;

public class GridResponseObject<T> {

 	private ResultCode resultCode = HttpResultCode.PRODUCT_SUCCESS;
 	private String message;
 	private Collection<T> data;
 	private int total;
	private int statusMsgCd;

	public Collection<T> getData() {
		if (data == null) {
			return new ArrayList<T>();
		}
		return data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}
	
    /**
     * @return the resultCode
     */
	public Integer getResultCode() {
		return resultCode.getCode();
	}

	
    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(ResultCode resultCode) {
    	this.resultCode = resultCode;
    }

	
    /**
     * @return the message
     */
    public String getMessage() {
    	return message;
    }

	
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
    	this.message = message;
    }

	
    /**
     * @return the statusMsgCd
     */
    public int getStatusMsgCd() {
    	return statusMsgCd;
    }

	
    /**
     * @param statusMsgCd the statusMsgCd to set
     */
    public void setStatusMsgCd(int statusMsgCd) {
    	this.statusMsgCd = statusMsgCd;
    }

	
    /**
     * @return the total
     */
    public int getTotal() {
    	return total;
    }

	
    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
    	this.total = total;
    }

}
