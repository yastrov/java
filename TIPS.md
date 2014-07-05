# Java Tips

## Compare objects
The == binary operator compares memory addresses.  
The .equals() method compares values.  
For string compare use (NRE-safe): "hello".equals(myString);  

For compare two float or double a and b variables for equal:  
Math.abs(a-b) < 1e-6

Integer may compare with int via == operator. Integer with other Integer - only via equals() or via convert to int with Integer.intValue().

## Immutable types
There are common immutable types:
- java.lang.String
- The wrapper classes for the primitive types: java.lang.Integer, java.lang.Byte, java.lang.Character, java.lang.Short, java.lang.Boolean, java.lang.Long, java.lang.Double, java.lang.Float
- enum classes
- java.math.BigInteger and java.math.BigDecimal

Do not use Integer or other in for loop as counter. (More good create via Integer.valueOf() for cache optimizing.)

## Switch research example
Switch work over equal(). For more see [Switch Tutorial](http://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html).
    String day = new StringBuilder(10)
                    .append("hel").append("lo")
                    .toString();
    switch (day) {
        case "hello":
                // It work ok!
                System.out.println("I take: hello!");
                break;
        default:
                System.out.println("Error!");
                break;
    }
    System.out.println(day == "hello");
    
    Integer b = Integer.valueOf(6);;
    switch (b) {
        case 6: 
                // It work ok!
                System.out.println("I take: 6!");
                break;
        default: 
                System.out.println("Error!");
                break;
    }