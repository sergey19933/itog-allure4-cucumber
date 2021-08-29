package org.serg.framework.managers;

import org.serg.framework.pages.*;
import org.serg.framework.utils.MethodsUtils;


public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;



    /**
     * Страничка с утилитарными методами
     */
    private MethodsUtils methodsUtils;

    /**
     * Страничка с товаром
     */
    private ListProduct listProduct;


    /**
     * Страничка с корзиной
     */
    private CartPage cartPage;



    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return homePage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }


    /**
     * Ленивая инициализация {@link MethodsUtils}
     *
     * @return methodsUtils
     */
    public MethodsUtils getMethodsUtilsPage() {
        if (methodsUtils == null) {
            methodsUtils = new MethodsUtils();
        }
        return methodsUtils;
    }



    /**
     * Ленивая инициализация {@link ListProduct}
     *
     * @return listProduct
     */
    public ListProduct getListProduct() {
        if (listProduct == null) {
            listProduct = new ListProduct();
        }
        return listProduct;
    }


    /**
     * Ленивая инициализация {@link CartPage}
     *
     * @return cartPage
     */
    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }




}


