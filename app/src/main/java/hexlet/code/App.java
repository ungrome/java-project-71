package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
            description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;
    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.")
    private String help;
    @Option(names = {"-v", "--version"}, description = "Print version information and exit.")
    private String version;
    @Parameters(paramLabel = "filePath1", index = "0", description = "path to first file")
    private String filePath1;
    @Parameters(paramLabel = "filePath2", index = "1", description = "path to second file")
    private String filePath2;
    @Override
    public Integer call() throws Exception {
        try {
            String diff = Differ.generate(filePath1, filePath2);
            System.out.println(diff);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return 0;
        }
        return 1;
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
