package diplom.tests;

import com.codeborne.selenide.Selenide;
import diplom.pages.HomePage;
import diplom.pages.LoginPage;
import diplom.pages.RegistrationPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HomePageAuthenticatedTest {

    private HomePage page;

    @Before
    public void setUp() throws Exception {
        LoginPage loginPage = Selenide.open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.emailInput.setValue("harry.styles.1221@example.com");
        loginPage.passwordInput.setValue("123456");
        loginPage.submitButton.click();
        Selenide.Wait()
                .until(driver -> !driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));

        page = Selenide.open("https://stellarburgers.nomoreparties.site/register", HomePage.class);
    }

    @After
    public void tearDown() throws Exception {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void profileLinkTest() {
        page.profileNavLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/account/profile"));
    }

    @Test
    public void tabsTest() {
        Selenide.Wait().until(driver ->
                page.tabs.get(0).getAttribute("class").contains("current")
                        && !page.tabs.get(1).getAttribute("class").contains("current")
                        && !page.tabs.get(2).getAttribute("class").contains("current"));

        page.tabs.get(1).click();

        Selenide.Wait().until(driver ->
                !page.tabs.get(0).getAttribute("class").contains("current")
                        && page.tabs.get(1).getAttribute("class").contains("current")
                        && !page.tabs.get(2).getAttribute("class").contains("current"));

        page.tabs.get(2).click();

        Selenide.Wait().until(driver ->
                !page.tabs.get(0).getAttribute("class").contains("current")
                        && !page.tabs.get(1).getAttribute("class").contains("current")
                        && page.tabs.get(2).getAttribute("class").contains("current"));

        page.tabs.get(0).click();

        Selenide.Wait().until(driver ->
                page.tabs.get(0).getAttribute("class").contains("current")
                        && !page.tabs.get(1).getAttribute("class").contains("current")
                        && !page.tabs.get(2).getAttribute("class").contains("current"));
    }
}
