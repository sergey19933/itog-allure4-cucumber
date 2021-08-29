package org.serg.framework.steps;

import io.cucumber.java.ru.И;
import org.serg.framework.managers.PageManager;

public class ListProductSteps {

    private final PageManager pageManager = PageManager.getPageManager();


    @И("^Категория фильтра \"(.+)\", фильтр \"(.+)\", значение \"(.+)\"$")
    public void selectFilterField(String nameFilter,String nameFilter1, String value) {
        pageManager.getListProduct().selectFilterField(nameFilter,nameFilter1, value);
    }


    @И("^Категория фильтра чекбокс \"(.+)\", субфильтр \"(.+)\", значение \"(Отключить|Включить)\"$")
    public void selectFilterFieldCheckBox(String nameFilter,String nameChek, String value) {
        pageManager.getListProduct().selectFilterCheck(nameFilter,nameChek, value);
    }

    @И("^Категория фильтра свитчера \"(.+)\", значение \"(Отключить|Включить)\"$")
    public void selectFilterFieldSwitcher(String nameFilter, String value) {
        pageManager.getListProduct().selectFilterSwitcher(nameFilter, value);
    }

    @И("^Добавление товара в количестве (\\d+)$")
    public void addEvenProduct(int numberProduct) {
        pageManager.getListProduct().addEvenProduct(numberProduct);
    }

//    @И("^Список$")
//    public void listProduct() {
//        pageManager.getListProduct().sout();
//    }
//    @И("^Список1$")
//    public void listProduct1() {
//        pageManager.getListProduct().a();
//    }


    @И("^Переход в корзину")
    public void goCart() {
        pageManager.getListProduct().goCart();
    }
}
