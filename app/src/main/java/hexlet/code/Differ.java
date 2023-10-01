package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        TypeReference<HashMap<String, Object>> type = new TypeReference<>() { };
        Path file1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path file2 = Paths.get(filePath2).toAbsolutePath().normalize();

        String stringsFromFile1 = Files.readString(file1);
        String stringsFromFile2 = Files.readString(file2);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map1 = objectMapper.readValue(stringsFromFile1, type);
        Map<String, Object> map2 = objectMapper.readValue(stringsFromFile2, type);
        return createDiffList(map1, map2);
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
            }
            else if (!data2.containsKey(key)) {
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
