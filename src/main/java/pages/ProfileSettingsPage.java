package pages;

import com.codeborne.selenide.*;
import lombok.extern.slf4j.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constants.*;
import static constants.TestDataConstants.*;
import static utils.WaitVisibleElement.*;

@Slf4j
public class ProfileSettingsPage {
    private static ProfileSettingsPage instance;

    private final SelenideElement biographyField = $(byId("short_bio"));
    private final SelenideElement saveButton = $(byText(SAVE_CHANGES));

    public static ProfileSettingsPage returnPageObject() {
        if (instance == null) {
            instance = new ProfileSettingsPage();
        }
        return instance;
    }

    public void fillBiographyField() {
        biographyField.setValue(BIOGRAPHY);
    }

    public void clickSaveButton() {
        waitVisible(saveButton);
        saveButton.click();
    }
}
