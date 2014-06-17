/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package walktest;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interface for Walk over dir.
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public interface WalkDirInterface {
    public static final Logger logger = Logger.getLogger(
                                        WalkDirInterface.class.getName());
    /**
     * Method for process each file object. Need to overwrite.
     * @param file
     * @throws IOException 
     */
    default public void processFile(File file) throws IOException {
        ;
    }
    /**
     * Method for process each directory object. Need to overwrite.
     * @param dir
     * @throws IOException 
     */
    default public void processFolder(File dir) throws IOException {
        ;
    }
    
    /**
     * Main method for walk over dir.
     * @param path
     * @throws IOException 
     */
    default public void run(String path) throws IOException {
        try {
            File dir = new File(path);
            File[] list = dir.listFiles();
            if (list.length != 0)
                for (File f : list) {
                    if (f.isFile()) {
                        processFile(f);
                    } else {
                        processFolder(f);
                        run(f.getCanonicalPath());
                    }
                }
        } catch (NullPointerException e) {
            System.err.println("Err path: " + path);
            logger.log(Level.WARNING, "NullPointerException", e);
        }
    }
    
    /**
     * Main method for walk over dir.
     * @param path
     * @throws IOException 
     */
    default public void run(File path) throws IOException {
        try {
            File[] list = path.listFiles();
            if (list.length != 0)
            for (File f : list) {
                if (f.isFile()) {
                    processFile(f);
                } else {
                    processFolder(f);
                    run(f);
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Err path: " + path.getCanonicalPath());
            logger.log(Level.WARNING, "NullPointerException", e);
        }
    }
}
