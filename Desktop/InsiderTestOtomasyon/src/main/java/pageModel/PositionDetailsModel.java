package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PositionDetailsModel extends BaseModel{

    public By positionDetailsPage = By.className("posting-headline");
    public By position = By.cssSelector("div.posting-headline > h2");
    public By btnApplyForThisJob = By.cssSelector("div.postings-btn-wrapper > a");
    public By positionDescription = By.cssSelector("div:nth-child(2) > div> h3");
    public By positionRequirements = By.cssSelector("div:nth-child(3) > div > h3");

    public PositionDetailsModel(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIfPositionDetails(){
        windowHandle();
        Assert.assertTrue(displayed(positionDetailsPage),"Pozisyon detay sayfasi acilmadi");
        Assert.assertTrue(displayed(btnApplyForThisJob),"Apply For This Job butonu gorulmedi");
        Assert.assertEquals(DataModel.Data.chosenPosition,getText(position),"Pozisyon bilgisi yanlis");
        Assert.assertTrue(displayed(positionRequirements),"Gereksinimler alani bilgiler gorulmedi");
        Assert.assertTrue(displayed(positionDescription),"Aciklama alani bilgileri gorulmedi");
    }

    public void clickApplyForThisJob(){
        clickElement(btnApplyForThisJob);
    }
}
