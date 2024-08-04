package questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.TextContent;
import ui.gmail.GmailHomePage;

public class LoginInformation {
    public static Question<String> invalidPasswordMessage() {
        return actor -> TextContent.of(GmailHomePage.incorrectPasswordField).answeredBy(actor);
    }
}
