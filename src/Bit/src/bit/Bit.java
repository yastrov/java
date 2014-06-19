/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bit;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class Bit {
    private static String firstPathName="", secondPathName="", bitrate="64",
                            command = "sox";

    public static void printHelp() {
        System.out.println(
            "This is utility-wrapper around unix SoX command for mp3 files:");
        System.out.println("Use:");
        System.out.println(
             "java -jar Bit.jar -b 64 -c C:\\sox.exe dirFrom dirFrom dirTo");
        System.out.println("-b\tbitrate for result file (64 by default)");
        System.out.println(
            "-c\tother command for call SoX for example: C:\\sox.exe dirFrom");
        System.out.println("dirFrom\tpath to original files (required)");
        System.out.println("dirTo\tpath to new files");
    }
    
    public static void parseArgs(String[] args) {
        int argsLength = args.length;
        if (argsLength == 0) {
            printHelp();
            System.exit(0);
        }
        for (int i=0; i < argsLength; i++) {
            if (args[i].equalsIgnoreCase("-h") ||
                      args[i].equalsIgnoreCase("--help")) {
                printHelp();
                System.exit(0);
            } else {
                if (args[i].equalsIgnoreCase("-b") ) {
                    bitrate = args[i+1];
                } else {
                    if (args[i].equalsIgnoreCase("-c") ) {
                        command = args[i+1];
                    }
                }      
            }
        }
        if (argsLength %2 == 0) {
            firstPathName =  args[argsLength-2];
            secondPathName = args[argsLength-1];
        } else {
            firstPathName =  args[argsLength-1];
            secondPathName = firstPathName + "-" + bitrate;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            parseArgs(args);
            ConverterFileVisitor visitor = new ConverterFileVisitor(
                            firstPathName, secondPathName, command,  bitrate);
            Path myPath = FileSystems.getDefault().getPath(firstPathName);
            Files.walkFileTree(myPath, visitor);
        } catch (IOException ex) {
            Logger.getLogger(Bit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
