package com.product.luffy.utils.Exception;

import org.springframework.core.NestedRuntimeException;
import com.product.luffy.utils.response.ResultCode;

public class ProductRuntimeException extends NestedRuntimeException {
    private static final long serialVersionUID = 3070431731480464199L;
    private int statusMsgCode;
    private String message;
    private ResultCode resultCode;
    private Object data;

    public ProductRuntimeException(String message) {
        super(message);
        this.message = message;
    }

    public ProductRuntimeException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
        this.message = message;
    }

    public static long getSerialversionuid() {
        return 3070431731480464199L;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatusMsgCode() {
        return statusMsgCode;
    }

    public void setStatusMsgCode(int statusMsgCode) {
        this.statusMsgCode = statusMsgCode;
    }

}
