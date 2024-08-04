package ui.gmail;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("https://gmail.com/")
public class GmailHomePage extends PageObject {
    public static Target email = Target.the("Email").located(By.id("identifierId"));
    public static Target password = Target.the("Password").located(By.name("Passwd"));
    public static Target nextLoginBtn = Target.the("NETX_BTN").locatedBy("//*[@id=\"identifierNext\"]/div/button/span");
    public static Target nextPasswordBtn = Target.the("NETX_BTN").locatedBy("//*[@id=\"passwordNext\"]/div/button/span");
    public static Target incorrectPasswordField = Target.the("INCORRECT_PASSWORD_FIELD").locatedBy("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div/div/div/form/span/section[2]/div/div/div[1]/div[2]/div[2]/span");
}
