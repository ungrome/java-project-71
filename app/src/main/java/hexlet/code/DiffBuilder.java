package hexlet.code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DiffBuilder {
    public static List<Map<String, Object>> createListOfDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> allData = new TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);
        List<Map<String, Object>> listOfDiff = new LinkedList<>();

        for (String key : allData.keySet()) {
            Object val1 = (data1.get(key) == null ? "null" : data1.get(key));
            Object val2 = (data2.get(key) == null ? "null" : data2.get(key));
            Map<String, Object> actualNode = new HashMap<>();
            actualNode.put("key", key);
            boolean isContainsBothKey = (data1.containsKey(key) && data2.containsKey(key));
            boolean isTheSameValue = val1.equals(val2);
            if (isContainsBothKey && isTheSameValue) {
                actualNode.put("property", "notChanged");
                actualNode.put("value1", val1);
            } else if (isContainsBothKey) {
                actualNode.put("property", "changed");
                actualNode.put("value1", val1);
                actualNode.put("value2", val2);
            } else if (data1.containsKey(key)) {
                actualNode.put("property", "deleted");
                actualNode.put("value1", val1);
            } else if (data2.containsKey(key)) {
                actualNode.put("property", "added");
                actualNode.put("value2", val2);
            }
            listOfDiff.add(actualNode);
        }
        return listOfDiff;
    }
}

