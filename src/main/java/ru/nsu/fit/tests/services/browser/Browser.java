package ru.nsu.fit.tests.services.browser;

import java.io.File;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
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
    private By inputElement;
    private int MAX_ATTEMPTS = 10;
    private int SLEEP_TIME = 5;

    public Browser() {
        // create profile
        //FirefoxProfile profile = new FirefoxProfile();

        // create web driver

        System.setProperty("webdriver.chrome.driver", "D:\\JavaProjects\\jbehave");

        try {
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("D:\\JavaProjects\\jbehave\\chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            //ChromeOptions chromeOptions = new ChromeOptions();
            //chromeOptions.addArguments("port=9999");
            webDriver = new ChromeDriver(service);
            webDriver.navigate().refresh();
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

    private WebElement delayedFindElement(By by, int attempt) {
        try {
            webDriver.navigate().refresh();
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

    public Browser click(By element) {
        webDriver.findElement(element).click();
        return this;
    }

    public Browser typeText(By element, String text) {
        webDriver.findElement(element).sendKeys(text);
        return this;
    }

    public WebElement getElement(By element) {
        return webDriver.findElement(element);
    }

    public String getValue(By element) {
        return getElement(element).getAttribute("value");
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

    public void setInputElement(By inputElement){
        this.inputElement = inputElement;
    }

    public void klick_imitation(By elemnt){
        click(elemnt);
    }

    public String get_value(){
        return getValue(inputElement);
    }

    public void insert_value(String value){
        typeText(inputElement, value);
    }

    @Override
    public void close() {
        webDriver.close();
    }
}

