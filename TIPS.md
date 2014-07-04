# Java Tips

## Compare objects
The == binary operator compares memory addresses.  
The .equals() method compares values.  
For string compare use (NRE-safe): "hello".equals(myString);  

For compare two float or double a and b variables for equal:  
Math.abs(a-b) < 1e-6

## Switch research example
Switch work over equal(). For more see [Switch Tutorial](http://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html).
    String day = new StringBuilder()
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
    
    Integer i = 6;
    Integer b = new Integer(6);
    switch (b) {
        case 6: 
                // It work ok!
                System.out.println("I take: 6!");
                break;
        default: 
                System.out.println("Error!");
                break;
    }