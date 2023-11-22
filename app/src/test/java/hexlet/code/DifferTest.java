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
    private static String expectedResult = "";
    public static String read(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
    @BeforeAll
    public static void initExpect() throws IOException {
        expectedResult = read("src/test/resources/expectedResult.txt");
        expectedStylish = read("src/test/resources/stylish.txt");
        expectedPlain = read("src/test/resources/plain.txt");
        expectedJson = read("src/test/resources/json.txt");
    }
    @Test
    public void json() throws IOException {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String result = Differ.generate(filepath1, filepath2);
        Assertions.assertEquals(result,expectedResult);
    }
    @Test
    public void yaml() throws IOException {
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        String result = Differ.generate(filepath1, filepath2);
        Assertions.assertEquals(result,expectedResult);
    }
    @Test
    public void jsonTest() throws IOException {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String result = Differ.generate(filepath1, filepath2, "json");
        Assertions.assertEquals(result,expectedJson);
    }
    @Test
    public void stylishTest() throws IOException {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String result = Differ.generate(filepath1, filepath2, "stylish");
        Assertions.assertEquals(result,expectedStylish);
    }
    @Test
    public void plainTest() throws IOException {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String result = Differ.generate(filepath1, filepath2, "plain");
        Assertions.assertEquals(result,expectedPlain);
    }
}
