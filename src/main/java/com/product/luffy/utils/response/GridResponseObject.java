package com.product.luffy.utils.response;


import java.util.ArrayList;
import java.util.Collection;

public class GridResponseObject<T> {

    private ResultCode resultCode = HttpResultCode.PRODUCT_SUCCESS;
    private String message;
    private Collection<T> data;
    private int total;

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
