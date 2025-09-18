package steps.assertions;

import io.qameta.allure.*;
import lombok.extern.slf4j.*;
import pages.*;

@Slf4j
public class Steps {
    CatalogPage catalogPage = CatalogPage.returnPageObject();
    SearchPage searchPage = SearchPage.returnPageObject();
    ProfileSettingsPage profileSettingsPage = ProfileSettingsPage.returnPageObject();

    @Step("Кликнуть на чекбокс Бесплатные")
    public void clickCheckboxFree() {
        log.info("Кликаем на чек-бокс Бесплатные");
        catalogPage.clickFreeCheckboxButton();
    }

    @Step("Кликнуть на кнопку Искать")
    public void clickSearchButton() {
        log.info("Кликаем на кнопку Искать");
        catalogPage.clickSearchButton();
    }

    @Step("Кликнуть на найденный курс Поколение Python")
    public void clickPythonButton() {
        log.info("Кликаем на найденный курс");
        catalogPage.clickSearchedCourse();
    }

    @Step("Ввести в поле поиска слово Java")
    public void inputTextBySearchField(String searchText) {
        log.info("Вводим слово в строку поиска {}", searchText);
        searchPage.inputSearchText(searchText);
    }


    @Step("Авторизоваться под пользователем")
    public void authorizeUser(String login, String password) {
        log.info("Кликаем на кнопку Войти");
        catalogPage.clickEnterButton();
        LoginPage loginPage = catalogPage.clickEnterButton();
        log.info("Вводим логин");
        loginPage.enterLogin(login);
        log.info("Вводим пароль");
        loginPage.enterPassword(password);
        log.info("Кликаем на кнопку Войти");
        loginPage.clickLoginButton();
    }

    @Step("Проверить что все ссылки рабочие")
    public Boolean checkLinksCorrected() {
        log.info("Проверяем ссылки на работоспособность");
        return catalogPage.checkUnworkedLinks();
    }

    @Step("Изменить биографию в настройках пользователя")
    public void changeBiographyByProfile() {
        log.info("Кликаем на кнопку аватара");
        catalogPage.clickAvatarImage();
        log.info("Кликаем на кнопку настройки у пользователя");
        catalogPage.clickSettingsButton();
        log.info("Заполняем поле Биография");
        profileSettingsPage.fillBiographyField();
        log.info("Кликаем на кнопку Сохранить");
        profileSettingsPage.clickSaveButton();
    }
}
