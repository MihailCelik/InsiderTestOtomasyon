package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log4j;
import java.util.List;
import java.util.Random;

public class BoutiqueModel extends BaseModel {

    public static By categoryHeader = By.xpath("//*[@class='main-nav']/li");
    public static By boutique = By.className("component-item");
    public static By productImage = By.xpath("//*[@class='image-container']");

    int index;
    List<WebElement> categoryList;
    List<WebElement> elementsBoutique;
    List<WebElement> elementsProductImage;
    Random rnd = new Random();

    public BoutiqueModel(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickRandom(List<WebElement> listElement){
        index = rnd.nextInt(listElement.size());
        scrollToElements(listElement.get(index));
        clickElement(listElement.get(index));
    }

    public void checkIfProductImageLoad() {
        categoryList = findElements(categoryHeader);
        clickRandom(categoryList);
        elementsBoutique = findElements(boutique);
        clickRandom(elementsBoutique);

        elementsProductImage = findElements(productImage);
        for (int j=0; j<elementsProductImage.size(); j++) {
            try {
                attributeContains(elementsProductImage.get(j),"/Content/images/defaultThumb.jpg");
            } catch (Exception  e) {
                Log4j.error(elementsProductImage.get(j).getAttribute("alt")+ ": Butik yuklenmedi");
            }
        }
        clickRandom(elementsProductImage);
    }
}
