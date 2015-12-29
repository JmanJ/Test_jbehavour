package ru.nsu.fit.tests.steps;

import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.testng.Assert;
import ru.nsu.fit.tests.services.browser.Browser;
import ru.nsu.fit.tests.services.browser.BrowserService;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.model.SeverityLevel;


public class MainSteps extends Steps {

    private Browser browser;

    @Given("Start page of Online Calculator in browser")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void openCalculator() {
        browser.openPage("http://testmethods.tmweb.ru/");
    }

    @When("i type $operation of $expr1 and $expr2 using UI keys")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature", "Subtraction", "Addition", "Multiplication", "Division"})
    public void userTypesExpressionUsingUI(String operation, String expr1, String expr2) {
        type_exp(expr1);
        type_exp(define_operation(operation));
        type_exp(expr2);
    }

    @When("i type $operation of $expr1 and $expr2 using keyboard")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature", "Subtraction", "Addition", "Multiplication", "Division"})
    public void userTypesExpressionUsingKeyboard(String operation, String expr1, String expr2) {
        browser.typeText(expr1 + define_operation(operation) + expr2);
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

    @When("i calc expression $expr")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature", "Subtraction", "Addition", "Multiplication", "Division"})
    public void type_exp(String expr){
        for (int i = 0; i < expr.length(); i++) {
            char symbol = expr.charAt(i);
            switch (symbol) {
                case '1':
                    browser.click(browser.oneElement);
                    break;
                case '2':
                    browser.click(browser.twoElement);
                    break;
                case '3':
                    browser.click(browser.threeElement);
                    break;
                case '4':
                    browser.click(browser.fourElement);
                    break;
                case '5':
                    browser.click(browser.fiveElement);
                    break;
                case '6':
                    browser.click(browser.sixElement);
                    break;
                case '7':
                    browser.click(browser.sevenElement);
                    break;
                case '8':
                    browser.click(browser.eightElement);
                    break;
                case '9':
                    browser.click(browser.nineElement);
                    break;
                case '0':
                    browser.click(browser.zeroElement);
                    break;
                case '+':
                    browser.click(browser.plusElement);
                    break;
                case '*':
                    browser.click(browser.timesElement);
                    break;
                case '-':
                    browser.click(browser.minusElement);
                    break;
                case '/':
                    browser.click(browser.divElement);
                    break;
                default:
                    throw new IllegalArgumentException("Could not find ui element for following symbol: " + symbol);
            }
        }
    }

    @When("i press result_button")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature"})
    public void press_result() {
        browser.click(browser.equalElement);
    }

    @When("i click reset")
    @Severity(SeverityLevel.BLOCKER)
    @Features({"UI feature"})
    public void press_reset() {
        browser.click(browser.clearElement);
    }

    @Then("i get in result $result")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void checkResult(int result) {
        Assert.assertEquals(Integer.parseInt(browser.getValue()), result);
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
