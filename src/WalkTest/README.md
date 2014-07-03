# WalkTeet

Example for walk over folder on disk.

Example contain approaches:  
-  implements Interface with abstract and complete method;
-  abstract class with inheritance;
-  DirectoryStream;
-  SimpleFileVisitor;

## For work with files and Directories

    String stringPath = System.getProperty("user.home");
    // First: get path

    Path myPath = java.nio.file.Paths.get(stringPath);
    // Or
    Path myPath = FileSystems.getDefault().getPath(stringPath);

    // Now do something:
    Files.createDirectories(myPath);