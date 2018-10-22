package mx.sintelti.cursos.threads;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StocksMain {
    private static volatile BigDecimal suma = new BigDecimal(0.0);
    public static synchronized void sumar(BigDecimal price){
        suma = suma.add(price);
    }
    public static void main(String[] args)  {
        String fileName = "C:\\Users\\isg34\\IdeaProjects\\Hilos\\src\\main\\resources\\list.txt";
        try{
            int cores = Runtime.getRuntime().availableProcessors();
            System.out.println("CPÜ Cores: "+ cores);
            double blockingCoeffficient = 0.9;
            int poolSize = (int)(cores / (1 - blockingCoeffficient));
            System.out.println("CPÜ Cores: "+ cores + " Pool Size: " + poolSize);
            List<String> lineas = Files.readAllLines(Paths.get(fileName));
            long inicio = System.nanoTime();
            Collection<Callable<Object>> tareas = new ArrayList<>();
            for (String linea: lineas) {
                StockRetriever stock = new StockRetriever(linea.trim());
                tareas.add(Executors.callable(stock));
            }
            ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);
            threadPool.invokeAll(tareas);
            long fin = System.nanoTime();
            System.out.println(suma);
            System.out.println("Tiempo total:" + ((fin - inicio)/1000000000.0) + " segundos");
            System.out.println("Inicio: " + inicio + " Fin:"+ fin);
            threadPool.shutdown();
            //new Thread(stock, linea).start();
        }catch (IOException ioe){
            System.out.println("Error al manipupar el archivo. - " + ioe.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
