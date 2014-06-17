import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Example for work with Encoding.
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class EncodingTest {

    /***
     * Set options for output in Windows console.
     */
    public static void setWinPrintStream() {
        try {
            PrintStream ps = new PrintStream (System.out, true, "Cp866");
            System.setOut(ps);
        } catch(UnsupportedEncodingException e) {
            System.err.println("Environment doesn't support Cp866 encoding");
            e.printStackTrace();
        }
    }
    
    /***
     * Read file with name filename, with UTF-8 Encoding.
     * @param filename 
     */
    public static void printUTF8File (String filename) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filename), "UTF-8"))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /***
     * Encoding to byte[] and decoding from UTF-8 byte[].
     */
    public static void toBytesFromBytes () {
        try {
            String s = "Hello world\u2026";
            byte[] utf8 = s.getBytes("UTF-8");
            System.out.println(utf8);
            s = new String(utf8, "UTF-8");
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        toBytesFromBytes();
    }
}
