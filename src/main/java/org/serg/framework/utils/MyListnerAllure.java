package org.serg.framework.utils;

import io.cucumber.plugin.event.*;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.serg.framework.managers.DriverManager;

public class MyListnerAllure extends AllureCucumber5Jvm {

    public void setEventPublisher(EventPublisher publisher) {
        EventHandler<TestStepFinished> eventHandler = testStepFinished -> {
            if (testStepFinished.getResult().getStatus().equals(Status.FAILED)) {
                Allure.getLifecycle().addAttachment("Scrin", "image/png", "png"
                        , ((TakesScreenshot) DriverManager.getDriverManager().getDriver())
                                .getScreenshotAs(OutputType.BYTES));
            }
        };
        publisher.registerHandlerFor(TestStepFinished.class, eventHandler);
        super.setEventPublisher(publisher);
    }
}
