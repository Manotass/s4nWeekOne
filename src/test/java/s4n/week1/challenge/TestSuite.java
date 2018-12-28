package s4n.week1.challenge;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        FunctionalIterfazTest.class,
        StreamsTest.class
})

public class TestSuite {
}
