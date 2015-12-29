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

import java.util.ArrayList;

public class HousewifeSteps extends Steps {

    private ArrayList<Double> productSum = new ArrayList();

    @Given("as housewife")
    @Severity(SeverityLevel.BLOCKER)
    @Features("UI feature")
    public void openCalculator() {
        press_reset();
    }

    @When("i buy $count items for $price ruble")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Multiplication"})
    public void sumOfProducts(String count, String price) {
        MainPageService.browser.insert_value(count + "*" + price);
        productSum.add(Integer.parseInt(count) * Double.parseDouble(price));
    }

    @When("i calculate check sum")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"UI feature", "Addition", "Multiplication"})
    public void checkSum() {
        for (double psum: productSum ){
            MainPageService.browser.insert_value(String.valueOf(psum) + "+");
        }
        MainPageService.browser.insert_value("0");
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
    public void checkResult(int result) {
        Assert.assertEquals(Integer.parseInt(MainPageService.browser.get_value()), result);
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
