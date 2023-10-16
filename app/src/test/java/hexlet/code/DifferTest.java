package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    private static String expectPlain = "";
    private static String expected = "";
    public static String read(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
    @BeforeAll
    public static void initExpect() throws IOException {
       // expectPlain = read("src/test/resources/plain.txt");
        expected = read("src/test/resources/expectedResult.txt");
    }
    @Test
    public void jsonTest() throws IOException {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String result = Differ.generate(filepath1, filepath2);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void yamlTest() throws IOException {
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        String result = Differ.generate(filepath1, filepath2);
        assertThat(result).isEqualTo(expected);
    }
}
