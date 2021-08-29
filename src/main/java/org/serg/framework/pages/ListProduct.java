package org.serg.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.serg.framework.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ListProduct extends BasePage {

    private List<String> productInfoList = new ArrayList<>();
    private List<String> productInfoListPrice = new ArrayList<>();

    @FindBy(xpath = "//aside[@data-widget='searchResultsFilters']//div[contains(@class,'filter-block')]//label")
    private List<WebElement> listFilterMenu;

    @FindBy(xpath = "//aside[@data-widget='searchResultsFilters']//div[contains(@class,'filter-block')]")
    private List<WebElement> listFilterCheck;

    @FindBy(xpath = "//div[@data-widget='searchResultsFiltersActive']")
    private WebElement choiceActiveFilter;

    @FindBy(xpath = "//span[contains(text(),'оставит')]//../..//div[contains(text(),'В корзину')]")
    private List<WebElement> addProductCart;

    @FindBy(xpath = " //a[@href='/cart']")
    private WebElement countCart;

    @FindBy(xpath = "//span[contains(text(),'оставит')]//../..//div[contains(text(),'В корзину')]//..//..//..//..//..//..//..//..//div[contains(@style,'50%;')]//a[contains(@href,'/product/')]//span//span")
    private List<WebElement> productInfo;

    @FindBy(xpath = "//span[contains(text(),'Корзина')]")
    private WebElement goCart;


    @FindBy(xpath = "//span[contains(text(),'оставит')]//../..//div[contains(text(),'В корзину')]//..//../..//..//..//..//..//a[contains(@href,'/product/')]//div[2]//span[1]")
    private List<WebElement> priceProduct;

    @FindBy(xpath = "//p[contains(text(),'Найти')]")
    private WebElement searchFilter;


    //выбор главного фильтра
    private WebElement choiceFilterField(String nameFilter) {
        for (WebElement filter : listFilterCheck) {
            if (filter.getAttribute("innerText").contains(nameFilter)) {
                return filter;
            }
        }
        Assert.fail("Фильтр '" + nameFilter + "' не найдено");
        return null;
    }

    //выбор подпункта заполняемых фильтров
    public ListProduct selectFilterField(String nameFilter, String nameFilterSub, String value) {
        WebElement blockFilter = choiceFilterField(nameFilter);
        try {
            WebElement field = blockFilter.findElement(By.xpath(".//p[contains(text(),'" + nameFilterSub + "')]/../input"));
            actions.doubleClick(field)
                    .sendKeys(Keys.BACK_SPACE)
                    .sendKeys(value)
                    .sendKeys(Keys.ENTER)
                    .perform();
            //проверка то что поле

//            Pattern pattern = Pattern.compile(".*" + nameFilter + ".*");
//            Matcher matcher = pattern.matcher(activeFilter);

            //    String activeFilter = choiceActiveFilter.getText();


            boolean checkField = wait.until(ExpectedConditions.attributeContains(field, "value", value));
            Assert.assertTrue("Фильтр не добавлен", checkField);

            //time();

        } catch (NoSuchElementException e) {
            Assert.fail("Фильтр '" + nameFilter + "' не найдено");
        }

        return this;

    }


    //выбор фильтра чекбокса с скрытами фильтрами и работа с ним
    public ListProduct selectFilterCheck(String nameFilter, String nameChek, String onOff) {
        WebElement blockFilter = choiceFilterField(nameFilter);


        if (isElement(By.xpath(".//span[@class='show']"), blockFilter)) {
            if (blockFilter.findElement(By.xpath(".//span[@class='show']")).isDisplayed()) {
                blockFilter.findElement(By.xpath(".//span[@class='show']")).click();
                actions.moveToElement(searchFilter)
                        .doubleClick(searchFilter)
                        .sendKeys(nameChek)
                        .perform();
            }
            selectChekBox(nameChek, blockFilter, onOff);
            blockFilter.findElement(By.xpath(".//span[@class='show']")).click();
        }
        //   selectChekBox(nameChek, blockFilter, onOff);
//        blockFilter.findElement(By.xpath(".//span[@class='show']")).click();
        //   time();

        return this;
    }


    private void selectChekBox(String nameChek, WebElement blockFilter, String onOff) {
        for (WebElement label : blockFilter.findElements(By.xpath(".//label"))) {
            if (label.getText().contains(nameChek)) {
                if (!label.isSelected() && onOff.equalsIgnoreCase("Включить")) {

                    label.click();


                    boolean checkField = wait.until(ExpectedConditions.attributeContains(choiceActiveFilter, "innerText", nameChek));
                    Assert.assertTrue("Фильтр не добавлен", checkField);


                } else if (label.isSelected() && onOff.equalsIgnoreCase("Отключить")) {

                    label.click();


                    boolean checkField = wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(choiceActiveFilter, "innerText", nameChek)));
                    Assert.assertTrue("Фильтр не добавлен", checkField);
                } else {
                    Assert.fail("Неверные данные");
                }
                return;
            }
        }
        Assert.fail("Чекбокс '" + nameChek + "' не найдено");
    }


    //выбор фильтра свитчера и работа с ним
    public ListProduct selectFilterSwitcher(String nameFilter, String onOff) {
        WebElement blockFilter = choiceFilterField(nameFilter);

        selectChekSwitcher(nameFilter, blockFilter, onOff);


        //  time();

        return this;
    }

    private void selectChekSwitcher(String nameFilter, WebElement blockFilter, String onOff) {
        for (WebElement label : blockFilter.findElements(By.xpath(".//label"))) {
            if (label.getText().contains(nameFilter)) {
                if (!label.isSelected() && onOff.equalsIgnoreCase("Включить")) {
                    label.click();

                    boolean checkField = wait.until(ExpectedConditions.attributeContains(choiceActiveFilter, "innerText", nameFilter));

                    Assert.assertTrue("Фильтр не добавлен", checkField);

                } else if (label.isSelected() && onOff.equalsIgnoreCase("Отключить")) {
                    label.click();

                    boolean checkField = wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(choiceActiveFilter, "innerText", nameFilter)));
                    Assert.assertTrue("Фильтр не добавлен", checkField);

                } else {
                    Assert.fail("Неверные данные");
                }
                return;
            }
        }
        Assert.fail("Чекбокс '" + nameFilter + "' не найдено");
    }
//Добавление товара
    //------------------------------------------------------


    public ListProduct addEvenProduct(int numberProduct) {

        for (int i = 1; i < addProductCart.size(); i++) {


            //запоминаем инфу о товаре
            productInfoList.add(productInfo.get(i).getText());

            //запоминаем цену о товаре
            productInfoListPrice.add(priceProduct.get(i).getText());


            //    waitUtilElementToBeClickable(addProductCart.get(i));
            actions.moveToElement(addProductCart.get(i)).click(addProductCart.get(i)).perform();


            String addProd = countCart.getText();
            boolean checkCountAdd = wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(countCart, "innerText", addProd)));
            Assert.assertTrue("Не добавлено",checkCountAdd );

            int countCartInt = Integer.parseInt(countCart.getText().replaceAll("[^0-9,]", ""));
            if (countCartInt == numberProduct) {
                break;
            }

        }

        return this;
    }

//    public ListProduct a() {
//        for (String x : productInfoListPrice) {
//            System.out.println(x.replaceAll("[^0-9,]", ""));
//        }
//        return this;
//    }
//
//    public ListProduct sout() {
//        for (String x : productInfoList) {
//            System.out.println(x);
//        }
//
//        return this;
//    }

    //переход в корзину
    public CartPage goCart() {
        goCart.click();
        return pageManager.getCartPage().checkingPage().addProductsInListAndCheckCart();
    }


    public List<String> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<String> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public List<String> getProductInfoListPrice() {
        return productInfoListPrice;
    }

    public void setProductInfoListPrice(List<String> productInfoListPrice) {
        this.productInfoListPrice = productInfoListPrice;
    }

    private void time() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}



