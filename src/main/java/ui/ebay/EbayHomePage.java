package ui.ebay;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("https://ebay.com/")
public class EbayHomePage extends PageObject {
    public static Target articleSearchBox = Target.the("article Search Box").located(By.id("gh-ac"));
    public static Target searchBtn = Target.the("Search Button").located(By.id("gh-btn"));

}
