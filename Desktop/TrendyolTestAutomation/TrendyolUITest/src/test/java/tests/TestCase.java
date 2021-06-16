package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageModel.HomeModel;
import pageModel.LoginModel;
import pageModel.BoutiqueModel;
import pageModel.ProductDetailsModel;
import pageModel.BasketModel;

public class TestCase extends BaseTest{

    public static String pageUrl = "https://www.trendyol.com/";
    public static String email = "Sisteme kayıtlı bir kullanıcı yazılır";
    public static String password = "Parola Yazılır";

    @Test
    public void AddProductToCart() {
        HomeModel homeModel = new HomeModel(webDriver);
        LoginModel loginModel = new LoginModel(webDriver);
        BoutiqueModel boutiqueModel = new BoutiqueModel(webDriver);
        ProductDetailsModel productDetailsModel = new ProductDetailsModel(webDriver);
        BasketModel basketModel = new BasketModel(webDriver);

        homeModel.openSiteAddress(pageUrl);
        Assert.assertEquals("trendyol",homeModel.checkIfHomePageOpen(),"Trendyol Ana sayfasi acilmadi");
        homeModel.clickLogin();

        loginModel.login(email,password);
        Assert.assertTrue(homeModel.checkIfMyAccountOpen(),"Hesaba giriş yapılamadı");

        homeModel.checkIfBoutiqueLoad();
        boutiqueModel.checkIfProductImageLoad();

        Assert.assertTrue(productDetailsModel.checkIfProductDetailsPageOpen(),"Urun detay sayfasi acilmadi");
        productDetailsModel.clickAddBasket();
        productDetailsModel.clickMyBasket();

        Assert.assertTrue(basketModel.checkIfBasketPageOpen(),"Sepet sayfasi acilmadi");
        Assert.assertTrue(basketModel.chekIfBasketProductCount().size() > 0,"Sepette urun bulunmamaktadir");
    }
}
