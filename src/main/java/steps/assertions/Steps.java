package steps.assertions;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import pages.CatalogPage;
import pages.LoginPage;
import pages.ProfileSettingsPage;
import pages.SearchPage;

@Slf4j
public class Steps {
    CatalogPage catalogPage = CatalogPage.returnPageObject();
    SearchPage searchPage = SearchPage.returnPageObject();

    @Step("Кликнуть на чекбокс Бесплатные")
    public void clickCheckboxFree() {
        catalogPage.clickFreeCheckboxButton();
    }

    @Step("Кликнуть на кнопку Искать")
    public void clickSearchButton() {
        catalogPage.clickSearchButton();
    }

    @Step("Кликнуть на найденный курс Поколение Python")
    public void clickPythonButton() {
        catalogPage.clickSearchedCourse();
    }

    @Step("Ввести в поле поиска слово Java")
    public void inputTextBySearchField(String searchText) {
        searchPage.inputSearchText(searchText);
    }

    @Step("Кликнуть на кнопку Войти")
    public void clickEnterButton() {
        catalogPage.clickEnterButton();
    }

    @Step("Авторизоваться под пользователем")
    public void authorizeUser(String login, String password) {
        LoginPage loginPage = catalogPage.clickEnterButton();
        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Проверить что все ссылки рабочие")
    public Boolean checkLinksCorrected() {
        return catalogPage.checkUnworkedLinks();
    }

    @Step("Изменить биографию в настройках пользователя")
    public void changeBiographyByProfile() {
        catalogPage.clickAvatarImage();
        catalogPage.clickSettingsButton();
        ProfileSettingsPage profileSettingsPage = ProfileSettingsPage.returnPageObject();
        profileSettingsPage.fillBiographyField();
        profileSettingsPage.clickSaveButton();
    }
}
