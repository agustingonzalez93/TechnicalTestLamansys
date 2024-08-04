package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.openqa.selenium.WebDriver;

public class SwitchToNewTab implements Task {
    private final WebDriver driver;

    public SwitchToNewTab(WebDriver driver) {
        this.driver = driver;
    }

    public static Performable toViewPrice(WebDriver driver) {
        return Instrumented.instanceOf(SwitchToNewTab.class).withProperties(driver);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String originalHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
}
