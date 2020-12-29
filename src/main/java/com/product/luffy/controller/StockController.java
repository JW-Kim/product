package com.product.luffy.controller;

import com.product.luffy.po.Company;
import com.product.luffy.po.Diary;
import com.product.luffy.po.Stock;
import com.product.luffy.service.StockService;
import com.product.luffy.utils.IdGen;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping(value = "/stock")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Resource(name = "com.product.luffy.service.impl.StockService")
    private StockService stockService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<Stock> selectStockList(@RequestParam("stockDt") String stockDt) {
        GridResponseObject<Stock> gridResponseObject = new GridResponseObject<>();

        gridResponseObject.setData(stockService.selectStockList(stockDt));
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return gridResponseObject;
    }

    @RequestMapping(value="/{stockId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseObject<Stock> selectStock(@PathVariable("stockId") String stockId) {
        ResponseObject<Stock> responseObject = new ResponseObject<>();

        responseObject.setData(stockService.selectStock(stockId));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value="/{stockId}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<Stock> deleteStock(@PathVariable("stockId") String stockId) {
        ResponseObject<Stock> responseObject = new ResponseObject<>();

        responseObject.setData(stockService.deleteStock(stockId));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return responseObject;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<Company> selectCompanyList(@RequestParam("searchVal") String searchVal) {
        GridResponseObject<Company> gridResponseObject = new GridResponseObject<>();
        gridResponseObject.setData(stockService.selectCompanyList(searchVal));
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return gridResponseObject;
    }

    @RequestMapping(value="/company", method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<String> insertCompany(@RequestBody Map<String, Object> requestParams){
        ResponseObject<String> responseObject = new ResponseObject<>();

        requestParams.put("companyId", IdGen.getNextId());
        LOGGER.debug(">>>>>>>>>> companyNm : {}",  requestParams.get("companyNm"));
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        responseObject.setData(stockService.insertCompany(requestParams));
        return responseObject;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<Boolean> insertStock(@RequestBody Map<String, Object> requestParams){
        ResponseObject<Boolean> responseObject = new ResponseObject<>();
        requestParams.put("stockId", IdGen.getNextId());

        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        responseObject.setData((stockService.insertStock(requestParams)));
        return responseObject;
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<Boolean> updateStock(@RequestBody Map<String, Object> requestParams){
        ResponseObject<Boolean> responseObject = new ResponseObject<>();
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        responseObject.setData(stockService.updateStock(requestParams));
        return responseObject;
    }

}
