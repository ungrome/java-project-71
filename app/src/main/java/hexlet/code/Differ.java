package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);
        List<Map<String, Object>> differenceList = DiffBuilder.createListOfDiff(data1, data2);
        return Formatter.formatDiff(differenceList, format);
    }
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
    public static String defineFormat(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }
    public static Map<String, Object> getData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String contentFromFile = Files.readString(path);
        String fileFormat = defineFormat(filePath);
        return Parser.toMap(contentFromFile, fileFormat);
    }
}
