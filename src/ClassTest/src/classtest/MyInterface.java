/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classtest;

/**
 *
 * @author Yuri Astrov
 */
public interface MyInterface {
    default void foo(){
        System.out.println(MyInterface.class.getName());
    }
    abstract void bar();
}
