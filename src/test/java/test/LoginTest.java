package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import data.DataHelper;
import data.SQLData;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    SQLData mySql = new SQLData();

    @AfterAll
    static void clear() {
        SQLData.cleanCodes();
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldValidLogin() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = SQLData.getVerificationCode(authInfo.getLogin());
        verificationPage.validVerify(verificationCode);
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
    void shouldVisibleAndClickTheButtonContinue() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidPass();
        loginPage.login(authInfo);
        loginPage.cleanLoginFields();
        loginPage.login(authInfo);
        loginPage.cleanLoginFields();
        loginPage.login(authInfo);
        $(Selectors.byText("Продолжить")).shouldBe(Condition.visible);
        ;
    }
}
