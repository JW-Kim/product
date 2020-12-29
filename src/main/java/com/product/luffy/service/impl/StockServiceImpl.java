package com.product.luffy.service.impl;

import com.product.luffy.mapper.StockMapper;
import com.product.luffy.po.Company;
import com.product.luffy.po.Stock;
import com.product.luffy.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("com.product.luffy.service.impl.StockService")
public class StockServiceImpl implements StockService{

    @Resource(name = "com.product.luffy.mapper.StockMapper")
    private StockMapper stockMapper;

    public List<Stock> selectStockList(String stockDt){
        return stockMapper.selectStockList(stockDt);
    }

    public Stock selectStock(String stockId){
        return stockMapper.selectStock(stockId);
    }

    public int deleteStock(String stockId){
        return stockMapper.deleteStock(stockId);
    }

    public List<Company> selectCompanyList(String searchVal){
        return stockMapper.selectCompanyList(searchVal);
    }

    public int insertCompany(@RequestBody Map<String, Object> params){
        return stockMapper.insertCompany(params);
    }

    public Boolean insertStock(@RequestBody Map<String, Object> params){
        return stockMapper.insertStock(params) > 0;
    }

    public Boolean updateStock(@RequestBody Map<String, Object> params){
        return stockMapper.updateStock(params) > 0;
    }
}
