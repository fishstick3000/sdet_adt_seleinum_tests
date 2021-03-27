import e2e_tests.firstTest;
import e2e_tests.fourthTest;
import e2e_tests.secondTest;
import e2e_tests.thirdTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        firstTest.class,
        secondTest.class,
        thirdTest.class,
        fourthTest.class
})

public class E2E_test_suite {
}
