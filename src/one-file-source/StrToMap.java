/**
  * Convert string "key1:val1:key2:val2" to Map object:
  * 
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 *  Convert string "key1:val1:key2:val2" to hMap.
 *  @author Yuri Astrov (yuriastrov@gmail.com)
 *          Юрий Астров
 */
public class StrToMap {

    /**
     * Convert string to Map. Naive realization.
     * @param string with format "key1:val1:key2:val2"
     * @return <code>Map<String, String></code>
     */
    public static Map<String, String> strToMapV1(String string) {
        Map<String, String> map = new HashMap<String, String>();
        if (string.contains(":")) {
            String[] parts = string.split(":");
            for(int i=0; i<(parts.length-1); i+=2 )
            {
                map.put(parts[i], parts[i+1]);
            }
        } else {
            throw new IllegalArgumentException("String " + string + " does not contain :");
        }
        return map;
    }

    /**
     * Convert string to Map. Cycle counter version.
     * @param string with format "key1:val1:key2:val2"
     * @return <code>Map<String, String></code>
     */
    public static Map<String, String> strToMapV2(String string) {
        Map<String, String> map = new HashMap<String, String>();
        if (string.contains(":")) {
            int i = 0;
            String[] parts = string.split(":");
            String key="", val="";
            for (String s: parts){
                if (i%2 == 0){
                    key = s;
                }
                else {
                    val = s;
                    map.put(key, val);
                }
                i++;
            }
        } else {
            throw new IllegalArgumentException("String " + string + " does not contain :");
        }
        return map;
    }

    /**
     * Convert string to Map.
     * @param string with format "key1:val1:key2:val2"
     * @return <code>Map<String, LinkedList<String>></code>
     */
    public static Map<String, LinkedList<String>> strToMapWListV2(String string) {
        Map<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
        if (string.contains(":")) {
            String[] parts = string.split(":");
            for(int i=0; i<(parts.length-1); i+=2 )
            {
                LinkedList<String> values = map.get(parts[i]);
                if (values==null) {
                    // Old style:
                    //values = new LinkedList<String>();
                    values = new LinkedList<>();
                    map.put(parts[i], values);
                }
                values.add(parts[i+1]);
            }
        } else {
            throw new IllegalArgumentException("String " + string + " does not contain delimeter ':'");
        }
        return map;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String string = "key1:val1:key2:val2:key3:val3:key1:val4";
        if (args.length > 0) {
            string = args[0];
        }
        try {
            Map<String, String> map = strToMapV2(string);
            Map<String, LinkedList<String>> mapl = strToMapWListV2(string);
            for(Map.Entry entry : map.entrySet()) {
                System.out.println( entry.getKey() + " " + entry.getValue() );
            }
            System.out.println( map );
            for (String key : map.keySet()) {
                System.out.println("Key = " + key);
            }
            // Java 1.8 Style
            map.keySet().stream().forEach((key) -> {
                    System.out.println("Key = " + key);
                });
            // Or
            map.forEach((id, val) -> System.out.println(val));
            
            String key, val;
            for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ){
                key = it.next();
                val = map.get(key);
                System.out.println( key + " " + val );
            }
            System.out.println( mapl );
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
    }
}
