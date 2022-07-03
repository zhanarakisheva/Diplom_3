package diplom.tests;

import com.codeborne.selenide.Selenide;
import diplom.pages.HomePage;
import diplom.pages.LoginPage;
import diplom.pages.ProfilePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProfilePageAuthenticatedTest {

    private ProfilePage page;

    @Before
    public void setUp() throws Exception {
        LoginPage loginPage = Selenide.open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.emailInput.setValue("harry.styles.1221@example.com");
        loginPage.passwordInput.setValue("123456");
        loginPage.submitButton.click();
        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/"));

        Selenide.page(HomePage.class).profileNavLink.click();
        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/account/profile"));

        page = Selenide.page(ProfilePage.class);
    }

    @After
    public void tearDown() throws Exception {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void constructorLinkTest() {
        page.constructorNavLink.click();

        Selenide.sleep(1000);

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    public void logoLinkTest() {
        page.logoNavLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    public void logOutTest() {
        page.logOutButton.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login"));
    }
}
