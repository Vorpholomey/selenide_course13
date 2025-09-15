package steps.assertions;

import lombok.extern.slf4j.Slf4j;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CatalogPage;
import pages.CoursePage;
import pages.LoginPage;
import pages.SearchPage;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static constants.Constants.*;

@Slf4j
public class AssertSteps {

    CatalogPage catalogPage = CatalogPage.returnPageObject();
    CoursePage coursePage = CoursePage.returnPageObject();
    SearchPage searchPage = SearchPage.returnPageObject();
    LoginPage loginPage = LoginPage.returnPageObject();

    @Step("Проверить что кнопка \"Войти\" присутствует на странице")
    public void checkLoginButton() {
        catalogPage.checkLoginButton();
    }

    @Step("Проверить что кнопка Поступить на курс  присутствует на странице")
    public void checkJoinTheCourseButton() {
        goToSecondTab();
        coursePage.checkAddCourseButton();
    }

    @Step("Перейти на другую вкладку")
    private void goToSecondTab() {
        WebDriver driver = getWebDriver();
        String handle = driver.getWindowHandles().toArray()[1]
                .toString();
        driver.switchTo().window(handle);
    }

    @Step("Проверить что в списках находится слово для поиска")
    public void checkWordInSearch(String word) {
        searchPage.checkSearchResult(word);
    }

    @Step("Проверить что иконка аватара пользователя видна")
    public void checkAvatarImage() {
        catalogPage.checkAvatarImage();
    }

    @Step("Проверить что появляется ошибка о авторизации")
    public void checkErrorMessage() {
        loginPage.errorMessageIsVisible();
    }

    @Step("Проверить что стили кнопки совпадают")
    public void checkButtonStyle() {
        log.info(catalogPage.getButtonColour());
        assert catalogPage.getButtonColour().equals(COLOUR_BUTTON_STYLE);
        log.info(catalogPage.getButtonPadding());
        assert catalogPage.getButtonPadding().equals(PADDING_BUTTON_STYLE);
        log.info(catalogPage.getButtonFontSize());
        assert catalogPage.getButtonFontSize().equals(FONT_SIZE_BUTTON_STYLE);
    }

    @Step("Проверить видимость элемента после прокрутки")
    public void checkVisibleSkolkovoLabelElement() {
        catalogPage.scrollToElement();
        sleep(CONNECTION_TIMEOUT);
        catalogPage.checkVisibleSkolkovoLabel();
    }
}
