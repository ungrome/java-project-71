package hexlet.code.Formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylishDiff(List<Map<String, Object>> listOfDiff) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (Map<String, Object> map: listOfDiff) {
            if (map.containsValue("notChanged")) {
                result.append("    ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get("value1"))
                        .append("\n");
            } else if (map.containsValue("changed")) {
                result.append("  - ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get("value1"))
                        .append("\n");
                result.append("  + ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get("value2"))
                        .append("\n");
            } else if (map.containsValue("added")) {
                result.append("  + ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get("value2"))
                        .append("\n");
            } else if (map.containsValue("deleted")) {
                result.append("  - ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get("value1"))
                        .append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
