package utils;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import static com.codeborne.selenide.Selenide.sleep;
import static constants.Constants.DELAY_FOR_WAIT_ELEMENTS;
import static constants.Constants.MAX_RETRIES;

@Slf4j
public class WaitVisibleElement {
    public static SelenideElement waitVisible(SelenideElement element) {
        int retry = 0;
        int maxRetries = MAX_RETRIES;
        while (retry < maxRetries) {
            try {
                if (element.exists()) {
                    return element;
                }
            } catch (Exception e) {
                log.info("Проверка видимости элемента не успешна {}", e.getMessage());
            }

            log.info("Элемент пока не найден. Попытка {}", retry);
            retry++;
            sleep(DELAY_FOR_WAIT_ELEMENTS);
        }
        return null;
    }
}
