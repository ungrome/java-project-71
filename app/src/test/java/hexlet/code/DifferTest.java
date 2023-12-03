package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
        String filepath1 = "src/test/resources/fixtures/file1.json";
        String filepath2 = "src/test/resources/fixtures/file2.json";
        String result = Differ.generate(filepath1, filepath2, "json");
        Assertions.assertEquals(result, expectedJson);
    }
    @Test
    public void stylishTestJson() throws IOException {
        String filepath1 = "src/test/resources/fixtures/file1.json";
        String filepath2 = "src/test/resources/fixtures/file2.json";
        String result = Differ.generate(filepath1, filepath2, "stylish");
        Assertions.assertEquals(result, expectedStylish);
    }
    @Test
    public void stylishTestYaml() throws IOException {

        String formatName = "stylish";
        String filePath1 = "src/test/resources/fixtures/file1.yaml";
        String filePath2 = "src/test/resources/fixtures/file2.yaml";
        String actual = Differ.generate(filePath1, filePath2, formatName);
        Assertions.assertEquals(actual, expectedStylish);
    }
    @Test
    public void plainTest() throws IOException {
        String filepath1 = "src/test/resources/fixtures/file1.json";
        String filepath2 = "src/test/resources/fixtures/file2.json";
        String result = Differ.generate(filepath1, filepath2, "plain");
        Assertions.assertEquals(result, expectedPlain);
    }
}
