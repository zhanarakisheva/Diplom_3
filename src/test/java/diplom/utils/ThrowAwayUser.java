package diplom.utils;

import com.codeborne.selenide.Selenide;
import diplom.pages.LoginPage;
import diplom.pages.RegistrationPage;

import java.util.UUID;

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
        registrationPage.nameInput.setValue(user.name);
        registrationPage.emailInput.setValue(user.email);
        registrationPage.passwordInput.setValue(user.password);
        registrationPage.submitButton.click();
        Selenide.Wait().until(driver -> !driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/register"));

        LoginPage loginPage = Selenide.open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.emailInput.setValue(user.email);
        loginPage.passwordInput.setValue(user.password);
        loginPage.submitButton.click();
        Selenide.Wait().until(driver -> !driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        return user;
    }

    public static void logout() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

}
