package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import java.net.HttpURLConnection;
import java.net.URL;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constants.*;
import static constants.TestDataConstants.SEARCH_DATA_PATH;
import static utils.WaitVisibleElement.*;

@Slf4j
public class CatalogPage {
    private static CatalogPage instance;

    private final SelenideElement loginButton = $(".navbar__auth_login");
    private final SelenideElement freeButton = $(byText(FREE_CHECKBOX_TEXT));
    private final SelenideElement searchByText = $(byText(SEARCH_DATA_PATH));
    private final SelenideElement searchButton = $(byText(FIND_BUTTON_TEXT));
    private final SelenideElement enterButton = $(byText(ENTER_BUTTON_TEXT));
    private final SelenideElement avatarImage = $(".navbar__profile-img");
    private final ElementsCollection links = $$x("//*[@href]");
    private final SelenideElement skolkovoLabel = $(".sk-link.page-footer__col");
    private final SelenideElement settingsButton = $(byText("Настройки"));

    public static CatalogPage returnPageObject() {
        if (instance == null) {
            instance = new CatalogPage();
        }
        return instance;
    }

    public void checkLoginButton() {
        loginButton.shouldBe(visible);
    }

    public void clickFreeCheckboxButton() {
        freeButton.shouldBe(visible);
        freeButton.click();
    }

    public void clickSearchedCourse() {
        log.info("Нажатие на найденный курс");
        waitVisible(searchByText);
        searchByText.click();
    }

    public void clickSearchButton() {
        log.info("Нажатие на кнопку поиска");
        waitVisible(searchButton);
        searchButton.click();
    }

    public LoginPage clickEnterButton() {
        waitVisible(enterButton);
        enterButton.shouldBe(clickable);
        enterButton.click();
        return LoginPage.returnPageObject();
    }

    public void checkAvatarImage() {
        waitVisible(avatarImage);
    }

    public boolean checkUnworkedLinks() {
        boolean isAllLinksOk = true;
        int valueOfFailLinks = 0;
        log.info("Найдено ссылок - {}", links.size());
        for (int i = 0; i < links.size(); i++) {
            try {
                SelenideElement element = $$("a[href]").get(i);
                if (element.is(clickable) && element.is(visible)) {
                    String url = element.getAttribute("href");
                    if (url != null && !url.isEmpty()) {
                        try {
                            URL link = new URL(url);
                            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
                            connection.setConnectTimeout(CONNECTION_TIMEOUT);
                            connection.setReadTimeout(CONNECTION_TIMEOUT);
                            connection.setRequestMethod("HEAD");
                            log.info("Проверка ссылки - {}", url);
                            int responseCode = connection.getResponseCode();
                            if (responseCode == RESPONSE_CODE_200) {
                                log.info("✓ Ссылка рабочая: {}", url);
                            } else {
                                log.error("✗ Ошибка ссылки: {} - код: {}", url, responseCode);
                                isAllLinksOk = false;
                                valueOfFailLinks++;
                            }
                            connection.disconnect();
                        } catch (Exception ex) {
                            log.error("Ошибка проверки URL: {} - {}", url, ex.getMessage());
                            isAllLinksOk = false;
                            valueOfFailLinks++;
                        }
                    }
                }
            } catch (Exception e) {
                log.error("Элемент недоступен: {}", e.getMessage());
                isAllLinksOk = false;
                valueOfFailLinks++;
            }
        }
        return isAllLinksOk;
    }

    public String getButtonColour() {
        return searchButton.getCssValue("color");
    }

    public String getButtonPadding() {
        return searchButton.getCssValue("padding");
    }

    public String getButtonFontSize() {
        return searchButton.getCssValue("font-size");
    }

    public void scrollToElement() {
        skolkovoLabel.scrollIntoView(true);
    }

    public void checkVisibleSkolkovoLabel() {
        waitVisible(skolkovoLabel);
        skolkovoLabel.shouldBe(visible);
    }

    public void clickAvatarImage() {
        log.info("Кликаем на кнопку аватара");
        waitVisible(avatarImage);
        avatarImage.shouldBe(visible);
        avatarImage.click();
    }

    public void clickSettingsButton() {
        log.info("Кликаем на кнопку настройки у пользователя");
        waitVisible(settingsButton);
        settingsButton.shouldBe(visible);
        settingsButton.click();
        ProfileSettingsPage.returnPageObject();
    }
}

