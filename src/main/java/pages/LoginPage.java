package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static utils.WaitVisibleElement.*;

public class LoginPage {
    private static LoginPage instance;

    private final SelenideElement loginField = $(byName("login"));
    private final SelenideElement passwordField = $(byName("password"));
    private final SelenideElement enterButton = $(".sign-form__btn.button_with-loader");
    private final SelenideElement errorMessage = $(byText("E-mail адрес и/или пароль не верны."));

    public static LoginPage returnPageObject() {
        if (instance == null) {
            instance = new LoginPage();
        }
        return instance;
    }

    public void enterLogin(String login) {
        waitVisible(loginField);
        loginField.setValue(login);
    }

    public void enterPassword(String password) {
        passwordField.shouldBe(visible);
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        waitVisible(enterButton);
        enterButton.click();
    }

    public void errorMessageIsVisible() {
        errorMessage.shouldBe(visible);
    }
}
