package com.accountability_notification_system.accountability_notification_system.services;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

@Service
public class ValorantMatchHistoryScraperService {
    private static WebDriver driver;

    public static boolean checkHistory() throws Exception {
        boolean activeToday = false;
        // TODO: get username and tag from database
        String username = "Dog";
        String connector = "%23";
        String tag = "5278";
        String url = String.format("https://tracker.gg/valorant/profile/riot/%s%s%s/overview?season=all", username, connector, tag);
        // scrape from tracker.gg
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("matches")));
            WebElement element1 = driver.findElement(By.className("matches"));
            int newMatchCount = Integer.parseInt(element1.getText().split(" ")[0]);
            // TODO: get user's match count from database
            int matchCount = 0;
            if (newMatchCount > matchCount) {
                activeToday = true;
                // TODO: set matchCount to new match count in database
            }
        } finally {
            driver.quit();
        }
        return activeToday;
    }
}
