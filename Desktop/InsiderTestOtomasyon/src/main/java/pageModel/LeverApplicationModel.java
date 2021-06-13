package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeverApplicationModel extends BaseModel{

    public By leverApplication = By.xpath("//h4[contains(text(),'Submit your application')]");

    public LeverApplicationModel(WebDriver webDriver) {
        super(webDriver);
    }

    public String checkIfLeverApplicationPageOpen(){
       return getText(leverApplication);
    }
}
