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
public class BClass extends AClass implements MyGenericInterface<Integer> {
    private final String selfName = "My String";
    private Integer value = 0;

    public BClass() {
        // Call other constructor
        this(6);
    }
    
    public BClass(Integer b) {
        // Call constructor for Basic class
        super(b);
        System.out.println(BClass.class.getName());
    }
    
    public void foo(){
        // Call foo() from superclass AClass()
        super.foo();
        System.out.println("foo from BClass");
    }
    
    @Override
    public void bar(){
        System.out.println("bar from BClass");
    }
    
    @Override
    public Integer getValue(){
        return value;
    }
    
    public void setValue(Integer value) {
        this.value = value;
    }

    /* Called by the garbage collector, may make perfomance problem.
    Use only if you work with System Resources and you know, what you do.
    Empty finalize() do no effect:). It just for simple.
    */
    protected void finalize () throws Throwable {
        ;
    }

}
