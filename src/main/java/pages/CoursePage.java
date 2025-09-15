package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static constants.Constants.JOIN_ON_COURSE_TEXT;

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
        addCourseButton.shouldBe(visible);
    }
}
