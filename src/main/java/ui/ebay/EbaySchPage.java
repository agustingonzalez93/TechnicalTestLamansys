package ui.ebay;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class EbaySchPage {
    public static Target firstItem = Target.the("First Item").located(By.xpath("//ul[contains(@class, 'srp-results')]//li[contains(@class, 's-item')][1]//div[@class='s-item__image-section']//img"));
}
