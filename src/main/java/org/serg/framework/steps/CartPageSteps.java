package org.serg.framework.steps;

import io.cucumber.java.ru.И;
import org.serg.framework.managers.PageManager;

public class CartPageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

//
//    @И("^Список проверка$")
//    public void addProductsInListAndCheckCart() {
//        pageManager.getCartPage().addProductsInListAndCheckCart();
//    }

    @И("^Проверка количества товаров$")
    public void checkingCartAllProductPage() {
        pageManager.getCartPage().checkingCartAllProductPage();
    }


    @И("^Проверка удалились товаров$")
    public void removeAllProducts() {
        pageManager.getCartPage().removeAllProducts();
    }

    @И("^Получение информации о товаре$")
    public void allInfoProduct() {
        pageManager.getCartPage().getMaxPriceAndAllInfoProduct();
    }

}
