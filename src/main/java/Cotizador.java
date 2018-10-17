import mx.sintelti.cursos.threads.StockRetriever;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Cotizador {
    public static void main(String[] args ) throws IOException {
        BigDecimal sumaTotal = new BigDecimal("0.00");
        //BigDecimal sumaTotal= 0;
        //StockRetriever stockRetriever = new StockRetriever("TSLA");
        //System.out.println( "TESLA" +" "+ stockRetriever.getStockPrices());

        List<String> listaCompany = leeArchivo();
        //sumaTotal.intValue(0);
        long inicio =  System.nanoTime();
        for (String lista:listaCompany){
            StockRetriever intera = new StockRetriever(lista);
            //System.out.println( lista +" "+ intera.getStockPrices());
            sumaTotal =sumaTotal.add(intera.getStockPrices());
        }
        long fin =  System.nanoTime();
        System.out.println( "SUMA "+" "+sumaTotal);
        System.out.println("Tiempo Ejecucion "+ (fin-inicio)/1000000000 +" Seg");
        System.out.println("Tiempo Ejecucion "+ (fin-inicio));
    }

    private static List<String> leeArchivo() throws IOException{
        String fileName = "C:\\Users\\isg34\\IdeaProjects\\Hilos\\src\\main\\resources\\list.txt";
        List<String>  lineas = Files.readAllLines(Paths.get(fileName));
        return  lineas;
    }
}

