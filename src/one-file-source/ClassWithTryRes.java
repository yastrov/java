/**
 * Template for new class and example for try-with-resources. Java 7+
 * Also see ClassTest project for more information.
 * @@author Yuri Astrov
 */
public class ClassWithTryRes extends SuperClass implements AutoCloseable {
    private Integer value = 0;

    public ClassTemplate () {
        //super(); // It must be in here, but we have other idea:
        this(6); // Call other constructor
    }
    
    public ClassTemplate (Integer value) {
        super(value);
        this.value = value;
    }
    
    @Override
    public void foo(){
        super.foo(); // Call from SuperClass
    }
    
    @Override
    public void close() {
        System.out.println("Close");
    }
}

/*
For example:

try (ClassWithTryRes o = new ClassWithTryRes()) {
    o.foo();
}
*/
