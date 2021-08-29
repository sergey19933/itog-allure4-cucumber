package org.serg.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.serg.framework.base.BasePage;

public class HomePage extends BasePage {


    @FindBy(xpath = "//input[@placeholder='Искать на Ozon']")
    private WebElement clickSearchProduct;


    public ListProduct clickSearchProduct(String nameProduct) {
        waitUtilElementToBeClickable(clickSearchProduct);
        actions.click(clickSearchProduct).sendKeys(nameProduct, Keys.ENTER).perform();
        Assert.fail();
        return pageManager.getListProduct();
    }


}
