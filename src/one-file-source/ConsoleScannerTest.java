import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Test input variables from Console (System input) via Scanner.
 * @author Yuri Astrov
 */
public class ConsoleScannerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = "1 ptr 2,0 ptr cat ptr";
        String myString;
        try (Scanner s = new Scanner(input).useDelimiter("\\s*ptr\\s*")) {
            int i = s.nextInt();
            System.out.println(i);
            double d = s.nextFloat();
            System.out.println(d);
            myString = s.next();
            System.out.println(myString);
        }
        try (Scanner s = new Scanner(input)) {
            s.findInLine("(\\d+) ptr ([\\d,]+) ptr (\\w+) ptr");
            MatchResult result = s.match();
            for (int i=1; i<=result.groupCount(); i++)
                System.out.println(result.group(i));
        }
        System.out.print("Inptun int number: ->_ ");
        try (Scanner s = new Scanner(System.in)) {
            int i = s.nextInt();
            System.out.println(i);
        }
    }
}