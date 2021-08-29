package org.serg.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"org.serg.framework.utils.MyListnerAllure"},
        glue = {"org.serg.framework.steps"},
        features = {"src/test/resources/"},
        tags = "@firstTest"
)

public class CucumberRunner {
}
