package org.serg.framework.steps;

import io.cucumber.java.ru.И;
import org.serg.framework.managers.PageManager;

public class HomePageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Поиск товара \"(.+)\"$")
    public void clickSearchProduct(String nameProduct) {
        pageManager.getHomePage().clickSearchProduct(nameProduct);
    }
//
//    @И("^Выбираем \"(.+)\" в главном меню$")
//    public void selectBaseMenu(String nameMainMenu) {
//        pageManager.getHomePage().selectBaseMenu(nameMainMenu);
//    }
//
//    @И("^Выбираем \"(.+)\" в подменю главного меню$")
//    public void selectSubMenu(String nameSubMenu) {
//        pageManager.getHomePage().selectSubMenu(nameSubMenu);
//    }
}
