package com.accountability_notification_system.accountability_notification_system.services;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import com.accountability_notification_system.accountability_notification_system.exceptions.UserNotFoundException;
import com.accountability_notification_system.accountability_notification_system.model.User;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

@Service
public class ValorantMatchHistoryScraperService {
    
    private static WebDriver driver;

    // this function checks current match 
    public static boolean checkHistory(User user) throws UserNotFoundException {
        boolean activeToday = false;
        int currentMatchCount = user.getMatchCount();
        String username = user.getValUser();
        String tag = user.getValTag();
        String connector = "%23";
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
            if (newMatchCount > currentMatchCount) {
                activeToday = true;
                user.setMatchCount(newMatchCount);
            }
        } catch (Exception e) {
            throw new UserNotFoundException("User not found, or you don't have an account on tracker.gg");
        } finally {
            driver.quit();
        }
        return activeToday;
    }
}
