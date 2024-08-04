package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import ui.gmail.GmailHomePage;

import java.time.Duration;

public class Login implements Task {
    private final String email;
    private final String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Performable as(String email, String password) {
        return Instrumented.instanceOf(Login.class).withProperties(email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(email).into(GmailHomePage.email),
                Click.on(GmailHomePage.nextLoginBtn),
                Enter.theValue(password).into(GmailHomePage.password.waitingForNoMoreThan(Duration.ofSeconds(10))),
                Click.on(GmailHomePage.nextPasswordBtn)
        );
    }
}
