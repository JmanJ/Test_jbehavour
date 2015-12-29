package ru.nsu.fit.tests.steps;


import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import ru.nsu.fit.tests.services.browser.Browser;
import ru.nsu.fit.tests.services.browser.BrowserService;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class ScientistSteps extends Steps {

    private Browser browser;

    @Given("as scientist")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void openCalculator() {
        browser.openPage("http://testmethods.tmweb.ru/");
    }

    @When("i div from the result $expr")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Subtraction"})
    public void divFromTheResult(String expr) {
        browser.click(browser.divElement);
        //type_exp
    }

    @When("i calc not standard expression $expr")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Subtraction"})
    public void subsFromTheResult(String expr) {
        browser.typeText(expr);
    }

    @BeforeScenario
    public void beforeScenario() {
        browser = BrowserService.openNewBrowser();
    }

    @AfterScenario
    public void afterScenario() {
        browser.close();
    }
}
