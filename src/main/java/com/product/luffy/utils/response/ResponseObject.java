package com.product.luffy.utils.response;

public class ResponseObject<T> {

	private ResultCode resultCode = HttpResultCode.PRODUCT_SUCCESS;
	private String message;
	private Object data;


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

}
