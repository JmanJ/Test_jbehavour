package ru.nsu.fit.tests.services.browser;

import java.io.File;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.nsu.fit.tests.shared.ImageUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Browser implements Closeable {
    private WebDriver webDriver;
    private ChromeDriverService service;
    private int MAX_ATTEMPTS = 10;
    private int SLEEP_TIME = 5;

    public final By inputElement = By.xpath("//input[@type='text' and @name='Input']");
    public final By equalElement = By.xpath("//input[@type='button' and @name='DoIt']");
    public final By zeroElement = By.xpath("//input[@type='button' and @name='zero']");
    public final By oneElement = By.xpath("//input[@type='button' and @name='one']");
    public final By twoElement = By.xpath("//input[@type='button' and @name='two']");
    public final By threeElement = By.xpath("//input[@type='button' and @name='three']");
    public final By fourElement = By.xpath("//input[@type='button' and @name='four']");
    public final By fiveElement = By.xpath("//input[@type='button' and @name='five']");
    public final By sixElement = By.xpath("//input[@type='button' and @name='six']");
    public final By sevenElement = By.xpath("//input[@type='button' and @name='seven']");
    public final By eightElement = By.xpath("//input[@type='button' and @name='eight']");
    public final By nineElement = By.xpath("//input[@type='button' and @name='nine']");
    public final By clearElement = By.xpath("//input[@type='button' and @name='clear']");
    public final By plusElement = By.xpath("//input[@type='button' and @name='plus']");
    public final By minusElement = By.xpath("//input[@type='button' and @name='minus']");
    public final By divElement = By.xpath("//input[@type='button' and @name='div']");
    public final By timesElement = By.xpath("//input[@type='button' and @name='times']");

    public Browser() {
        // create profile
        //FirefoxProfile profile = new FirefoxProfile();

        // create web driver

        System.setProperty("webdriver.chrome.driver", "D:\\JavaProjects\\MetTest");
        try {
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("D:\\JavaProjects\\MetTest\\chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            webDriver = new ChromeDriver(service);

            webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Browser openPage(String url) {
        webDriver.get(url);
        return this;
    }

    public Browser waitForElement(By element) {
        return waitForElement(element, 10);
    }

    public Browser waitForElement(final By element, int timeoutSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutSec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return this;
    }

    public Browser click(By element) {
        WebElement e  = delayedFindElement(element, 5);  //webDriver.findElement(element);
        e.click();
        return this;
    }

    private WebElement delayedFindElement(By by, int attempt) {
        try {
            return webDriver.findElement(by);
        } catch (NoSuchElementException e) {
            if (attempt >= MAX_ATTEMPTS) {
                throw e;
            }
            try {
                Thread.sleep(attempt * SLEEP_TIME);
            } catch (InterruptedException ie) {
                throw new RuntimeException("Could not sleep thread", ie);
            }
            return this.delayedFindElement(by, attempt + 1);
        }
    }

    public Browser typeText(String text) {
        webDriver.findElement(inputElement).sendKeys(text);
        return this;
    }

    public WebElement getElement(By element) {

        return webDriver.findElement(element);
    }

    public String getValue() {
        return getElement(inputElement).getAttribute("value");
    }

    public List<WebElement> getElements(By element) {
        return webDriver.findElements(element);
    }

    public boolean isElementPresent(By element) {
        return getElements(element).size() != 0;
    }

    public byte[] makeScreenshot() {
        try {
            return ImageUtils.toByteArray(((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() {
        webDriver.close();
    }
}

