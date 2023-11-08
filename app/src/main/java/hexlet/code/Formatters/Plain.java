package hexlet.code.Formatters;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Plain {
    public static String plainDiff(List<Map<String, Object>> listOfDiff) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> map : listOfDiff) {
            Object plainValue1 = getValue(map.get("value1"));
            Object plainValue2 = getValue(map.get("value2"));
            if (map.containsValue("notChanged")) {
                result.append("");
            } else if (map.containsValue("changed")) {
                result.append("Property \'")
                        .append(map.get("key"))
                        .append("\' was updated. From ")
                        .append(plainValue1)
                        .append(" to ")
                        .append(plainValue2)
                        .append("\n");
            } else if (map.containsValue("added")) {
                result.append("Property \'")
                        .append(map.get("key"))
                        .append("\' was added with value: ")
                        .append(plainValue2)
                        .append("\n");
            } else if (map.containsValue("deleted")) {
                result.append("Property \'")
                        .append(map.get("key"))
                        .append("\' was removed")
                        .append("\n");
            }
        }
        return result.toString().trim();
    }

    public static String getValue(Object value) {

        String stringValue = Objects.toString(value);

        if (stringValue.startsWith("[") && stringValue.endsWith("]")
                || (stringValue.startsWith("{") && stringValue.endsWith("}"))) {
            stringValue = "[complex value]";
        } else if (stringValue.equals("false") || stringValue.equals("true")
                || stringValue.equals("null") || StringUtils.isNumeric(stringValue)) {
            return stringValue;
        } else {
            return "'" + value + "'";
        }
        return stringValue;
    }
}
