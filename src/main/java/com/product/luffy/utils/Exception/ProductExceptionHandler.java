package com.product.luffy.utils.Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;
import com.product.luffy.utils.response.ResultCode;


@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductRuntimeException.class)
    public @ResponseBody
    Object handleProductRuntimeException(ProductRuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        ResultCode resResultCode = e.getResultCode();
        String resMessage = e.getMessage();
        String resData = null;
        if (resResultCode != null && resMessage != null
                && !resMessage.equals(resResultCode.toString())) {
            e.setMessage(resResultCode.toString().concat(" :: ").concat(resMessage));
            resData = resMessage;
            resMessage = resResultCode.toString();
        }

        ResponseObject<Object> resObj = new ResponseObject<Object>();
        if (resResultCode != null) {
            resObj.setResultCode(resResultCode);
        } else {
            resObj.setResultCode(HttpResultCode.PRODUCT_INTERNAL_SERVER_EXCEPTION);
        }
        resObj.setMessage(resMessage);
        resObj.setData(resData);
        return resObj;
    }

    @ExceptionHandler(Throwable.class)
    public @ResponseBody
    Object handleInternalServerException(Exception e, HttpServletRequest request, HttpServletResponse response) {

        ResponseObject<Object> resObj = new ResponseObject<Object>();
        resObj.setResultCode(HttpResultCode.PRODUCT_INTERNAL_SERVER_EXCEPTION);

        if (e instanceof MissingServletRequestParameterException) {
            resObj.setResultCode(HttpResultCode.PRODUCT_INVALID_PARAMETER);
            resObj.setMessage(e.getMessage());
        }
        return resObj;
    }
}
