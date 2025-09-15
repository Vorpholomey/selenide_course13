import constants.Constants;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Generators;

import static com.codeborne.selenide.Selenide.sleep;
import static constants.TestDataConstants.*;


@Slf4j
public class CollectionTest extends TestBase {


    @Test
 //   @Disabled
    @DisplayName("Проверка отображения кнопки \"Войти\"")
    public void checkLoginButtonByLoginPageTest() {
        log.info(displayName);
        assertSteps.checkLoginButton();
    }

    @Test
 //   @Disabled
    @DisplayName("Проверка фильтрации и навигации по курсам")
    public void joinTheFreeCourseTest() {
        log.info(displayName);
        steps.clickCheckboxFree();
        sleep(2000);
        steps.clickSearchButton();
        steps.clickPythonButton();
        assertSteps.checkJoinTheCourseButton();
    }

    @Test
  //  @Disabled
    @DisplayName("Тестирование поля поиска")
    public void searchInputFieldTest() {
        log.info(displayName);
        steps.clickSearchButton();
        steps.inputTextBySearchField(SEARCH_DATA_JAVA);
        steps.clickSearchButton();
        assertSteps.checkWordInSearch(SEARCH_DATA_JAVA);
    }

    @Test
 //   @Disabled
    @DisplayName("Тестирование авторизации")
    public void authorizationTest() {
        log.info(displayName);
        steps.clickEnterButton();
        sleep(1000);//без слипа не работает
        steps.authorizeUser(Constants.LOGIN, Constants.PASSWORD);
        assertSteps.checkAvatarImage();
    }

    @Test
 //   @Disabled
    @DisplayName("Проверка неработающих ссылок (404)")
    public void unworkedLinkTest() {
        log.info(displayName);
        Assertions.assertTrue(steps.checkLinksCorrected());
    }

    @Test
 //   @Disabled
    @DisplayName("Тестирование формы авторизации с некорректными данными")
    public void invalidAuthorizationTest() {
        log.info(displayName);
        steps.clickEnterButton();
        sleep(2000);
        steps.authorizeUser(Generators.generatorLogin(), Generators.generatorPassword());
        assertSteps.checkErrorMessage();
    }

    @Test
  //  @Disabled
    @DisplayName("Проверка стилей элемента")
    public void styleElementTest() {
        log.info(displayName);
        assertSteps.checkButtonStyle();
    }

    @Test
   // @Disabled
    @DisplayName("Проверка видимости элемента после действия")
    public void visibleElementTest() {
        log.info(displayName);
        assertSteps.checkVisibleSkolkovoLabelElement();
    }

    @Test
//    @Disabled
    @DisplayName("Проверка всплывающего окна после изменения профиля")
    public void changeProfileDataTest() {
        log.info(displayName);
        steps.clickEnterButton();
        sleep(1000);//без слипа не работает
        steps.authorizeUser(Constants.LOGIN, Constants.PASSWORD);
        steps.changeBiographyByProfile();

    }

}
