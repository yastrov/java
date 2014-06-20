/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classtest;

/**
 * public class A implements MyGenericInterface<Integer> {}
 * @author Yuri Astrov
 * @param <T> type of return value
 */
public interface MyGenericInterface<T> extends MyInterface {
    abstract T getValue();
    abstract void setValue(T value);
}
