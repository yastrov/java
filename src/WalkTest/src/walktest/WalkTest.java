/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package walktest;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Walk over dir example.
 * @author  Yuri Astrov (yuriastrov@gmail.com)
 */
public class WalkTest implements WalkDirInterface {
    private static final Logger logger = Logger.getLogger(WalkTest.class.getName());
    private static final String logFileName = "logger.log";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = System.getProperty("user.home");
        if (args.length > 0) {
            path = args[0];
        }
        WalkTest myApp = new WalkTest();
        try {
            Handler fh = new FileHandler(logFileName);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            System.out.println("Interface test:");
            myApp.run(path);
            System.out.println("Inheritance test:");
            WalkDirTest walker = new WalkDirTest();
            walker.run(path);
        } catch (IOException e) {
            logger.log(Level.WARNING, null, e);
        }
        logger.fine("done");
    }
    
    @Override
    public void processFile(File file) throws IOException  {
        System.out.println("-> File: " + file.getCanonicalPath());
    }
    
    @Override
    public void processFolder(File dir) throws IOException {
        System.out.println("-> Dir: " + dir.getCanonicalPath());
    }
}