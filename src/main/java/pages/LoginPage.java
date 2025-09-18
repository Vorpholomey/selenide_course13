package pages;

import com.codeborne.selenide.*;
import lombok.extern.slf4j.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constants.*;
import static utils.WaitVisibleElement.*;

@Slf4j
public class LoginPage {
    private static LoginPage instance;

    private final SelenideElement loginField = $(byName("login"));
    private final SelenideElement passwordField = $(byName("password"));
    private final SelenideElement enterButton = $(".sign-form__btn.button_with-loader");
    private final SelenideElement errorMessage = $(byText(ERROR_LOGIN_PASS_MESSAGE));

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
        waitVisible(passwordField);
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
