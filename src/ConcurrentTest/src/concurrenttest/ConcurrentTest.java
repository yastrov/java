/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package concurrenttest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class ConcurrentTest {
    
    public static void testSimpleThread(){
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("Hello from simple thread!");
            }
        };
        t.start();
    }
    public static void testSimpleCallable() {
        try {
            FutureTask<Integer> task =
                    new FutureTask<Integer>(new Callable<Integer>(){
                        @Override
                        public Integer call() throws Exception {
                            return 1;
                        }
                    });
            new Thread(task).start();
            System.out.println(task.get());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * 
     * @param nThreads number of threads (equiv for number of processors)
     */
    public static void testCallable(int nThreads) {
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Callable<Integer> worker = new  Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return Counter.next();
                    //return AtomicCounter.next();
                    //return VolatileCounter.next();
                }
            };
            Future<Integer> submit= executor.submit(worker);
            list.add(submit);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            ;
        }
        Integer sum=0;
        for (Future<Integer> future : list) {
            try {
                sum += future.get();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
            ex.printStackTrace();
            }
        }
        System.out.println(sum);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Get number of aviable processors
        int nThreads = Runtime.getRuntime().availableProcessors();
        testSimpleThread();
        testSimpleCallable();
        testCallable(nThreads);
        //------------------------
        List<Integer> listL = new LinkedList<>();
        Integer[] arr = {1,2,3};
        listL = Arrays.asList(arr);
        // Bug? This in not terminated:
        //Integer sum = 0;
        //sum = new ForkJoinPool().invoke(new SumListRecursiveTask(listL, 0, listL.size()-1));
        //System.out.println(sum);
    }   
}