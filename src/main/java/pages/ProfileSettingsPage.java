package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import utils.WaitVisibleElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static constants.TestDataConstants.*;

@Slf4j
public class ProfileSettingsPage {
    private static ProfileSettingsPage instance;

    private final SelenideElement biographyField = $(byId("short_bio"));
    private final SelenideElement saveButton = $(byText("Сохранить изменения"));

    public static ProfileSettingsPage returnPageObject() {
        if (instance == null) {
            instance = new ProfileSettingsPage();
        }
        return instance;
    }

    public void fillBiographyField() {
        log.info("Заполняем поле Биография");
        biographyField.setValue(BIOGRAPHY);
    }

    public void clickSaveButton() {
        log.info("Кликаем на кнопку Сохранить");
        WaitVisibleElement.waitVisible(saveButton);
        saveButton.click();
    }
}
