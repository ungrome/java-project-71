package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    private static String expectedStylish = "";
    private static String expectedJson = "";
    private static String expectedPlain = "";
    private static String jsonFilePath1;
    private static String jsonFilePath2;
    private static String yamlFilePath1;
    private static String yamlFilePath2;

    public static String read(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
    @BeforeAll
    public static void initExpect() throws Exception {
        expectedStylish = read("src/test/resources/stylish.txt");
        expectedPlain = read("src/test/resources/plain.txt");
        expectedJson = read("src/test/resources/json.txt");
        jsonFilePath1 = "src/test/resources/fixtures/file1.json";
        jsonFilePath2 = "src/test/resources/fixtures/file2.json";
        yamlFilePath1 = "src/test/resources/fixtures/file1.yaml";
        yamlFilePath2 = "src/test/resources/fixtures/file2.yaml";
    }
    @Test
    public void defaultFormatTest() throws Exception {
        String resultJson = Differ.generate(jsonFilePath1, jsonFilePath2);
        Assertions.assertEquals(resultJson, expectedJson);
        String resultYaml = Differ.generate(yamlFilePath1, yamlFilePath2);
        Assertions.assertEquals(resultYaml, expectedJson);
    }
    @Test
    public void jsonTest() throws Exception {
        String resultJson = Differ.generate(jsonFilePath1, jsonFilePath2, "json");
        Assertions.assertEquals(resultJson, expectedJson);
        String resultYaml = Differ.generate(yamlFilePath1, yamlFilePath2, "json");
        Assertions.assertEquals(resultYaml, expectedJson);
    }
    @Test
    public void stylishTest() throws Exception {
        String resultJson = Differ.generate(jsonFilePath1, jsonFilePath2, "stylish");
        Assertions.assertEquals(resultJson, expectedStylish);
        String resultYaml = Differ.generate(yamlFilePath1, yamlFilePath2, "stylish");
        Assertions.assertEquals(resultYaml, expectedStylish);
    }
    @Test
    public void plainTest() throws Exception {
        String resultJson = Differ.generate(jsonFilePath1, jsonFilePath2, "plain");
        Assertions.assertEquals(resultJson, expectedPlain);
        String resultYaml = Differ.generate(yamlFilePath1, yamlFilePath2, "plain");
        Assertions.assertEquals(resultYaml, expectedPlain);
    }
}
