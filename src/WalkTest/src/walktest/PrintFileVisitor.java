/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package walktest;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


/*
For use, in main:

PrintFileVisitor visitor = new PrintFileVisitor();
Path myPath = FileSystems.getDefault().getPath(path);
Files.walkFileTree(myPath, visitor);
*/

/**
 * Walk over dir tree, using SimpleFileVisitor.
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
public class PrintFileVisitor extends SimpleFileVisitor<Path> {
    private long fullSize = 0;
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
            System.out.format("File: %s ", file);
        } else {
            System.out.format("Other: %s ", file);
        }
        System.out.println("(" + attr.size() + "bytes)");
        fullSize += attr.size();
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir,  BasicFileAttributes attrs) {
        System.out.format("Enter to the directory: %s%n", dir);
        return CONTINUE;
    }
    
    @Override
    public FileVisitResult postVisitDirectory (Path dir, IOException exc) {
        System.out.format("Escape from the directory: %s%n", dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
                                       IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }
    
    /**
     * Get full size for all files in path.
     * @return size in bytes
     */
    public long getFullSize() {
        return fullSize;
    }
}
