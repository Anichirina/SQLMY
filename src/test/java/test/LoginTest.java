package test;

import data.DataHelper;
import data.SQLData;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @AfterAll
    static void clear() {
        SQLData.cleanCodes();
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void tidyUp() {
        SQLData.cleanCodes();
    }

    @Test
    void shouldValidLogin() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = SQLData.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        $("[data-test-id='dashboard']").shouldBe(visible)
                .shouldHave(exactText("Личный кабинет"));
    }


    @Test
    void shouldInvalidLogin() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidPass();
        loginPage.invalidLogin(authInfo);
    }

    @Test
    void shouldBeBlocked() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidPass();
        loginPage.login(authInfo);
        loginPage.login(authInfo);
        loginPage.isBlocked(authInfo);
    }
}
