package ru.nsu.fit.tests.steps;


import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import ru.nsu.fit.tests.services.browser.Browser;
import ru.nsu.fit.tests.services.browser.BrowserService;
import ru.nsu.fit.tests.services.browser.MainPageService;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;


public class SchoolboySteps extends Steps{

    private MyContext context;

    @Given("as schoolboy")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void openCalculator() {
        press_reset();
    }

    @When("i calc expression $expr using keyboard")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature", "Subtraction", "Addition", "Multiplication", "Division"})
    public void calc_exp(String expr){
        MainPageService.browser.insert_value(expr);
    }

    @When("i subs from the result $expr")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Subtraction"})
    public void subsFromTheResult(String expr) {
        MainPageService.browser.click(MainPageService.minusElement);
        MyContext.type_exp(expr);
        //int t = (int) context.variables.get("obj");
    }

    @When("i click reset")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature"})
    public void press_reset() {
        MainPageService.browser.click(MainPageService.clearElement);
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
