package ui.ebay;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class EbayItmPage extends PageObject {
    public static Target productPrice = Target.the("product price").located(By.cssSelector("div.x-price-primary span.ux-textspans"));
}
