package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import ui.ebay.EbayHomePage;
import ui.ebay.EbaySchPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class SearchFirstItem implements Task {
    private final String productName;

    public SearchFirstItem(String productName) {
        this.productName = productName;
    }

    public static Performable withProductName(String productName) {
        return Instrumented.instanceOf(SearchFirstItem.class).withProperties(productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(productName).into(EbayHomePage.articleSearchBox),
                Click.on(EbayHomePage.searchBtn),
                WaitUntil.the(EbaySchPage.firstItem, isVisible()).forNoMoreThan(15).seconds(),
                WaitUntil.the(EbaySchPage.firstItem, isClickable()).forNoMoreThan(15).seconds(),
                Click.on(EbaySchPage.firstItem).afterWaitingUntilEnabled()
        );
    }

}
