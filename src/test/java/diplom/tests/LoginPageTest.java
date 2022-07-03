package diplom.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import diplom.pages.ForgotPasswordPage;
import diplom.pages.HomePage;
import diplom.pages.LoginPage;
import diplom.pages.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class LoginPageTest {

    @After
    public void tearDown() throws Exception {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void fromHomeLoginButtonLoginTest() {
        HomePage page = Selenide.open("https://stellarburgers.nomoreparties.site", HomePage.class);
        page.logInLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        directLoginTest(Selenide.page(LoginPage.class));
    }

    @Test
    public void fromHomeProfileButtonLoginTest() {
        HomePage page = Selenide.open("https://stellarburgers.nomoreparties.site", HomePage.class);
        page.profileNavLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        directLoginTest(Selenide.page(LoginPage.class));
    }

    @Test
    public void fromRegistrationLoginLinkLoginTest() {
        RegistrationPage page = Selenide.open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        page.logInLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        directLoginTest(Selenide.page(LoginPage.class));
    }

    @Test
    public void fromForgotPasswordLoginLinkLoginTest() {
        ForgotPasswordPage page = Selenide.open("https://stellarburgers.nomoreparties.site/forgot-password", ForgotPasswordPage.class);
        page.logInLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        directLoginTest(Selenide.page(LoginPage.class));
    }

    public void directLoginTest(LoginPage page) {
        page.emailInput.setValue("harry.styles.1221@example.com");
        page.passwordInput.setValue("123456");
        page.submitButton.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/"));
    }

}
