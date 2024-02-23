package com.example;

import java.util.*;


public class StocksPortefolio {
    
    private IStockMarketService stockmarket;
    private List<Stock> stocks;

    public StocksPortefolio(IStockMarketService x){
        this.stockmarket = x;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock s){
        this.stocks.add(s);
    }

    public double totalValue(){
        double r = 0.0;
        for (Stock stock : stocks) {
            r = r +(stock.getQuantity() * this.stockmarket.lookUpPrice(stock.getLabel()));
        }
        return r;
    }

}
