package tests.web;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import questions.EmailAccountInformation;
import questions.LoginInformation;
import tasks.Login;
import ui.gmail.GmailHomePage;
import ui.gmail.SessionLoggedinPage;
import utilities.DataInfo;

import java.time.Duration;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class Gmail {
    @Managed
    WebDriver webDriver;
    Actor agus = Actor.named("Agus");

    @Before
    public void setTheStage() {
        agus.can(BrowseTheWeb.with(webDriver));
        agus.attemptsTo(
                Open.browserOn().the(GmailHomePage.class));
    }

    @After
    public void afterTest() throws InterruptedException {
        Thread.sleep(3000);
    }

    @Test
    public void validateLoginSuccessful() {
        givenThat(agus).has(
                Login.as(DataInfo.getProperty("email"), DataInfo.getProperty("password"))
        );

        when(agus).attemptsTo(
                Click.on(SessionLoggedinPage.userProfileIcon.waitingForNoMoreThan(Duration.ofSeconds(30)))
        );

        then(agus).should(
                //seeThat(the(SessionLoggedinPage.userProfileIcon), containsText(EmailAccountInformation.accountUserName().answeredBy(agus)))
                seeThat(the(SessionLoggedinPage.userProfileIcon), isCurrentlyVisible()),
                seeThat(TheTarget.textOf(SessionLoggedinPage.userProfileIcon),
                        equalTo(EmailAccountInformation.accountUserName().answeredBy(agus)))
        );
    }

    @Test
    public void validateLoginUnsuccessful() {
        when(agus).attemptsTo(
                Login.as(DataInfo.getProperty("email"), DataInfo.getProperty("incorrectPassword"))
        );

        then(agus).should(
                seeThat(TheTarget.textOf(GmailHomePage.incorrectPasswordField),
                        equalTo(LoginInformation.invalidPasswordMessage().answeredBy(agus)))
        );
    }
}
