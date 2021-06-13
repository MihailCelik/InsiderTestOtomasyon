package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.interactions.Actions;

public class BaseModel {

    protected WebDriver webDriver;

    protected BaseModel(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected WebElement findElement(By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return webDriver.findElement(by);
    }

    protected void clickElement(By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        findElement(by).click();
    }

    protected String getText(By by) {
        return findElement(by).getText();
    }

    protected boolean displayed(By by) {
        return webDriver.findElement(by).isDisplayed();
    }

    protected void windowHandle() {
        String currentTabHandle = webDriver.getWindowHandle();
        String newTabHandle = webDriver.getWindowHandles()
                .stream()
                .filter(handle -> !handle.equals(currentTabHandle ))
                .findFirst()
                .get();
        webDriver.switchTo().window(newTabHandle);
    }

    protected List<WebElement> findElements(By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return webDriver.findElements(by);
    }

    protected void sleep(int second) {
        try {
            Thread.sleep(second);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    protected void moveToElement (By by) {
        Actions actionProvider = new Actions(webDriver);
        actionProvider.moveToElement(findElement(by)).build().perform();
    }

    protected By setLocatorParameters (By by,String name) {
        String test = by.toString().replace("By.xpath: ","");
        test = test.replace("{1}",name);
        return new By.ByXPath(test);
    }

    protected void scrollToElement(By by) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", findElement(by));
    }

    protected void sendKeys(By by, String text) {
        findElement(by).sendKeys(text);
    }

    protected void moveToElements (WebElement byElement) {
        new Actions(webDriver).moveToElement(byElement).perform();
    }

    protected void scrollToElements(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(webDriver, 1000);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected String getAttribute(By by, String value) {
        return findElement(by).getAttribute(value);
    }

    protected void scrollToElement(String scroll) {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("window.scrollBy(0," + scroll + ")");
    }
}
