package ru.nsu.fit.tests.steps;


import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import org.testng.Assert;
import ru.nsu.fit.tests.services.browser.Browser;
import ru.nsu.fit.tests.services.browser.BrowserService;
import ru.nsu.fit.tests.services.browser.MainPageService;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class ScientistSteps extends Steps {


    @Given("as scientist")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void openCalculator() {
        press_reset();
    }

    @When("i div from the result $expr")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Subtraction"})
    public void divFromTheResult(String expr) {
        MainPageService.browser.click(MainPageService.divElement);
        MyContext.type_exp(expr);
    }

    @When("i calc not standard expression $expr")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Subtraction"})
    public void subsFromTheResult(String expr) {
        MainPageService.browser.insert_value(expr);
    }

    @When("i click reset")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature"})
    public void press_reset() {
        MainPageService.browser.click(MainPageService.clearElement);
    }

    @Then("i get not standard in result $result")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void checkResult(String result) {
        Assert.assertEquals(MainPageService.browser.get_value(), result);
    }

    @BeforeScenario
    public void beforeScenario() {
        MainPageService.start_test();
    }

    @AfterScenario
    public void afterScenario() {
        MainPageService.close_browser();
    }
}
