package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
            description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.")
    private String help;
    @Option(names = {"-v", "--version"}, description = "Print version information and exit.")
    private String version;
    @Override
    public Integer call() throws Exception {
        return null;
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

