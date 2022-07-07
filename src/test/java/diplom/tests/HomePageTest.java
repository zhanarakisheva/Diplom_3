package diplom.tests;

import com.codeborne.selenide.Selenide;
import diplom.pages.HomePage;
import diplom.utils.ThrowAwayUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HomePageTest {

    private HomePage page;

    @Before
    public void setUp() throws Exception {
        ThrowAwayUser.authenticate();
        page = Selenide.open("https://stellarburgers.nomoreparties.site", HomePage.class);
    }

    @After
    public void tearDown() throws Exception {
        ThrowAwayUser.logout();
    }

    @Test
    public void profileLinkTest() {
        page.profileNavLink.click();

        Selenide.Wait().until(driver -> driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/account/profile"));
    }

    @Test
    public void tabsTest0() {
        Selenide.Wait().until(driver ->
                page.tabs.get(0).getAttribute("class").contains("current")
                        && !page.tabs.get(1).getAttribute("class").contains("current")
                        && !page.tabs.get(2).getAttribute("class").contains("current"));

        page.tabs.get(1).click();
        page.tabs.get(0).click();

        Selenide.Wait().until(driver ->
                page.tabs.get(0).getAttribute("class").contains("current")
                        && !page.tabs.get(1).getAttribute("class").contains("current")
                        && !page.tabs.get(2).getAttribute("class").contains("current"));
    }

    @Test
    public void tabsTest1() {
        page.tabs.get(1).click();

        Selenide.Wait().until(driver ->
                !page.tabs.get(0).getAttribute("class").contains("current")
                        && page.tabs.get(1).getAttribute("class").contains("current")
                        && !page.tabs.get(2).getAttribute("class").contains("current"));
    }

    @Test
    public void tabsTest2() {
        page.tabs.get(2).click();

        Selenide.Wait().until(driver ->
                !page.tabs.get(0).getAttribute("class").contains("current")
                        && !page.tabs.get(1).getAttribute("class").contains("current")
                        && page.tabs.get(2).getAttribute("class").contains("current"));
    }
}
