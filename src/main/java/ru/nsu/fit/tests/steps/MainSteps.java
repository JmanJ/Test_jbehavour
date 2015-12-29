package ru.nsu.fit.tests.steps;

import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.testng.Assert;
import ru.nsu.fit.tests.services.browser.Browser;
import ru.nsu.fit.tests.services.browser.BrowserService;
import ru.nsu.fit.tests.services.browser.MainPageService;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.model.SeverityLevel;


public class MainSteps extends Steps {


    @Given("Start page of Online Calculator in browser")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void openCalculator() {
        //browser.openPage("http://testmethods.tmweb.ru/");
        press_reset();
    }

    @When("i type $operation of $expr1 and $expr2 using UI keys")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature", "Subtraction", "Addition", "Multiplication", "Division"})
    public void userTypesExpressionUsingUI(String operation, String expr1, String expr2) {
        MyContext.type_exp(expr1);
        MyContext.type_exp(define_operation(operation));
        MyContext.type_exp(expr2);
    }

    @When("i type $operation of $expr1 and $expr2 using keyboard")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature", "Subtraction", "Addition", "Multiplication", "Division"})
    public void userTypesExpressionUsingKeyboard(String operation, String expr1, String expr2) {
        MainPageService.browser.insert_value(expr1 + define_operation(operation) + expr2);
    }

    private String define_operation(String operation){
        if (operation.equals("sum")){
            return "+";
        }
        if (operation.equals("div")){
            return "/";
        }
        if (operation.equals("sub")){
            return "-";
        }
        if (operation.equals("mul")){
            return "*";
        }
        throw new IllegalArgumentException("Unknown operation: " + operation);
    }

    @When("i calc expression $expr using UI keys")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature", "Subtraction", "Addition", "Multiplication", "Division"})
    public void calc_exp(String expr){
        MyContext.type_exp(expr);
    }

    @When("i press result_button")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature"})
    public void press_result() {
        MainPageService.browser.click(MainPageService.equalElement);
    }

    @When("i click reset")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature"})
    public void press_reset() {
        MainPageService.browser.click(MainPageService.clearElement);
    }

    @Then("i get in result $result")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void checkResult(long result) {
        Assert.assertEquals(Long.parseLong(MainPageService.browser.get_value()), result);
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
