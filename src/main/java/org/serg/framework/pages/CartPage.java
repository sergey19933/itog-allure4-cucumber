package org.serg.framework.pages;

import io.qameta.allure.Allure;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.serg.framework.base.BasePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartPage extends BasePage {

    private List<String> listProductsCart = new ArrayList<>();

    @FindBy(xpath = "//div[@data-widget='row']//div[contains(text(),'Корзина')]")
    private WebElement checkingThePage;

    @FindBy(xpath = " //span[contains(text(),'Доставка Ozon')]//..//..//a//span[contains(@style,'')]")
    private List<WebElement> productsCart;

    @FindBy(xpath = "//div[@data-widget='stickyContainer']//span[contains(text(),'Ваша корзина')]")
    private WebElement checkingCartAllProductPage;

    @FindBy(xpath = "//div[@data-widget='stickyContainer']//span[contains(text(),'тов')]")
    private WebElement checkingCartAllProductsPage;

    @FindBy(xpath = " //div[@data-widget='container']//span[contains(text(),'Удалить выбранные')]")
    private WebElement removeAllProducts;

    @FindBy(xpath = "//div[contains(text(),'Удаление товаров')]//..//button")
    private WebElement clickRemove;

    @FindBy(xpath = "//h1")
    private WebElement checkCartRemove;


    //проверка то что страница та самая
    public CartPage checkingPage() {
        Assert.assertEquals("Не та страница", "Корзина", checkingThePage.getText());
        return this;
    }

    //добавляем в лист товары из корзины и проверка
    public CartPage addProductsInListAndCheckCart() {
//        for (WebElement productList : productsCart) {
//            listProductsCart.add(productList.getText());
//        }

        for (int i = 0; i < productsCart.size(); i++) {
            listProductsCart.add(productsCart.get(i).getText());
        }

//        //проверка все ли товары добавились в корзину
//        System.out.println("Корзина: " + listProductsCart);
//        System.out.println("_______________");
//        System.out.println("То что добавлено на странице товаров: " + pageManager.getListProduct().getProductInfoList());
//        System.out.println("_______________");
//        System.out.println(pageManager.getListProduct().getProductInfoListPrice());

        boolean collectionsEquals = pageManager.getListProduct().getProductInfoList().containsAll(listProductsCart);
        Assert.assertTrue("Не совпадает список товаров", collectionsEquals);

        return this;
    }


    //проверка что "Ваша корзина  n товаров"
    public CartPage checkingCartAllProductPage() {
        String nameCartAndCountProduct = checkingCartAllProductPage.getText() + " " + " " + checkingCartAllProductsPage.getText();
        Assert.assertEquals("Не совпадет", "Ваша корзина  " + pageManager.getListProduct().getProductInfoList().size()
                + " тов", nameCartAndCountProduct.substring(0, 19));
        return this;
    }


    public CartPage removeAllProducts() {

        removeAllProducts.click();
        //подтверждение удаления товаров
        clickRemove.click();
        //проверка что появился текст корзина пуста
        try {
            Assert.assertEquals("Корзина не пуста","Корзина пуста", checkCartRemove.getText() );
        } catch (NoSuchElementException e) {
        }
        //проверка удалились ли элементы
        elementsIsNotPresent("//span[contains(text(),'Доставка Ozon')]//..//..//a//span[contains(@style,'')]");
        return this;
    }

    public CartPage getMaxPriceAndAllInfoProduct() {
        String indexProduct = Collections.max(pageManager.getListProduct().getProductInfoListPrice());
        int index = indexProduct.indexOf(indexProduct);

        Allure.addAttachment("Инфо о товаре с максимальной ценой", "Название товара: " + pageManager.getListProduct().getProductInfoList().get(index)
                + ": Максимальная цена: " + indexProduct);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < pageManager.getListProduct().getProductInfoList().size(); i++) {
            list.add("Товар: " + pageManager.getListProduct().getProductInfoList().get(i) +
                    " Цена: " + pageManager.getListProduct().getProductInfoListPrice().get(i));
        }

        Allure.addAttachment("Весь товар", list.toString());


        return this;
    }


}
