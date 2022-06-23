package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Task4Test {

    @Test
    public void Task4Test(){
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();

        webDriver.get("https://www.youtube.com/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        WebElement parentElement  = webDriver.findElement(By.cssSelector("div.eom-button-row.style-scope.ytd-consent-bump-v2-lightbox"));
        parentElement.findElement(By.id("button")).click();

        List<WebElement> tiles = webDriver.findElements(By.id("dismissible")).subList(0,12);
        List<YTTile> ytTileList = new ArrayList<YTTile>();
        YTTile ytTile = new YTTile();
        for (WebElement tile: tiles) {
            String title = tile.findElement(By.cssSelector("h3.style-scope.ytd-rich-grid-media")).getText();
            String channel = tile.findElement(By.cssSelector("a.yt-simple-endpoint.style-scope.yt-formatted-string")).getText();
            String time = "LIVE";

            try {
                time = tile.findElement(By.cssSelector("span.style-scope.ytd-thumbnail-overlay-time-status-renderer")).getText();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }

            System.out.println("<Tytuł> " + title + " <Kanał> " + channel + " <Czas> " + time);

            ytTile.setTitle(title);
            ytTile.setChannel(channel);
            ytTile.setLength(time);
            ytTileList.add(ytTile);
        }

        webDriver.quit();
    }
}