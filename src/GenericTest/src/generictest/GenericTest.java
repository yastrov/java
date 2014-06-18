/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generictest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class GenericTest {

    /**
     * Sum two arguments. We need to know type of elements.
     * @param <T> type of arguments
     * @param a first argument
     * @param b second argument
     * @return summ as Integer
     */
    public static <T extends Number> T sumTwo(T a, T b) {
        Integer c;
        c = (a.intValue() + b.intValue());
        return (T)c;
    }
    
    /**
     * Sum elements in List. We don't need to know type of elements in List.
     * @param list of numbers
     * @return sum of elements as Double
     */
    public static double sum(List<? extends Number> list){
        double sum = 0;
        for(Number n : list){
            sum += n.doubleValue();
        }
        return sum;
    }
    
    public static <T extends MyInterface> Integer callFoo(T obj) {
        return obj.foo();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer x = 7, y = 9, z = 0, res;
        // Only this way!
        GenericTest myApp = new GenericTest();
        res = myApp.<Integer>sumTwo(x, y);
        System.out.println("Generic functions for summ two Integer: " + res);
        List<Double> myDoubleList = new LinkedList<>();
        //----------
        Double[] arr = {1.0,2.0,3.0,5.0,4.0,7.0,8.0,9.0};
        myDoubleList.addAll(Arrays.asList(arr));
        Double dResult = myApp.sum(myDoubleList);
        System.out.println("Generic functions for summ List of Doubles: " + dResult);
        //----------
        MyClass mC = new MyClass();
        System.out.println("Test extends for simple class and custom interface: " 
                + myApp.callFoo(mC));
        //----------
        MyGenericClass<Integer> mGC = new MyGenericClass<Integer>(5);
        System.out.println("Test custom generic class and custom interface: " 
                + mGC.getValue());
    }
}