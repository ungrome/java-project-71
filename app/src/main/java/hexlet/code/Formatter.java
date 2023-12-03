package hexlet.code;


import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatDiff(List<Map<String, Object>> listOfDiff, String formatName) {
        switch (formatName) {
            case "stylish":
                return Stylish.stylishDiff(listOfDiff);
            case "plain":
                return Plain.plainDiff(listOfDiff);
            case "json":
                return Json.jsonDiff(listOfDiff);
            default:
                throw new RuntimeException("Unknown extension: " + formatName);
        }
    }
}
