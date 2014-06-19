/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bit;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for visit all folders and call Executor maethod for each file.
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class ConverterFileVisitor extends SimpleFileVisitor<Path> {
    private static String firstPathName, secondPathName, command, bitrate;
    private static ExecutorClass executor;
    private String iFile, oFile;
    
    /**
     * Constructor.
     * @param firstPathName - source folder
     * @param secondPathName - destination folder
     * @param command - command for conver (use "sox" by default)
     * @param bitrate - bitrate
     */
    public ConverterFileVisitor(String firstPathName, String secondPathName,
                                String command, String bitrate) {
        this.firstPathName = firstPathName;
        this.secondPathName = secondPathName;
        this.command = command;
        this.bitrate = bitrate;
        this.executor = new ExecutorClass();
        File f = new File(secondPathName);
        if (!f.exists()) { f.mkdirs(); }
    }
    
    private String newPath(String path) {
        return path.replaceAll(firstPathName, secondPathName);
    }
    
    /**
     * Get extension for path
     * @param path path as String
     * @return String with extension
     */
    private String getExtension(String path) {
        String extension = "";
        int i = path.lastIndexOf('.');
        if (i >= 0) {
            extension = path.substring(i+1);
        }
        return extension;
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
            try {
                iFile = file.toFile().getCanonicalPath();
                oFile = newPath(iFile);
                if (getExtension(iFile).equalsIgnoreCase("mp3")) {
                    executor.execute(command, bitrate, iFile, oFile);
                } else {
                    Files.copy(file,new File(oFile).toPath(),REPLACE_EXISTING);
                }
            } catch (IOException ex) {
                Logger.getLogger(ConverterFileVisitor.class.getName()).log(
                                                    Level.SEVERE, null, ex);
                try {
                    System.err.println(file.toFile().getCanonicalPath());
                } catch (IOException ex1) {
                    Logger.getLogger(ConverterFileVisitor.class.getName()).log(
                                                       Level.SEVERE, null, ex1);
                }
                System.err.println(ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConverterFileVisitor.class.getName()).log(
                                    Level.SEVERE, null, ex);
                System.err.println(ex);
            }
            
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir,  
            BasicFileAttributes attrs) {
        try {
            String s = dir.toFile().getCanonicalPath();
            s = newPath(s);
            new File(s).mkdirs();
        } catch (IOException ex) {
            Logger.getLogger(ConverterFileVisitor.class.getName()).log(
                                Level.SEVERE, null, ex);
        }
        return CONTINUE;
    }
}
