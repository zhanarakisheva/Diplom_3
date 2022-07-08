package diplom.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage {

    @FindBy(css = "main form input")
    public SelenideElement emailInput;

    @FindBy(css = "main form button")
    public SelenideElement submitButton;

    @FindBy(css = "main div div p a")
    public SelenideElement logInLink;

}
