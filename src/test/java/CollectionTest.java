import constants.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import utils.*;

import static constants.TestDataConstants.*;

@Slf4j
public class CollectionTest extends TestBase {


    @Test
    @DisplayName("Проверка отображения кнопки \"Войти\"")
    public void checkLoginButtonByLoginPageTest() {
        log.info(displayName);
        assertSteps.checkLoginButton();
    }

    @Test
    @DisplayName("Проверка фильтрации и навигации по курсам")
    public void joinTheFreeCourseTest() {
        log.info(displayName);
        steps.clickCheckboxFree();
        steps.clickSearchButton();
        steps.clickPythonButton();
        assertSteps.checkJoinTheCourseButton();
    }

    @Test
    @DisplayName("Тестирование поля поиска")
    public void searchInputFieldTest() {
        log.info(displayName);
        steps.clickSearchButton();
        steps.inputTextBySearchField(SEARCH_DATA_JAVA);
        steps.clickSearchButton();
        assertSteps.checkWordInSearch(SEARCH_DATA_JAVA);
    }

    @Test
    @DisplayName("Тестирование авторизации")
    public void authorizationTest() {
        log.info(displayName);
        steps.authorizeUser(Constants.LOGIN, Constants.PASSWORD);
        assertSteps.checkAvatarImage();
    }

    @Test
    @DisplayName("Проверка неработающих ссылок (404)")
    public void unworkedLinkTest() {
        log.info(displayName);
        Assertions.assertTrue(steps.checkLinksCorrected());
    }

    @Test
    @DisplayName("Тестирование формы авторизации с некорректными данными")
    public void invalidAuthorizationTest() {
        log.info(displayName);
        steps.authorizeUser(Generators.generatorLogin(), Generators.generatorPassword());
        assertSteps.checkErrorMessage();
    }

    @Test
    @DisplayName("Проверка стилей элемента")
    public void styleElementTest() {
        log.info(displayName);
        assertSteps.checkButtonStyle();
    }

    @Test
    @DisplayName("Проверка видимости элемента после действия")
    public void visibleElementTest() {
        log.info(displayName);
        assertSteps.checkVisibleSkolkovoLabelElement();
    }

    @Test
    @DisplayName("Проверка всплывающего окна после изменения профиля")
    public void changeProfileDataTest() {
        log.info(displayName);
        steps.authorizeUser(Constants.LOGIN, Constants.PASSWORD);
        steps.changeBiographyByProfile();
    }
}
