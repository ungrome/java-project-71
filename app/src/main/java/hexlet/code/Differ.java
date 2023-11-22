package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Map<String, Object> map1 = getData(filePath1);
        Map<String, Object> map2 = getData(filePath2);
        List<Map<String, Object>> differenceList = DiffBuilder.createListOfDiff(map1, map2);
        return Formatter.formatDiff(differenceList, format);
    }
    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
    public static String defineFormat(String filePath) {
        if (filePath.endsWith("json")) {
            return "json";
        } else if (filePath.endsWith("yaml")) {
            return "yaml";
        } else if (filePath.endsWith("yml")) {
            return "yml";
        } else {
            throw new RuntimeException("File '" + filePath + "' is in an unknown format");
        }
    }
    public static Map<String, Object> getData(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String stringsFromFile = Files.readString(path);
        String fileFormat = defineFormat(filePath);
        return Parser.toMap(stringsFromFile, fileFormat);
    }
}
