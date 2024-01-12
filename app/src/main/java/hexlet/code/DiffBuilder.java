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

            Map<String, Object> actualNode = new HashMap<>();
            actualNode.put("key", key);

            if (data1.containsKey(key) && data2.containsKey(key)) {
                boolean checkNull = data1.get(key) != null && data2.get(key) != null;
                if (checkNull && data1.get(key).equals(data2.get(key))) {
                    actualNode.put("keyStatus", "notChanged");
                    actualNode.put("value1", data1.get(key));
                } else {
                    actualNode.put("value1", data1.getOrDefault(key, null));
                    actualNode.put("value2", data2.getOrDefault(key, null));
                    actualNode.put("keyStatus", "changed");
                }
            } else if (data1.containsKey(key)) {
                actualNode.put("keyStatus", "deleted");
                actualNode.put("value1", data1.getOrDefault(key, null));
            } else {
                actualNode.put("keyStatus", "added");
                actualNode.put("value2", data2.getOrDefault(key, null));
            }
          listOfDiff.add(actualNode);
        }
        return listOfDiff;
    }
}

