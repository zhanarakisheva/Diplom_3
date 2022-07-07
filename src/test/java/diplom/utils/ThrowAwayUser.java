package diplom.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import diplom.pages.LoginPage;
import diplom.pages.RegistrationPage;

import java.util.UUID;

import static com.codeborne.selenide.Condition.visible;

public class ThrowAwayUser {

    public String name;
    public String email;
    public String password;

    private ThrowAwayUser() {
        name = UUID.randomUUID().toString();
        email = name + "@email.com";
        password = UUID.randomUUID().toString();
    }

    public static ThrowAwayUser authenticate() {
        ThrowAwayUser user = new ThrowAwayUser();

        RegistrationPage registrationPage = Selenide.open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        registrationPage.nameInput.shouldBe(visible).setValue(user.name);
        registrationPage.emailInput.shouldBe(visible).setValue(user.email);
        registrationPage.passwordInput.shouldBe(visible).setValue(user.password);
        registrationPage.submitButton.shouldBe(visible).click();
        Selenide.Wait().until(driver -> !driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/register"));

        LoginPage loginPage = Selenide.open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.emailInput.shouldBe(visible).setValue(user.email);
        loginPage.passwordInput.shouldBe(visible).setValue(user.password);
        loginPage.submitButton.shouldBe(visible).click();
        Selenide.Wait().until(driver -> !driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        return user;
    }

    public static void logout() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

}
