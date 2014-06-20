package classtest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yuri Astrov
 */
public class AClass extends AbstractClass implements MyInterface{
    private Integer a = 0;
    
    AClass(Integer a) {
        this.a = a;
    }

    @Override
    public void foo(){
         System.out.println("foo from AClass");
    }
    
    @Override
    public void bar(){
        System.out.println("bar from AClass");
    }
    
    @Override
    public void zoo(){
        System.out.println("zoo from AClass");
    }
}
