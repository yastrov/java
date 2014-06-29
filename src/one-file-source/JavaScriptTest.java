import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/* myscript.js content:
var a = 3;
var b = 8;
print(a+b);

function sayString(str) {
    print(str);
}
*/

/**
 * Test JavaScript Engine from Java default library.
 * See also:
 * http://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/
 * @author Yuri Astrov
 */
public class JavaScriptTest {

    public static void testJSScriptEngine() throws ScriptException,
            NoSuchMethodException, FileNotFoundException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // Also you can:
        //scriptEngineMgr.getEngineByExtension("js");
        if (engine == null) {
            System.err.println("No JavaScript engine have been found!");
            System.exit(1);
        }
        System.out.println("Eval string:");
        engine.eval("print('Hello world');");
        Path path =  Paths.get(System.getProperty("user.home")+"/myscript.js");
        engine.eval(new FileReader(path.toString()));
        // Call function from script with parameter
        System.out.println("Call function from script with parameter:");
        Invocable iEngine = (Invocable) engine;
        iEngine.invokeFunction("sayString", "I want to say!");
        // Call function from Java object
        System.out.println("Call function from Java object in script:");
        engine.put("myInteger", new Integer(1));
        engine.eval("print(myInteger.getClass());");
        //-----
        System.out.println("Change object in script:");
        engine.eval("myInteger = 5;");
        Integer newInt = (Integer)engine.get("myInteger");
        System.out.println(newInt);
    }
    
    /**
     * Execute Runnable object, which declared in script.
     */
    public static void testInterface() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        if (engine == null) {
            System.err.println("No JavaScript engine have been found!");
            System.exit(1);
        }
        engine.eval("var obj = new Object();");
        engine.eval("obj.run = function() { print('run method called'); }");
        Object obj = engine.get("obj");
        Invocable iEngine = (Invocable) engine;
        Runnable r = iEngine.getInterface(obj, Runnable.class);
        new Thread(r).start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            testJSScriptEngine();
            testInterface();
        } catch (ScriptException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
