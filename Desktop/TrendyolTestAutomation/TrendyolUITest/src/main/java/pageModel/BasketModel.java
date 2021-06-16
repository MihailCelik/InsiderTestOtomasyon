package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasketModel extends BaseModel {

    public static By productItem = By.cssSelector(".pb-item");
    public static By basketPage = By.id("basketAside");

    public BasketModel(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean checkIfBasketPageOpen(){
        return displayed(basketPage);
    }

    public List<WebElement> chekIfBasketProductCount(){
        return findElements(productItem);
    }
}
