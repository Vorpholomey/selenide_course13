package pages;

import com.codeborne.selenide.*;
import lombok.extern.slf4j.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constants.*;
import static utils.WaitVisibleElement.*;

@Slf4j
public class CoursePage {
    private static CoursePage instance;
    private final SelenideElement addCourseButton = $(byText(JOIN_ON_COURSE_TEXT));

    public static CoursePage returnPageObject() {
        if (instance == null) {
            instance = new CoursePage();
        }
        return instance;
    }

    public void checkAddCourseButton() {
        waitVisible(addCourseButton);
        addCourseButton.shouldBe(visible);
    }
}
