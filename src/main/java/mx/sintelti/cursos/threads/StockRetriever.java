package mx.sintelti.cursos.threads;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
public class StockRetriever implements  Runnable {
    private String compani;
    StockRetriever(String compani){
        this.compani = compani;
    }
    @Override
    public void run() {
        try {
            Stock stock = YahooFinance.get(compani);
            BigDecimal price = stock.getQuote().getPrice();
            //stock.print();
            StocksMain.sumar(price);// = TestStock.suma.add(price);
            //System.out.println(TestStock.suma);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

}
