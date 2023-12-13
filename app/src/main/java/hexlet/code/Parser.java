package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> toMap(String contentFromFile, String fileFormat)
            throws JsonProcessingException, RuntimeException {
        switch (fileFormat) {
            case "yml":
            case "yaml":
                return parseYaml(contentFromFile);
            case "json":
                return parseJson(contentFromFile);
            default:
                throw new RuntimeException("Unknown extension: " + fileFormat);
        }
    }
    public static Map<String, Object> parseYaml(String content)
            throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, new TypeReference<>() { });
    }

    public static Map<String, Object> parseJson(String content)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() { });
    }
}