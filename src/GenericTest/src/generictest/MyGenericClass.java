/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generictest;

/**
 * Test for declarating Generic class.
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class MyGenericClass <T> implements MyInterface {
    private T value;
    public MyGenericClass (T value) {
        this.value = value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
    
    public Integer foo(){
        return 1;
    }
}