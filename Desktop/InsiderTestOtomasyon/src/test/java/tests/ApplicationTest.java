package tests;

import org.testng.Assert;
import pageModel.*;
import org.testng.annotations.Test;

public class ApplicationTest extends BaseTest{

    @Test
    public void JabApplicationTest() {
        HomeModel homeModel = new HomeModel(webDriver);
        CareersModel careersModel = new CareersModel(webDriver);
        OpenPositionsModel openPositionsModel = new OpenPositionsModel(webDriver);
        LeverApplicationModel leverApplicationModel = new LeverApplicationModel(webDriver);
        PositionDetailsModel positionDetailsModel = new PositionDetailsModel(webDriver);

        homeModel.openSiteAddress(DataModel.Data.pageUrl);
        Assert.assertTrue(homeModel.checkIfHomePageOpen(),"Insider sayfasi acilmadi");
        homeModel.clickMoreMenu();
        homeModel.clickCareersMenu();

        careersModel.checkIfCareerPageBlocks();
        careersModel.clickFindYourDreamJob();

        openPositionsModel.selectFilterByLocation(DataModel.Data.positionLocationName);
        openPositionsModel.selectFilterByDepartment(DataModel.Data.positionDepartmentName);
        Assert.assertTrue(openPositionsModel.checkIfPositionListItem().size()>0,"Is listesinde Quality Assurance pozisyonu listelenmedi");
        openPositionsModel.checkIfPositionList();
        openPositionsModel.clickApplyNow();

        positionDetailsModel.checkIfPositionDetails();
        positionDetailsModel.clickApplyForThisJob();

        Assert.assertEquals("SUBMIT YOUR APPLICATION",leverApplicationModel.checkIfLeverApplicationPageOpen(),"Lever Application sayfasi acilmadi");
    }
}
