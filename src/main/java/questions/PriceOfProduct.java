package questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.TextContent;
import ui.ebay.EbayItmPage;

public class PriceOfProduct {
    public static Question<String> displayed() {
        return actor -> TextContent.of(EbayItmPage.productPrice).answeredBy(actor);
    }
}
