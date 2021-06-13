package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.testng.Assert;

public class OpenPositionsModel extends BaseModel{

    public static By filterByLocation = By.id("select2-filter-by-location-container");
    public static By locations = By.xpath("//li[contains(text(),'{1}')]");
    public static By filterByDepartment = By.id("select2-filter-by-department-container");
    public static By departments = By.xpath("//li[contains(text(),'{1}')]");
    public static By positionListItem = By.className("position-list-item");
    public static By positionItem = By.xpath("//*[@id='jobs-list']/div[1]");
    public static By btnApplyNowDen = By.xpath("//*[@id='jobs-list']/div[1]/div/a");
    public static By positionTitle = By.xpath("//div[@class='position-list-item-wrapper bg-light']/p");
    public static By positionDepartment = By.className("position-department");
    public static By positionLocation = By.className("position-location");

    List<WebElement> positionElements;
    List<WebElement> departmentElements;
    List<WebElement> locationElements;

    public OpenPositionsModel(WebDriver webDriver) {
        super(webDriver);
    }

    public void selectFilterByLocation(String locationsName){
        clickElement(filterByLocation);
        locations = setLocatorParameters(locations,locationsName);
        scrollToElement(locations);
        clickElement(locations);
    }

    public void selectFilterByDepartment(String departmentName){
        clickElement(filterByDepartment);
        departments = setLocatorParameters(departments,departmentName);
        scrollToElement(departments);
        clickElement(departments);
    }

    public void checkIfPositionList(){
        sleep(10000);
        positionElements = findElements(positionTitle);
        departmentElements = findElements(positionDepartment);
        locationElements = findElements(positionLocation);

        for (int i=0; i<positionElements.size(); i++) {
            Assert.assertTrue(positionElements.get(i).getText().contains(DataModel.Data.positionDepartmentName),"Pozisyon uyusmamakta");
            Assert.assertTrue(departmentElements.get(i).getText().contains(DataModel.Data.positionDepartmentName), "Pozisyonun Departmani uyusmamakta");
            Assert.assertTrue(locationElements.get(i).getText().contains(DataModel.Data.positionLocationName), "Pozisyonun Locationi uyusmamakta");
        }
    }

    public List<WebElement> checkIfPositionListItem(){
        positionElements = findElements(positionListItem);
        return positionElements;
    }

    public void clickApplyNow(){
        sleep(6000);
        scrollToElement(positionItem);
        moveToElement(positionItem);
        clickElement(btnApplyNowDen);
        DataModel.Data.chosenPosition = positionElements.get(0).getText();
    }
}
