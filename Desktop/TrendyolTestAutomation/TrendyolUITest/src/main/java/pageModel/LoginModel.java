package pageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginModel extends BaseModel {

    public static By loginRegisterHead = By.id("login-register");
    public static By loginEmail = By.id("login-email");
    public static By loginPassword = By.id("login-password-input");
    public static By btnLogin = By.className("q-primary");

    public LoginModel(WebDriver webDriver) {
        super(webDriver);
    }

    public void login(String email, String password){
        Assert.assertTrue(displayed(loginRegisterHead),"Trendyol’a giris yap veya hesap olustur sayfasi acilmadi");
        sendKeys(loginEmail,email);
        sendKeys(loginPassword,password);
        clickElement(btnLogin);
    }
}
