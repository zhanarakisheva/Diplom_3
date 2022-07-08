package diplom.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage {

    @FindBy(css = "header nav ul li:nth-child(1) a")
    public SelenideElement constructorNavLink;

    @FindBy(css = "header nav ul li:nth-child(2) a")
    public SelenideElement ordersFeedNavLink;

    @FindBy(css = "header nav div a")
    public SelenideElement logoNavLink;

    @FindBy(css = "header nav > a")
    public SelenideElement profileNavLink;

    @FindBy(css = "main section:nth-child(2) div button")
    public SelenideElement logInLink;

    @FindBy(css = "main section:first-child div:first-of-type div")
    public List<SelenideElement> tabs;

}
