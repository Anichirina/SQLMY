package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible).shouldHave(text("Личный кабинет"));
    }

    public void shouldVisilePersonAccounte() {
        $("[data-test-id='dashboard']").shouldBe(visible)
                .shouldHave(exactText("Личный кабинет"));
    }
}
