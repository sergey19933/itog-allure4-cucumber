package org.serg.framework.utils;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.serg.framework.base.BasePage;
import org.serg.framework.managers.DriverManager;

public class MethodsUtils extends BasePage {


//aside[@data-widget='searchResultsFilters']//*[contains(text(),'Цена')]/..//*[contains(text(),'до')]

    /**
     * @param nameCategory-название   категории фильтра
     * @param filter-фильтр
     * @param ofOn-включить\отключить чек бокс
     */
    public void choiceFilterProduct(String nameCategory, String filter, String ofOn) {
        String xPath = "//aside[@data-widget='searchResultsFilters']//*[contains(text(),'"
                + nameCategory + "')]/..//*[contains(text(),'" + filter + "')]/../..//input";
        WebElement webElement = DriverManager.getDriverManager().getDriver().findElement(By.xpath(xPath));
        scrollToElementJs(webElement);
        if (!webElement.isSelected() && ofOn.equalsIgnoreCase("Включить")) {
            actions.click(webElement).perform();
        } else if (webElement.isSelected() && ofOn.equalsIgnoreCase("Отключить")) {
            actions.click(webElement).perform();
        } else {
            Assert.fail();
        }

    }


    /**
     * @param nameCategory-название категории фильтра
     * @param filter-фильтр
     * @param value-ограничение     цены
     */
    public void fillInputField(String nameCategory, String filter, String value) {
        String xPath = "//aside[@data-widget='searchResultsFilters']//*[contains(text(),'"
                + nameCategory + "')]/..//*[contains(text(),'" + filter + "')]/../..//input";
        WebElement webElement = DriverManager.getDriverManager().getDriver().findElement(By.xpath(xPath));
        actions.doubleClick(webElement)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(value)
                .perform();

        Assert.assertEquals(value, webElement.getAttribute("value")
                .replaceAll("\\D*", ""), "Поле введено не верно");
    }



    /**
     * @param filter-фильтр
     * @param ofOn-включить\отключить чек бокс
     */
    public void choiceFilterProductOther(String filter, String ofOn) {
        String xPath = "//aside[@data-widget='searchResultsFilters']//*[contains(text(),'" + filter + "')]/../..//input";
        WebElement webElement = DriverManager.getDriverManager().getDriver().findElement(By.xpath(xPath));
        scrollToElementJs(webElement);
        if (!webElement.isSelected() && ofOn.equalsIgnoreCase("Включить")) {
            actions.click(webElement).perform();
        } else if (webElement.isSelected() && ofOn.equalsIgnoreCase("Отключить")) {
            actions.click(webElement).perform();
        } else {
            Assert.fail();
        }

    }



}
