package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {

        Map<String, Object> map1 = getData(filePath1);
        Map<String, Object> map2 = getData(filePath2);
        return createDiffList(map1, map2);
    }
    public static String defineFormat(String filePath) {
        if (filePath.endsWith("json")) {
            return "json";
        } else if (filePath.endsWith("yaml")) {
            return "yaml";
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

    public static String createDiffList(Map<String, Object> data1,
                                                      Map<String, Object> data2) {
        Map<String, Object> allData = new TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);

        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (String key: allData.keySet()) {
            if (!data1.containsKey(key)) {
                result.append(" + ")
                        .append(key)
                        .append(": ")
                        .append(data2.get(key))
                        .append("\n");
            } else if (data1.containsKey(key) && data2.containsKey(key)) {
                if (data1.get(key).equals(data2.get(key))) {
                    result.append("   ")
                            .append(key)
                            .append(": ")
                            .append(data2.get(key))
                            .append("\n");
                } else {
                    result.append(" - ")
                            .append(key)
                            .append(": ")
                            .append(data1.get(key))
                            .append("\n");
                    result.append(" + ")
                            .append(key)
                            .append(": ")
                            .append(data2.get(key))
                            .append("\n");
                }
            } else if (!data2.containsKey(key)) {
                result.append(" - ")
                        .append(key)
                        .append(": ")
                        .append(data1.get(key))
                        .append("\n");
            }
        }
        result.append("}");
        //System.out.println(result);
        return result.toString();
    }
}
