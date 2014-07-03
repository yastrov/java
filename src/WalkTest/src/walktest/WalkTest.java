/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package walktest;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Walk over dir example.
 * @author   
 */
public class WalkTest implements WalkDirInterface {
    private static final Logger logger = Logger.getLogger(WalkTest.class.getName());
    private static final String logFileName = "logger.log";
    
    /**
     * Print files in dir via DirectoryStream class.
     * @param dir 
     */
    public static void printViaDirectorySteram (Path dir) {
        /*
        AT! If you return ArrayList or others - you can overflow memory!
        Also you can create filter
       DirectoryStream.Filter<Path> filter = newDirectoryStream.Filter<Path>()*/
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{c,h,cpp,hpp,java}")) {
            for (Path entry: stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException | DirectoryIteratorException ex) {
           ex.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = System.getProperty("user.home");
        if (args.length > 0) {
            path = args[0];
        }
        // Create instance of this for call 'run' method.
        WalkTest myApp = new WalkTest();
        try {
            Handler fh = new FileHandler(logFileName);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            //-------------------
            System.out.println("Interface test:");
            myApp.run(path);
            //--------------------
            System.out.println("Inheritance test:");
            WalkDirTest walker = new WalkDirTest();
            walker.run(path);
            //--------------------
            System.out.println("SimpleFileVisitor test:");
            PrintFileVisitor visitor = new PrintFileVisitor();
            Path myPath = FileSystems.getDefault().getPath(path);
            Files.walkFileTree(myPath, visitor);
            System.out.println("Size of all files (bytes): " + 
                                visitor.getFullSize());
        } catch (IOException e) {
            logger.log(Level.WARNING, null, e);
        }
        logger.fine("done");
    }
    
    // Overriding the interface method
    @Override
    public void processFile(File file) throws IOException  {
        System.out.println("-> File: " + file.getCanonicalPath());
    }
    
    // Overriding the interface method
    @Override
    public void processFolder(File dir) throws IOException {
        System.out.println("-> Dir: " + dir.getCanonicalPath());
    }
}