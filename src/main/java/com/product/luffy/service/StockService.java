package com.product.luffy.service;

import com.product.luffy.po.Company;
import com.product.luffy.po.Stock;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface StockService {

    public List<Stock> selectStockList(String stockDt);

    public Stock selectStock(String stockId);

    public int deleteStock(String stockId);

    public List<Company> selectCompanyList(String searchVal);

    public int insertCompany(@RequestBody Map<String, Object> params);

    public Boolean insertStock(@RequestBody Map<String, Object> params);

    public Boolean updateStock(@RequestBody Map<String, Object> params);
}
