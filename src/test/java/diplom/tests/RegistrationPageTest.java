package diplom.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import diplom.pages.HomePage;
import diplom.pages.LoginPage;
import diplom.pages.RegistrationPage;
import diplom.utils.ThrowAwayUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.Assert.*;

public class RegistrationPageTest {

    private RegistrationPage page;

    @Before
    public void setUp() throws Exception {
        page = Selenide.open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
    }

    @After
    public void tearDown() throws Exception {
        ThrowAwayUser.logout();
    }

    @Test
    public void registerTest() {
        String name = UUID.randomUUID().toString();
        String email = name + "@example.com";
        String password = UUID.randomUUID().toString();

        page.nameInput.shouldBe(visible).setValue(name);
        page.emailInput.shouldBe(visible).setValue(email);
        page.passwordInput.shouldBe(visible).setValue(password);
        page.submitButton.shouldBe(visible).click();

        Selenide.Wait()
                .until(driver -> !driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/register"));

        LoginPage loginPage = Selenide.open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.emailInput.shouldBe(visible).setValue(email);
        loginPage.passwordInput.shouldBe(visible).setValue(password);
        loginPage.submitButton.shouldBe(visible).click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/"));
        HomePage homePage = Selenide.page(HomePage.class);

        homePage.logInLink.shouldBe(visible).shouldHave(text("Оформить заказ"));
    }

    @Test
    public void incorrectPasswordTest() {
        page.passwordInput.shouldBe(visible).setValue("short");
        page.submitButton.shouldBe(visible).click();
        page.incorrectPasswordError.shouldBe(visible);
    }
}
