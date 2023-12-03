package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> toMap(String contentFromFile, String fileFormat)
            throws JsonProcessingException {
        ObjectMapper mapper;
        switch (fileFormat) {
            case "json":
                mapper = new ObjectMapper();
                break;
            case "yml":
            case "yaml":
                mapper = new YAMLMapper();
                break;
            default:
                throw new RuntimeException("Unknown extension: " + fileFormat);
        }
        return mapper.readValue(contentFromFile, new TypeReference<>() { });
    }
}
