package logging;

import com.google.common.collect.Sets;
import great.common.testframework.Assert;
import great.common.testframework.TestUtilities;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

public class LogServiceTest {

    @Test
    void packageOrgApacheLogging_UsageOfPackageOnlyIn2Classes() {
        Set<String> exclusions = Sets.newHashSet("LogServiceTest.java", "Log4j2LogService.java");
        Collection<String> errors = TestUtilities.getFiles(getClass().getResource("/").getFile() + "../../src",
                entry -> !exclusions.contains(entry.getKey()) && StringUtils.containsAny(entry.getValue(), "org.apache.logging", "java.util.logging"));
        Assert.assertEmpty(StringUtils.join(errors, "\n"), errors);
    }

}