package com.product.luffy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.luffy.po.Code;
import com.product.luffy.service.CodeService;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;

@Controller
@RequestMapping("/code")
public class CodeController {

    @Resource(name = "com.product.luffy.service.impl.CodeService")
    private CodeService codeService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseObject<Code> selectCode(@RequestParam("cdId") String cdId,
                                    @RequestParam("cdDtlId") String cdDtlId) {
        ResponseObject<Code> responseObject = new ResponseObject<Code>();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cdId", cdId);
        paramMap.put("cdDtlId", cdDtlId);

        responseObject.setData(codeService.selectCode(paramMap));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

}
