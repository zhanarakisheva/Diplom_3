package diplom.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import diplom.pages.HomePage;
import diplom.pages.LoginPage;
import diplom.pages.RegistrationPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class RegistrationPageTest {

    private RegistrationPage page;

    @Before
    public void setUp() throws Exception {
        page = Selenide.open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
    }

    @Test
    public void registerTest() {
        String name = UUID.randomUUID().toString();
        String email = name + "@example.com";
        String password = UUID.randomUUID().toString();

        page.nameInput.setValue(name);
        page.emailInput.setValue(email);
        page.passwordInput.setValue(password);
        page.submitButton.click();

        Selenide.Wait()
                .until(driver -> !driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/register"));

        LoginPage loginPage = Selenide.open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.emailInput.setValue(email);
        loginPage.passwordInput.setValue(password);
        loginPage.submitButton.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/"));
        HomePage homePage = Selenide.page(HomePage.class);
        Assert.assertEquals("Оформить заказ", homePage.logInLink.text());
    }

    @Test
    public void incorrectPasswordTest() {
        page.passwordInput.setValue("short");
        page.submitButton.click();
        assertTrue(page.incorrectPasswordError.isDisplayed());
    }
}
