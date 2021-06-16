package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsModel extends BaseModel {

    public static By addBasket = By.xpath("//button[@class='pr-in-btn add-to-bs']");
    public static By myBasket = By.xpath("//p[.='Sepetim']");

    public ProductDetailsModel(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean checkIfProductDetailsPageOpen(){
        return displayed(addBasket);
    }

    public void clickAddBasket(){
        clickElement(addBasket);
    }

    public void clickMyBasket(){
        clickElement(myBasket);
    }
}
