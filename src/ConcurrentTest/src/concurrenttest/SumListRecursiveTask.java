/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package concurrenttest;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 *
 * Use for task like Map Reduce And parallell quicksort algorythms.
 * new ForkJoinPool().invoke(new SumListRecursiveTask(list, 0, list.length-1));
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class SumListRecursiveTask extends RecursiveTask<Integer>{
    private final List<Integer> list;
    private Integer start, stop;
    
    public SumListRecursiveTask( List<Integer> list, Integer start,
            Integer stop) {
        this.list = list;
        this.start = start;
        this.stop = stop;
    }
    
    @Override
    protected Integer compute() {
        Integer sum = 0;
        List<SumListRecursiveTask> subTasks = new LinkedList<>();
        Integer mediana = start+(stop-start)/2;
        subTasks.add(new SumListRecursiveTask(list, start, mediana));
        subTasks.add(new SumListRecursiveTask(list, mediana, stop));
        for(SumListRecursiveTask task1 : subTasks) {
            sum += task1.join();
        }
        return sum;
    }
}
