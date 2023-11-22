package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    private static String expectedStylish = "";
    private static String expectedJson = "";
    private static String expectedPlain = "";
    public static String read(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
    @BeforeAll
    public static void initExpect() throws IOException {
        expectedStylish = read("src/test/resources/stylish.txt");
        expectedPlain = read("src/test/resources/plain.txt");
        expectedJson = read("src/test/resources/json.txt");
    }
    @Test
    public void jsonTest() throws IOException {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String result = Differ.generate(filepath1, filepath2, "json");
        assertThat(result).isEqualTo(expectedJson);
    }
    @Test
    public void stylishTest() throws IOException {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String result = Differ.generate(filepath1, filepath2, "stylish");
        assertThat(result).isEqualTo(expectedStylish);
    }
    @Test
    public void plainTest() throws IOException {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String result = Differ.generate(filepath1, filepath2, "plain");
        assertThat(result).isEqualTo(expectedPlain);
    }
}
