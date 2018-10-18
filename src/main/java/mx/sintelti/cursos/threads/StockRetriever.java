package mx.sintelti.cursos.threads;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class StockRetriever implements  Runnable {
    private String company;

    public StockRetriever(String company) {
        this.company = company;
    }

    public BigDecimal getStockPrices() throws IOException  {
        Stock stock = YahooFinance.get(company);
        BigDecimal price = stock.getQuote().getPrice();
        //System.out.println("");
        return  price;
    }

    @Override
    public  void  run() {
        try {
            //getStockPrices();
            Stock stock = YahooFinance.get(company);
            System.out.println(stock.getSymbol() + " - " + stock.getQuote().getPrice());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }




}
