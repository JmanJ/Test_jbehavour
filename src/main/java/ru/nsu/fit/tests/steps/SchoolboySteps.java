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


public class SchoolboySteps extends Steps{

    private Browser browser;
    private MyContext context;

    @Given("as schoolboy")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void openCalculator() {
        browser.openPage("http://testmethods.tmweb.ru/");
    }

    @When("i subs from the result $expr")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Subtraction"})
    public void subsFromTheResult(String expr) {
        browser.click(browser.minusElement);
        //type_exp
        //int t = (int) context.variables.get("obj");
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
