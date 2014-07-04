import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.Comparator;
import java.util.stream.Stream;

/*
Stream API and Lambda Test.
Лямбда может быть использована там,
где принимается интерфейс с единственной и при этом абстрактной функцией.
(Callable, Runnable, Consumer, Comparator, Predicate, Function).
For parallel work, use (Example):
myList.stream().parallel().map(...)

public interface MyInterface <T> {
    abstract Boolean check(T value);
}
For more, see:
http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
http://winterbe.com/posts/2014/03/16/java-8-tutorial/
http://java.dzone.com/articles/why-we-need-lambda-expressions

Some examples:
*/

public class LambdaStreamTest {

    /**
     * Example for pass Lamdba function as parameter.
     * @param numbers as List of Integers
     * @param p filter Interface
     * @return sum of elements as Integer
     */
    // Predicate<Integer> have been replaced  by MyInterface<Integer>
    public static Integer sum(List<Integer> numbers, MyInterface<Integer> p) {
        Integer total = 0;
        for (int number : numbers) {
            if (p.check(number)) {
                total += number;
            }
        }
        return total;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Integer> myList= Arrays.asList(1,2,3,4,5,6,7,8,9);
        Integer sum = 0;
        sum = sum(myList, n -> n % 2 == 0);
        System.out.println(sum);
        myList.forEach((Integer value) -> System.out.println(value));
        System.out.println(sum);
        
        // Filter ang map and to Array
        Integer[] arr = myList.stream()
                        .filter(n -> n % 2 == 0)
                        .map(n -> n*n)
                        .filter(n -> n < 60)
                         //.forEach(System.out::println);
                        // Stream<Integer> to Integer[]
                        .toArray(size -> new Integer[size]);
        
        System.out.println("Min");
        // Min or Max
        Optional<Integer> min = myList.stream()
                                        .min((x, y) -> Integer.compare(x, y));
        min.ifPresent(System.out::println);
        
        System.out.println("Sort");
        // Sort
        //myList.stream().sorted(); Or with Comparator:
        myList.stream().sorted( (x,y) -> y-x).forEach(System.out::println);
        
        System.out.println("Match");
        // Match
        boolean anyHigherEight = myList.stream()
                                .anyMatch((v) -> v > 8);
        System.out.println(anyHigherEight);
        
        System.out.println("Reduce");
        // Reduce
        Optional<Integer> reduced = myList.stream().reduce( (n1, n2) -> n1+n2);
        reduced.ifPresent(System.out::println);
        // Or
        int sum2 = myList.stream().reduce(0, Integer::sum);
        
        //Create stream manually.
        Stream<String> myStream = Stream.of("aa", "ab", "ac");
        myStream.filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        Comparator<Integer> intCmp = (Integer i1, Integer i2) -> (int) (i2.intValue() - i1.intValue());
    }
}