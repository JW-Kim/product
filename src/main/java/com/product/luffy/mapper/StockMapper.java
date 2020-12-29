package com.product.luffy.mapper;

import com.product.luffy.po.Company;
import com.product.luffy.po.Stock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Repository("com.product.luffy.mapper.StockMapper")
public interface StockMapper {
    public List<Stock> selectStockList(@Param("stockDt") String stockDt);

    public Stock selectStock(@Param("stockId") String stockId);

    public int deleteStock(@Param("stockId") String stockId);

    public List<Company> selectCompanyList(@Param("searchVal") String searchVal);

    public int insertCompany(Map<String, Object> params);

    public int insertStock(@RequestBody Map<String, Object> params);

    public int updateStock(@RequestBody Map<String, Object> params);
}
