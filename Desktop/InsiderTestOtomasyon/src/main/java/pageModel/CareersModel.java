package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CareersModel extends BaseModel{

    public By careersPageHead = By.id("page-head");
    public static By ourStoryBlocks = By.xpath("//h2[contains(text(),'Our story')]");
    public static By teamsBlocks = By.xpath("//a[contains(text(),'See all teams')]");
    public static By locationBlocks = By.xpath("//h3[contains(text(),'Our Locations')]");
    public static By lifeAtInsiderBlocks = By.xpath("//h2[contains(text(),'Life at Insider')]");
    public By btnFindYourDreamJob = By.xpath("//*[@id='page-head']/div/div/div[1]/div/div/a");

    public CareersModel(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIfCareerPageBlocks(){
        scrollToElement(ourStoryBlocks);
        sleep(2000);
        Assert.assertTrue(displayed(ourStoryBlocks),"Our Story Block gorulmedi");
        scrollToElement(teamsBlocks);
        sleep(2000);
        Assert.assertTrue(displayed(teamsBlocks),"Teams Block gorulmedi");
        scrollToElement(locationBlocks);
        sleep(2000);
        Assert.assertTrue(displayed(locationBlocks),"Location Block gorulmedi");
        scrollToElement(lifeAtInsiderBlocks);
        sleep(2000);
        Assert.assertTrue(displayed(lifeAtInsiderBlocks),"Life At Insider gorulmedi");
    }

    public void clickFindYourDreamJob(){
        scrollToElement(careersPageHead);
        sleep(2000);
        clickElement(btnFindYourDreamJob);
    }
}
