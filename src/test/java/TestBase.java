import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import steps.assertions.*;

import static com.codeborne.selenide.Selenide.*;


public abstract class TestBase {
    protected static String displayName;
    protected Steps steps = new Steps();
    protected AssertSteps assertSteps = new AssertSteps();

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
    }
    @BeforeEach
    public void setDisplayName(final TestInfo testInfo) {
        displayName = testInfo.getDisplayName();
    }

    @BeforeEach
    public void openSite() {
        open("https://stepik.org/catalog");
    }

    @AfterEach
    public void closeSite() {
        closeWindow();
        closeWebDriver();
    }
}
