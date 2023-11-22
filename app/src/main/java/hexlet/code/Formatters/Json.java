package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public final class Json {
    public static String jsonDiff(List<Map<String, Object>> listOfDiff) {
        try {
            return new ObjectMapper().writeValueAsString(listOfDiff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
