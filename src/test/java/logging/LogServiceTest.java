package logging;

import basic.Reject;
import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogServiceTest {

    private static final Map<String, Map<String, String>> JAVA_FILES = new HashMap<>();

    @Test
    void packageOrgApacheLogging_UsageOfPackageOnlyIn2Classes() {
        Set<String> exclusions = Sets.newHashSet("LogServiceTest.java", "Log4j2LogService.java");
        Collection<String> errors = getFiles(getClass().getResource("/").getFile() + "../../src",
                entry -> !exclusions.contains(entry.getKey()) && StringUtils.containsAny(entry.getValue(), "org.apache.logging", "java.util.logging"));
        assertTrue(errors.isEmpty(), StringUtils.join(errors, "\n"));
    }



    public static Collection<String> getFiles(String folder, Predicate<Map.Entry<String, String>> filter) {
        return getFiles(folder).entrySet().stream().filter(filter).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    public static Map<String, String> getFiles(String folder) {
        JAVA_FILES.computeIfAbsent(folder, s -> {
            File documentFolder = new File(folder);
            Collection<File> files = FileUtils.listFiles(documentFolder, new String[] { "java" }, true);
            Map<String, String> content = new HashMap<>();
            files.forEach(f -> {
                try {
                    content.put(f.getName(), FileUtils.readFileToString(f, Charset.defaultCharset()));
                } catch (IOException e) {
                    throw Reject.developmentError("java-Datei kann nicht gelesen werden!", e);
                }
            });
            LogManager.getLogger().info(String.format("Count..%s Java-Dateien in %s", files.size(), folder));
            return content;
        });
        return JAVA_FILES.get(folder);
    }

}