package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task3Test {

    String searchText = "HTML select tag - W3Schools";
    String destinationUrl = "https://www.w3schools.com/tags/tag_select.asp";

    @Test
    public void Task3Test(){
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();

        webDriver.get("https://www.google.com/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        webDriver.findElement(By.id("L2AGLb")).click();
        webDriver.findElement(By.name("q")).sendKeys(searchText);
        webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[2]")).click();

        checkCurrentWebsite(webDriver);
        webDriver.findElement(By.id("accept-choices")).click();
        webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[3]/a")).click();

        ArrayList<String> pages = new ArrayList<String> (webDriver.getWindowHandles());
        webDriver.switchTo().window(pages.get(1));
        webDriver.switchTo().frame("iframeResult");
        String header = webDriver.findElement(By.xpath("/html/body/h1")).getText();
        System.out.println(header);
        WebElement selectList = webDriver.findElement(By.id("cars"));
        Select select = new Select(selectList);
        select.selectByVisibleText("Opel");
        WebElement option = select.getFirstSelectedOption();
        System.out.println(option.getAttribute("value") + ", " + option.getText());

        webDriver.quit();
    }

    public void checkCurrentWebsite(WebDriver webDriver) {
        String currentUrl = webDriver.getCurrentUrl();
        if (!currentUrl.equals(destinationUrl)) {
            System.out.println("Aktualny adres URL to: " + currentUrl);
            webDriver.get(destinationUrl);
        }
    }
}

/*
Wybrać z rozwijanej listy „Opel”
Pobrać wybrany element do WebElement i wypisać w konsoli tekst oraz wartość „value” (Opel, opel)
 */