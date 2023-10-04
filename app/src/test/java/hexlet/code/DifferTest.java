package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    @Test
    public void testGeneratingDifferenceList() throws IOException {
        String expectedResultFilePath = "src/test/resources/expectedDifferenceList.txt";
        Path expectedResultFile = Paths.get(expectedResultFilePath).toAbsolutePath().normalize();
        String expectedResult = Files.readString(expectedResultFile);

        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";
        String result = Differ.generate(filePath1, filePath2);
        assertThat(result).isEqualTo(expectedResult);
    }
}
