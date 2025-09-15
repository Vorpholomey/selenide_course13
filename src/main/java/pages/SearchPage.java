package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {
    private static SearchPage instance;
    private final ElementsCollection collectionCourses = $$(".course-card__title");
    private final SelenideElement searchInput = $(".search-form__input");

    public static SearchPage returnPageObject() {
        if (instance == null) {
            instance = new SearchPage();
        }
        return instance;
    }

    public void inputSearchText(String searchText) {
        searchInput.sendKeys(searchText);
    }

    public void checkSearchResult(String searchResult) {
        collectionCourses.shouldBe(CollectionCondition.anyMatch("Хотя бы один курс содержит Java",
                element -> element.getText().contains(searchResult)
        ));
    }
}
