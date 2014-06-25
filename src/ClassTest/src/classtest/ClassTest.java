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
public class ClassTest {

    
    private static void example(MyInterface myInt) {
        ;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Test for Interface
        Boolean bo = java.io.Serializable.class.isInstance("a test string");
        System.out.println(bo);
        System.out.println("a test string" instanceof java.io.Serializable);
        System.out.println("a test string" instanceof String);
        
        AClass a = new AClass(5);
        a.foo();
        a.bar();
        BClass b = new BClass(2);
        b.foo();
        b.bar();
        b.zoo();
        Integer v = b.getValue();
        System.out.println(b instanceof MyInterface);
        // Pointer to interface
        MyInterface i = b;
        MyGenericInterface<Integer> i2 = b;
        AClass aa = b;
        // And call function by interface
        example(b);
        // Object from anonymous MyInterface
        MyInterface wI = new MyInterface() {
            
            // foo() declarated in MyInterface
            
            @Override
            public void bar() {
                System.out.println("bar() from anonymous MyInterface");
            }
        };
        wI.bar();
    }
    
}
