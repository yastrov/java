package walktest;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WalkStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Path start = FileSystems.getDefault().getPath("/");
            Files.walk(start)
                    .filter( path -> path.toFile().canRead())
                    .filter( path -> path.toFile().isFile())
                    .filter( path -> path.toString().endsWith(".mp3"))
                    .forEach( System.out::println );
        } catch(java.nio.file.AccessDeniedException ex) {
            Logger.getLogger(Walk.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf(ex.toString());
        } 
        catch (IOException ex) {
            Logger.getLogger(Walk.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf(ex.toString());
        }
    }
}
