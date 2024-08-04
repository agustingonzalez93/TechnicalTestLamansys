package tests.web;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import questions.PriceOfProduct;
import tasks.SearchFirstItem;
import tasks.SwitchToNewTab;
import ui.ebay.EbayHomePage;
import ui.ebay.EbayItmPage;
import utilities.DataInfo;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class Ebay {
    @Managed
    WebDriver webDriver;
    Actor agus = Actor.named("Agus");

    @Before
    public void setTheStage() {
        agus.can(BrowseTheWeb.with(webDriver));
        agus.attemptsTo(
                Open.browserOn().the(EbayHomePage.class));
    }

    @After
    public void afterTest() throws InterruptedException {
        Thread.sleep(3000);
    }

    @Test
    public void showPriceOfFirstElectricGuitar() {
        givenThat(agus).attemptsTo(
                SearchFirstItem.withProductName(DataInfo.getProperty("electricGuitar"))
        );

        when(agus).attemptsTo(
                SwitchToNewTab.toViewPrice(webDriver)
        );

        then(agus).should(
                seeThat(TheTarget.textOf(EbayItmPage.productPrice),
                        equalTo(PriceOfProduct.displayed().answeredBy(agus)))
        );

        String price = agus.asksFor(PriceOfProduct.displayed());
        System.out.println("El precio de la guitarra es: " + price);
    }
}
