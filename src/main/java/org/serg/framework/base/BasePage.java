package org.serg.framework.base;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.serg.framework.managers.DriverManager;
import org.serg.framework.managers.PageManager;
import org.serg.framework.managers.TestPropManager;

import java.util.concurrent.TimeUnit;

import static org.serg.framework.utils.PropConst.IMPLICITLY_WAIT;

public class BasePage {

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    protected final DriverManager driverManager = DriverManager.getDriverManager();

    /**
     * Менеджер страничек
     *
     * @see PageManager
     */
    protected PageManager pageManager = PageManager.getPageManager();


    /**
     * Объект для имитации реального поведения мыши или клавиатуры
     *
     * @see Actions
     */
    protected Actions actions = new Actions(driverManager.getDriver());


    /**
     * Объект для выполнения любого js кода
     *
     * @see JavascriptExecutor
     */
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();


    /**
     * Объект явного ожидания
     * При применении будет ожидать заданного состояния 10 секунд с интервалом в 1 секунду
     *
     * @see WebDriverWait
     */
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);


    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    private final TestPropManager props = TestPropManager.getTestPropManager();


    /**
     * Конструктор позволяющий инициализировать все странички и их элементы помеченные аннотацией {@link FindBy}
     * Подробнее можно просмотреть в класс {@link org.openqa.selenium.support.PageFactory}
     *
     * @see FindBy
     * @see PageFactory
     * @see PageFactory#initElements(WebDriver, Object)
     */
    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }


    /**
     * Явное ожидание того что элемент станет видемым
     *
     * @param element - веб элемент который мы ожидаем что будет  виден на странице
     */
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    /**
     * Явное ожидание того что элемент станет видимым
     *
     * @param element - до веб элемент который мы ожидаем найти и который виден на странице
     */


    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Функция позволяющая производить scroll до любого элемента с помощью js
     *
     * @param element - веб-элемент странички
     * @see JavascriptExecutor
     */
    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    protected boolean isElement(By by,WebElement webElement) {
        try {
            driverManager.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            webElement.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        }

    }



    /**
     * Проверка, есть ли элемент
     *
     * @param xpath-путь файла
     */
    public void elementsIsNotPresent(String xpath) {
        try {
            driverManager.getDriver().manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
            Assert.assertTrue("Товары не удалились",driverManager.getDriver().findElements(By.xpath(xpath)).size() == 0);
        }catch (Exception e){
        }
        finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
        }
    }




}
