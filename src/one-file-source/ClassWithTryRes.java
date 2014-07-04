/**
 * Template for new class and example for try-with-resources. Java 7+
 * Also see ClassTest project for more information.
 * @@author Yuri Astrov
 */
public class ClassWithTryRes extends SuperClass
                                implements AutoCloseable,
                                           Comparable<ClassWithTryRes> {
    private Integer value = 0;

    static {
        // Init here
        ;
    }

    public ClassWithTryRes () {
        //super(); // It must be in here, but we have other idea:
        this(6); // Call other constructor
    }
    
    public ClassWithTryRes (Integer value) {
        super(value);
        this.value = value;
    }
    
    @Override
    public void foo(){
        super.foo(); // Call from SuperClass
    }
    
    /**
     * For Try-With-resource
     */
    @Override
    public void close() {
        System.out.println("Close");
    }

    public Integer getValue() {
        return value;
    }

    public static ClassWithTryRes valueOf(String s) throws NumberFormatException {
        return new ClassWithTryRes(Integer.valueOf(s));
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.value)
                .append(":")
                .append(this.getClass())
                .toString();
    }
    
    /**
     * Always you should override hashCode() if you override equals().
     * Rules:
     * For two references: a and b, a.equals(b) and b.equals(a);
     * a.equals(a);
     * If a.equals(b) and b.equals(c), then a.equals(c);
     * If a.equals(b) and b.equals(a) then a.hashCode() equals b.hashCode().
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        // One approach
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        // Other approach
        if ((obj == null) || (!(obj instanceof ClassWithTryRes)))
            return false;
        // And next call .equals() for each variable
        // Or (after == this and == null):
        if (this.value.equals(other.getValue()))
            return true;
        // Or (after == this and == null):
         if (obj instanceof ClassWithTryRes) {
            ClassWithTryRes other = (ClassWithTryRes) obj; 
            return Objects.equals(value, other.getValue())
        return false;
    }

    /**
     * Also you can use HashCodeBuilder.
     */
    @Override
    public int hashCode() {
        // Since Java 7
        // return Objects.hash(this.value, this.value2, this.value3);
        return Objects.hash(this.value);
    }

    @Override
    public int compareTo(ClassWithTryRes obj) throws NullPointerException {
        if (money == null)
            throw new NullPointerException();
        // Just for example
        return this.value - obj.getValue();
    }
}

/*
For example:

try (ClassWithTryRes o = new ClassWithTryRes()) {
    o.foo();
}
*/