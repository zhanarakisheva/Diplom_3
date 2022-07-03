package diplom.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(name = "name")
    public SelenideElement emailInput;

    @FindBy(name = "Пароль")
    public SelenideElement passwordInput;

    @FindBy(css = "form button")
    public SelenideElement submitButton;

}
