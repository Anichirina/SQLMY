package test;

import data.DataHelper;
import data.SQLData;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import page.VerificationPage;

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


    @Test
    void shouldValidLogin() {

        val loginPage = new LoginPage();
        val dashboardPage = new DashboardPage();
        val authInfo = DataHelper.getAuthInfo();
        loginPage.login(authInfo);
        val verificationCode = SQLData.getVerificationCode(authInfo.getLogin());
        VerificationPage verificationPage;
        verificationPage.validVerify(verificationCode);
        dashboardPage.shouldVisilePersonAccounte();
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
        loginPage.shouldButtonContinue();
        ;
    }

    @Test
    void shouldV() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidPass();
        loginPage.login(authInfo);

        loginPage.shouldButtonContinue();
        ;
    }
}
