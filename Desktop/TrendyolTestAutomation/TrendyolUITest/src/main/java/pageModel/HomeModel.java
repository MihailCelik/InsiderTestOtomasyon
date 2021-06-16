package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log4j;
import java.util.List;

public class HomeModel extends BaseModel {

    public static By closeModal = By.className("modal-close");
    public static By logoHead = By.id("logo");
    public static By goLogin = By.xpath("//p[.='Giriş Yap']");
    public static By myAccount = By.xpath("//p[.='Hesabım']");
    public static By categoryHeader = By.xpath("//*[@class='main-nav']/li");
    public static By boutiqueImage = By.xpath("//span[@class='image-container']/img");

    List<WebElement> categoryList;
    List<WebElement> elementsBoutiqueImage;

    public HomeModel(WebDriver webDriver) {
        super(webDriver);
    }

    public void openSiteAddress(String pageUrl){
        webDriver.get(pageUrl);
    }

    public String checkIfHomePageOpen(){
        clickElement(closeModal);
        return getAttribute(logoHead,"title");
    }

    public void clickLogin(){
        clickElement(goLogin);
    }

    public boolean checkIfMyAccountOpen(){
        sleep(2000);
        return displayed(myAccount);
    }

    public void checkIfBoutiqueLoad() {

        categoryList = findElements(categoryHeader);
        for (int i=0; i<categoryList.size(); i++) {
            clickElements(categoryHeader,i);
            elementsBoutiqueImage = findElements(boutiqueImage);

            for (int j=0; j<elementsBoutiqueImage.size(); j++) {
                try {
                    scrollToElements(elementsBoutiqueImage.get(j));
                    attributeContains(elementsBoutiqueImage.get(j),"https://cdn.dsmcdn.com/web/production/large_boutique_placeholder.png");
                } catch (Exception  e) {
                    Log4j.error(elementsBoutiqueImage.get(j).getAttribute("alt")+ ": Butik yuklenmedi");
                }
            }
        }
    }
}
