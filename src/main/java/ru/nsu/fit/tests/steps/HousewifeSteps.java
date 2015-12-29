package ru.nsu.fit.tests.steps;


import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import org.testng.Assert;
import ru.nsu.fit.tests.services.browser.Browser;
import ru.nsu.fit.tests.services.browser.BrowserService;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.ArrayList;

public class HousewifeSteps extends Steps {

    private Browser browser;
    private ArrayList<Double> productSum = new ArrayList<Double>();

    @Given("as housewife")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void openCalculator() {
        browser.openPage("http://testmethods.tmweb.ru/");
    }

    @When("i buy $count items for $price ruble")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Multiplication"})
    public void sumOfProducts(String count, String price) {
        browser.typeText(count + "*" + price);
        productSum.add(Integer.parseInt(count) * Double.parseDouble(price));
    }

    @When("i calculate check sum")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Multiplication"})
    public void checkSum() {
        for (double psum: productSum ){
            browser.typeText(String.valueOf(psum) + "+");
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
