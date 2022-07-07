package diplom.tests;

import com.codeborne.selenide.Selenide;
import diplom.pages.ForgotPasswordPage;
import diplom.pages.HomePage;
import diplom.pages.LoginPage;
import diplom.pages.RegistrationPage;
import diplom.utils.ThrowAwayUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginPageTest {

    private ThrowAwayUser user;

    @Before
    public void setUp() throws Exception {
        user = ThrowAwayUser.authenticate();
        ThrowAwayUser.logout();
    }

    @After
    public void tearDown() throws Exception {
        ThrowAwayUser.logout();
    }

    @Test
    public void fromHomeLoginButtonLoginTest() throws Exception {
        HomePage page = Selenide.open("https://stellarburgers.nomoreparties.site", HomePage.class);
        page.logInLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        directLoginTest(Selenide.page(LoginPage.class));
    }

    @Test
    public void fromHomeProfileButtonLoginTest() throws Exception {
        HomePage page = Selenide.open("https://stellarburgers.nomoreparties.site", HomePage.class);
        page.profileNavLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        directLoginTest(Selenide.page(LoginPage.class));
    }

    @Test
    public void fromRegistrationLoginLinkLoginTest() throws Exception {
        RegistrationPage page = Selenide.open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        page.logInLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        directLoginTest(Selenide.page(LoginPage.class));
    }

    @Test
    public void fromForgotPasswordLoginLinkLoginTest() throws Exception {
        ForgotPasswordPage page = Selenide.open("https://stellarburgers.nomoreparties.site/forgot-password", ForgotPasswordPage.class);
        page.logInLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        directLoginTest(Selenide.page(LoginPage.class));
    }

    public void directLoginTest(LoginPage page) throws Exception {
        page.emailInput.setValue(user.email);
        page.passwordInput.setValue(user.password);
        page.submitButton.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/"));
        HomePage homePage = Selenide.page(HomePage.class);
        Assert.assertEquals("Оформить заказ", homePage.logInLink.text());
    }

}
