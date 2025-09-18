package pages;

import com.codeborne.selenide.*;
import lombok.extern.slf4j.*;

import java.net.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constants.*;
import static constants.TestDataConstants.*;
import static utils.WaitVisibleElement.*;

@Slf4j
public class CatalogPage {
    private static CatalogPage instance;

    private final SelenideElement loginButton = $(".navbar__auth_login");
    private final SelenideElement freeButton = $(byText(FREE_CHECKBOX_TEXT));
    private final SelenideElement searchCourseByText = $(byText(SEARCH_DATA_PATH));
    private final SelenideElement searchButton = $(byText(FIND_BUTTON_TEXT));
    private final SelenideElement enterButton = $(byText(ENTER_BUTTON_TEXT));
    private final SelenideElement avatarImage = $(".navbar__profile-img");
    private final ElementsCollection links = $$x("//*[@href]");
    private final SelenideElement skolkovoLabel = $(".sk-link.page-footer__col");
    private final SelenideElement settingsButton = $(byText(PROPERTIES));

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
        waitVisible(freeButton);
        freeButton.shouldBe(visible).click();
        sleep(CONNECTION_TIMEOUT);
    }

    public void clickSearchedCourse() {
        waitVisible(searchCourseByText);
        searchCourseByText.shouldBe(visible).click();
        sleep(CONNECTION_TIMEOUT);
    }

    public void clickSearchButton() {
        waitVisible(searchButton);
        searchButton.shouldBe(visible).click();
        sleep(CONNECTION_TIMEOUT);
    }

    void clickLoginButton() {
        waitVisible(enterButton);
        enterButton.shouldBe(visible).click();
    }

    public LoginPage clickEnterButton() {
        clickLoginButton();
        sleep(CONNECTION_TIMEOUT);
        return LoginPage.returnPageObject();
    }

    public void checkAvatarImage() {
        waitVisible(avatarImage);
    }

    public boolean checkUnworkedLinks() {
        boolean isAllLinksOk = true;
        int errorsCount = 0;
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
                            errorsCount = errorsCount + checkWorkedLinks(responseCode, url);
                            connection.disconnect();
                        } catch (Exception ex) {
                            log.error("Ошибка проверки URL: {} - {}", url, ex.getMessage());
                            errorsCount++;
                        }
                    }
                }
            } catch (Exception e) {
                log.error("Элемент недоступен: {}", e.getMessage());
                errorsCount++;
            }
        }
        if (errorsCount > 0) {
            isAllLinksOk = false;
            log.info("Колличество не рабочих ссылок равно {}", errorsCount);
        }
        return isAllLinksOk;
    }

    private Integer checkWorkedLinks(int responseCode, String url) {
        if (responseCode == RESPONSE_CODE_200) {
            log.info("✓ Ссылка рабочая: {}", url);
            return 0;
        } else {
            log.error("✗ Ошибка ссылки: {} - код: {}", url, responseCode);
            return 1;
        }
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
        waitVisible(avatarImage);
        avatarImage.shouldBe(visible).click();
    }

    public void clickSettingsButton() {
        waitVisible(settingsButton);
        settingsButton.shouldBe(visible).click();
        ProfileSettingsPage.returnPageObject();
    }
}

