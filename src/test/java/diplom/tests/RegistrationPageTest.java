package diplom.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import diplom.pages.RegistrationPage;
import org.junit.After;
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

    @After
    public void tearDown() throws Exception {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void registerTest() {
        String name = UUID.randomUUID().toString();
        String email = name + "@example.com";

        page.nameInput.setValue(name);
        page.emailInput.setValue(email);
        page.passwordInput.setValue("longEnoughPassword");
        page.submitButton.click();

        Selenide.Wait()
                .until(driver -> !driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/register"));

        assertEquals("https://stellarburgers.nomoreparties.site/login", WebDriverRunner.getWebDriver().getCurrentUrl());
    }

    @Test
    public void incorrectPasswordTest() {
        page.passwordInput.setValue("short");
        page.submitButton.click();
        assertTrue(page.incorrectPasswordError.isDisplayed());
    }
}
