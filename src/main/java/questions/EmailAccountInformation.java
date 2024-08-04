package questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.TextContent;
import ui.gmail.SessionLoggedinPage;

public class EmailAccountInformation {
    public static Question<String> accountUserName() {
        return actor -> TextContent.of(SessionLoggedinPage.userProfileIcon).answeredBy(actor);
    }
}
