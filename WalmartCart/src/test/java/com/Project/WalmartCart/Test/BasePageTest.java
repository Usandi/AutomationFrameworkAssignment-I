package com.Project.WalmartCart.Test;

import com.Project.WalmartCart.Base.BasePage;
import com.Project.WalmartCart.Base.BaseSetUp;
import com.Project.WalmartCart.Pages.CartPage;
import com.Project.WalmartCart.Pages.ProductPage;
import com.Project.WalmartCart.Pages.SearchProductsPage;
import com.Project.WalmartCart.Pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class BasePageTest extends BaseSetUp {
    BasePage basepage;

    @DataProvider(name="productsData")
    public Object[][] pData() {
        return new Object[][] { {40, "25"} };
    }

    @DataProvider(name="productData")
    public Object[][] proData() {
        return new String[][] { {"Walmart # 579798970", "Fisher-Price Baby’s First Blocks & Rock-a-Stack, Plant-Based Toys"} };
    }

    @DataProvider(name="cartData")
    public Object[][] cData() {
        return new String[][] { {"Fisher-Price Baby’s First Blocks & Rock-a-Stack, Plant-Based Toys - Walmart.com - Walmart.com", "Fisher-Price Baby’s First Blocks & Rock-a-Stack, Plant-Based Toys"} };
    }

    @DataProvider(name="searchData")
    public Object[][] sData() {
        return new String[][] { {"infant toys", "infant toys - Walmart.com"} };
    }

    @Test (dataProvider = "searchData")
    public void SearchResultPageTest(String searchWord, String expctedpgtitle) throws InterruptedException {
        basepage = new BasePage(driver);
        SearchResultPage srp = basepage.Search(searchWord);//SearchResultPage returned by search() from BasePage
        Assert.assertEquals(srp.getpgTitle(), expctedpgtitle);


    }

    @Test(dataProvider = "productsData", dependsOnMethods = {"SearchResultPageTest"})
    public void SearchProductsPageTest(int tnumproducts, String tnumpages) throws InterruptedException {
        basepage = new BasePage(driver);
        SearchProductsPage spp = basepage.productsData();
        Object productsinfo[] = spp.getProductsData();
        Assert.assertEquals(productsinfo[0],tnumproducts);
        Assert.assertEquals(productsinfo[1],tnumpages);
    }

    @Test(dataProvider = "productData", dependsOnMethods = {"SearchProductsPageTest"})
    public void ProductPageTest(String itemnum, String itemdes) throws InterruptedException {
        basepage = new BasePage(driver);
        ProductPage pp = basepage.productData();
        String productinfo[] = pp.getProductData();
        Assert.assertEquals(productinfo[0],itemnum);
        Assert.assertEquals(productinfo[1],itemdes);
    }

    @Test(dataProvider = "cartData", dependsOnMethods = {"ProductPageTest"})
    public void CartPageTest(String pgtitle, String citemdes) throws InterruptedException {
        basepage = new BasePage(driver);
        CartPage cp = basepage.cartData();
        String cartinfo[] = cp.getCartData();
        Assert.assertEquals(cartinfo[0],pgtitle);
        Assert.assertEquals(cartinfo[1],citemdes);
    }
}
