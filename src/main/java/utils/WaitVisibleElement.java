package utils;

import com.codeborne.selenide.*;
import lombok.extern.slf4j.*;

import static com.codeborne.selenide.Selenide.*;
import static constants.Constants.*;

@Slf4j
public class WaitVisibleElement {
    public static SelenideElement waitVisible(SelenideElement element) {
        int retry = 0;
        while (retry < MAX_RETRIES) {
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
