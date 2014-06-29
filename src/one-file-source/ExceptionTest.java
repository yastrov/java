/* Exception hierarchy:
    Object
        ^
        |
    Throwable
    ^       ^
    |       |
Exception   Error
    ^
    |
RuntimeException

----------------------------
Assertion have been used in Java default libraries in function pop() before
return array[--number];
assert number<1 : "Stack is empty";
 */

import java.io.FileWriter;
import java.io.IOException;
import com.google.common.io.closer;

/**
 *
 * @author Yuri Astrov
 */
public class ExceptionTest {

    /**
     * Best approach.
     * New version for work with exception handling. (Java 7)
     * Your class must implements AutoCloseable 
     * @throws IOException 
     */
    public static void testTryWithRecources() throws IOException {
        try (FileWriter writer = new FileWriter("\\data.txt");
             FileWriter writerT = new FileWriter("\\data2.txt");) {
            writer.write("Hello world!");
            writerT.write("Hello world!");
        }
    }
    
    /**
     * Throw (raise) exception and catch two in one time.
     */
    public static void testRaiseAndCatchTwoExc() {
        try {
            throw new IOException();
        } catch (IOException | Error e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Old-style approach for one resource.
     */
    public static void testOldStyleOneResource() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("\\data.txt");
            writer.write("Hello world.");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Old style for three resources.
     * @throws IOException 
     */
    public static void testOldStyleThreeResources() throws IOException {
        FileWriter writer = null;
        FileWriter writerTwo = null;
        FileWriter writerThree = null;
        try {
            writer = new FileWriter("\\data.txt");
            writer.write("Hello world.");
            writerTwo = new FileWriter("\\data2.txt");
            writerThree = new FileWriter("\\data3.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException  ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if(writerTwo != null)
                        writerTwo.close();
                } finally {
                    if(writerThree != null)
                        writerThree.close();
                }
            }
        }
    }
    
    /**
     * Old style with Google Guava library
     */
    public static void testGoogleGuavaCloser() {
        Closer closer = Closer.create();
        try {
           OutputStream stream = closer.register(openOutputStream());
        } catch (Throwable e) {
           throw closer.rethrow(e);
        } finally {
           closer.close();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            testTryWithRecources();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}