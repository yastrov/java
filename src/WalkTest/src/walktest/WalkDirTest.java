/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package walktest;

import java.io.File;
import java.io.IOException;

/**
 * This class complete WalkDir abstract class.
 * It may be class with main function (WalkTest), but no:). 
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class WalkDirTest  extends WalkDir {

    @Override
    public void processFile(File file) throws IOException  {
        System.out.println("-> File: " + file.getCanonicalPath());
    }
    
    @Override
    public void processFolder(File dir) throws IOException {
        System.out.println("-> Dir: " + dir.getCanonicalPath());
    }
}
