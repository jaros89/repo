package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Task2Test {

    String specificLanguage = "Engilsh";

    @Test
    public void Task2Test(){
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();

        webDriver.get("https://pl.wikipedia.org/wiki/Wiki");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        List<WebElement> langList = webDriver.findElements(By.cssSelector("a.interlanguage-link-target"));
        printLangNames(langList);

        webDriver.quit();
    }

    public void printLangNames(List<WebElement> langList) {
        for (WebElement lang: langList) {
            String langName = lang.getText();

            if (langName.equals(specificLanguage)) {
                String url = lang.getAttribute("href");
                System.out.println(langName + ' ' + url);
            } else {
                System.out.println(langName);
            }
        }
    }
}