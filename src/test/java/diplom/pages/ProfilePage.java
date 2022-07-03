package diplom.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage {

    @FindBy(css = "header nav ul li:first-child a")
    public SelenideElement constructorNavLink;

    @FindBy(css = "header nav ul li:last-child a")
    public SelenideElement ordersFeedNavLink;

    @FindBy(css = "header nav div a")
    public SelenideElement logoNavLink;

    @FindBy(css = "header nav > a")
    public SelenideElement profileNavLink;

    @FindBy(css = "main div nav ul li:last-child button")
    public SelenideElement logOutButton;

}
