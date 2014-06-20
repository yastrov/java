/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generictest;

/**
 *public class A implements MyGenericInterface<Integer> {}
 * @author Yuri Astrov (yuriastrov@gmail.com)
 * @param <T> type of velue
 */
public interface MyGenericInterface<T> {
    abstract void setParam(T param);
    abstract T getParam();
}
