/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package walktest;

import java.io.File;
import java.io.IOException;
import java.lang.NullPointerException;
import java.util.logging.*;

/**
 * Class for for Walk over dir. Base for Inheritance.
 * @author  Yuri Astrov (yuriastrov@gmail.com)
 */
public abstract class WalkDir {
    private static final Logger logger = Logger.getLogger(WalkDir.class.getName());
    
    /**
     * Method for process each file object. Must be overwriten.
     * @param file
     * @throws IOException 
     */
    public abstract void processFile(File file) throws IOException;
    
    /**
     * Method for process each directory object. Must be overwriten.
     * @param dir
     * @throws IOException 
     */
    public abstract void processFolder(File dir) throws IOException;
    
    /**
     * Main method for walk over dir.
     * @param path
     * @throws IOException 
     */
    public void run(String path) throws IOException {
        try {
            File dir = new File(path);
            File[] list = dir.listFiles();
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
            logger.log(Level.WARNING, "NullPointerException", e);
        }
    }
    
    /**
     * Main method for walk over dir.
     * @param path
     * @throws IOException 
     */
    public void run(File path) throws IOException {
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
            logger.log(Level.WARNING, "NullPointerException", e);
        }
    }
}