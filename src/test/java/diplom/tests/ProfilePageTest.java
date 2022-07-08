package diplom.tests;

import com.codeborne.selenide.Selenide;
import diplom.pages.HomePage;
import diplom.pages.ProfilePage;
import diplom.utils.ThrowAwayUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProfilePageTest {

    private ProfilePage page;

    @Before
    public void setUp() throws Exception {
        ThrowAwayUser.authenticate();

        HomePage homePage = Selenide.open("https://stellarburgers.nomoreparties.site", HomePage.class);
        homePage.profileNavLink.click();
        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/account/profile"));

        page = Selenide.page(ProfilePage.class);
    }

    @After
    public void tearDown() throws Exception {
        ThrowAwayUser.logout();
    }

    @Test
    public void constructorLinkTest() {
        page.constructorNavLink.click();

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
