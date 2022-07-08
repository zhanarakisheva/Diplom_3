package diplom.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage {

    @FindBy(css = "form fieldset:nth-of-type(1)  input")
    public SelenideElement nameInput;

    @FindBy(css = "form fieldset:nth-of-type(2) input")
    public SelenideElement emailInput;

    @FindBy(css = "form fieldset:nth-of-type(3) input")
    public SelenideElement passwordInput;

    @FindBy(css = "form button")
    public SelenideElement submitButton;

    @FindBy(css = "p.input__error")
    public SelenideElement incorrectPasswordError;

    @FindBy(css = "main div div p a")
    public SelenideElement logInLink;

}
