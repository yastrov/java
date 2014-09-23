import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import static java.nio.charset.StandardCharsets.UTF_8;

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
     * Java 7 style for open file.
     * Read and print File to console.
     * @param filename - String with name of file.
     * @param charsetStr - name of charset encoding as String.
     * @throws IOException 
     */
    public static void printFileViaBuffered (String filename, String charsetStr)
            throws IOException {
        Path path = Paths.get(filename);
        Charset charset = Charset.forName(charsetStr);
        String line = null;
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
    
    public static void copyFileViaBuffered (String filename, String newFilename,
            String charsetStr) throws IOException {
        Path file = Paths.get(filename);
        Path newFile = Paths.get(newFilename);
        Charset charset = Charset.forName(charsetStr);
        int data = 0;
        try (BufferedReader reader = Files.newBufferedReader(file, charset);
             BufferedWriter writer = Files.newBufferedWriter(newFile, charset,
                      StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            while ((data = reader.read()) != -1) {
                writer.write(data);
            }
        }
    }
    
    /**
    * java.nio.file.Files.copy analog.
    * @param filename
    * @param newFilename
    */
    public static void copyFilevViaStream (String filename, String newFilename)
            throws IOException {
        Path file = Paths.get(filename);
        Path newFile = Paths.get(newFilename);
        int data = 0;
        try (InputStream in = Files.newInputStream(file);
             OutputStream out = Files.newOutputStream(newFile, 
                      StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            byte[] buffer = new byte[2*1024];
            while ((data = in.read(buffer)) != -1) {
                out.write(buffer, 0, data);
            }
        }
    }


    public static void readByLines(String filename)  throws IOException {         
        try( Stream<String> lines = Files.lines( filename, StandardCharsets.UTF_8 ) )
        {
            for( String line : (Iterable<String>) lines::iterator )
            {
                ;
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        toBytesFromBytes();
        String home = System.getProperty("user.home");
        try {
            printFileViaBuffered(home+"/.bash_history", "UTF-8");
            copyFilevViaStream(home+"/.bash_history", home+"/.bash_history123");
        } catch (IOException ex) {
            ex.printStackTrace();;
        }
    }
}