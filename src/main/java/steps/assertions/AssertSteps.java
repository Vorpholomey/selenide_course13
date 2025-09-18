package steps.assertions;

import io.qameta.allure.*;
import lombok.extern.slf4j.*;
import org.openqa.selenium.*;
import pages.*;

import static com.codeborne.selenide.WebDriverRunner.*;
import static constants.Constants.*;

@Slf4j
public class AssertSteps {

    CatalogPage catalogPage = CatalogPage.returnPageObject();
    CoursePage coursePage = CoursePage.returnPageObject();
    SearchPage searchPage = SearchPage.returnPageObject();
    LoginPage loginPage = LoginPage.returnPageObject();

    @Step("Проверить что кнопка \"Войти\" присутствует на странице")
    public void checkLoginButton() {
        log.info("Проверяем что кнопка Войти присутствует на странице");
        catalogPage.checkLoginButton();
    }

    @Step("Проверить что кнопка Поступить на курс присутствует на странице")
    public void checkJoinTheCourseButton() {
        goToSecondTab();
        log.info("Проверяем, что курс присутствует на странице");
        coursePage.checkAddCourseButton();
    }

    @Step("Перейти на другую вкладку")
    private void goToSecondTab() {
        log.info("Переходим на вторую вкладку");
        WebDriver driver = getWebDriver();
        String handle = driver.getWindowHandles().toArray()[1]
                .toString();
        driver.switchTo().window(handle);
    }

    @Step("Проверить что в списках находится слово для поиска")
    public void checkWordInSearch(String word) {
        log.info("Проверяем что в списках находится искомое слово {}", word);
        searchPage.checkSearchResult(word);
    }

    @Step("Проверить что иконка аватара пользователя видна")
    public void checkAvatarImage() {
        log.info("Проверяем что картинка аватара пользователя видна");
        catalogPage.checkAvatarImage();
    }

    @Step("Проверить что появляется ошибка о авторизации")
    public void checkErrorMessage() {
        log.info("Проверяем что появляется ошибка авторизации");
        loginPage.errorMessageIsVisible();
    }

    @Step("Проверить что стили кнопки совпадают")
    public void checkButtonStyle() {
        log.info("Проверяем цвет кнопки {}", catalogPage.getButtonColour());
        assert catalogPage.getButtonColour().equals(COLOUR_BUTTON_STYLE);
        log.info("Проверяем расположение кнопки {}", catalogPage.getButtonPadding());
        assert catalogPage.getButtonPadding().equals(PADDING_BUTTON_STYLE);
        log.info("Проверяем размер шрифта у кнопки {}", catalogPage.getButtonFontSize());
        assert catalogPage.getButtonFontSize().equals(FONT_SIZE_BUTTON_STYLE);
    }

    @Step("Проверить видимость элемента после прокрутки")
    public void checkVisibleSkolkovoLabelElement() {
        log.info("Прокручиваем страницу до искомого элемента");
        catalogPage.scrollToElement();
        log.info("Проверяем наличие иконки Сколково");
        catalogPage.checkVisibleSkolkovoLabel();
    }
}
