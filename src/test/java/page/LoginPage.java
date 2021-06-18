package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement notification = $(".notification__content");

    public void login(DataHelper.AuthInfo info) {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        loginField.setValue(info.getLogin());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }

    public void validLogin(DataHelper.AuthInfo info) {
        login(info);
    }


    public void invalidLogin(DataHelper.AuthInfo info) {
        login(info);
        notification.shouldBe(Condition.visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    public void shouldButtonContinue() {
        $(Selectors.byText("Продолжить")).shouldBe(Condition.visible);
    }


    public void cleanLoginFields() {
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }
}
