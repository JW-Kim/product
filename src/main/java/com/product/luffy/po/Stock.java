package com.product.luffy.po;

public class Stock {
    private String stockId;
    private String stockDt;
    private String companyId;
    private String direction;
    private int maxPrice;
    private int minPrice;
    private int price;
    private int preClosingPrice;
    private String companyNm;

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockDt() {
        return stockDt;
    }

    public void setStockDt(String stockDt) {
        this.stockDt = stockDt;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPreClosingPrice() {
        return preClosingPrice;
    }

    public void setPreClosingPrice(int preClosingPrice) {
        this.preClosingPrice = preClosingPrice;
    }
}
