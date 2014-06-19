/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bit;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Executor for SoX command utility.
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class ExecutorClass {
    private static File log;
    private static File home;
    
    public ExecutorClass() {
        log = new File(System.getProperty("user.home"),"Bitlog.log");
        home = new File(System.getProperty("user.home"));
    }
    
    /**
     * Execute SoX command utility
     * @param command - send "sox" by default
     * @param bitrate - bitrate as String
     * @param iName - Filename, what to convert
     * @param oName  - Filename after convert
     */
    public void execute(String command, String bitrate, String iName,
                            String oName) throws InterruptedException {
        try {//, "-t mp3"
            ProcessBuilder pb =
                    new ProcessBuilder(command, iName, "-C", bitrate, oName);
            //Map<String, String> env = pb.environment();
            pb.directory(home);
            pb.redirectErrorStream(true);
            pb.redirectOutput(Redirect.appendTo(log));
            Process p = pb.start();
            int rc = p.waitFor();
            /*assert pb.redirectInput() == Redirect.PIPE;
            assert pb.redirectOutput().file() == log;
            assert p.getInputStream().read() == -1;*/
        } catch (IOException ex) {
            Logger.getLogger(ExecutorClass.class.getName()).log(Level.SEVERE,
                                null, ex);
            System.err.println( "SoX: "+command +" -C "+ bitrate
                                +" "+ iName +" "+ oName);
            System.err.println(ex);
        }
    }
}
