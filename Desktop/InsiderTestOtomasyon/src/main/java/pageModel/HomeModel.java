package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeModel extends BaseModel{

    public static By mainHead = By.id("main-head");
    public static By moreMenu = By.xpath("//span[contains(text(),'More')]");
    public static By careersMenu = By.xpath("//h5[contains(text(),'Careers')]");
    public static By btnAccept = By.id("wt-cli-accept-btn");

    public HomeModel(WebDriver webDriver) {
        super(webDriver);
    }

    public void openSiteAddress(String pageUrl){
        webDriver.get(pageUrl);
    }

    public boolean checkIfHomePageOpen(){
        return displayed(mainHead);
    }

    public void clickMoreMenu(){
        clickElement(btnAccept);
        clickElement(moreMenu);
    }

    public void clickCareersMenu(){
        clickElement(careersMenu);
    }
}
