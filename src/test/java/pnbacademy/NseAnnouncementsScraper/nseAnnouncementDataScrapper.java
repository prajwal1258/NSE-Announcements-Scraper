package pnbacademy.NseAnnouncementsScraper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class nseAnnouncementDataScrapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // --- Setup WebDriverManager ---
        // This automatically downloads and sets up the correct ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        // --- Configure Chrome Options for Downloading ---
        // Create a map to set download preferences
//        Map<String, Object> prefs = new HashMap<>();
        // Define the default download directory
//        String downloadPath = "/Users/prajwalbodhale/eclipse-workspace/NseAnnouncementsScraper/downloads";
        // Set download directory
        String downloadPath = System.getProperty("user.dir") + File.separator + "downloads";
        File dir = new File(downloadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
     // Set up Chrome options
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        options.setExperimentalOption("prefs", prefs);

        // --- Initialize WebDriver ---
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        // Set an explicit wait timeout (e.g., 20 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        System.out.println("Download directory set to: " + downloadPath);

        try {
            // 1. Go to the URL
            driver.get("https://www.nseindia.com/companies-listing/corporate-filings-announcements");
            Thread.sleep(2000);

            // --- Define Locators as requested ---
            // This is the XPath you provided for the ticker input
            String tickerInputXPath = "/html[1]/body[1]/div[12]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/span[1]/input[1]";
            
            // This is the locator you provided for the "1W" button
            String oneWeekButtonXPath = "(//*[text()='1M'])[1]";

            // This is the ID you provided for the download butto
            String downloadButtonId = "CFanncEquity-download";
           
            String tickerSelect="CIPLA";
            // 2. Find and select the ticker
            System.out.println("Waiting for ticker input field...");
            
            // NOTE: The XPath you provided is an absolute XPath and is very fragile.
            // If it fails, the website structure may have changed.
            // A much more reliable locator for this field is By.id("symbol")
            WebElement tickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tickerInputXPath)));

            System.out.println("Entering ticker '"+tickerSelect+"'...");
//            tickerInput.clear();
            Thread.sleep(3000);
            tickerInput.sendKeys(tickerSelect);
            
            // Wait for the autocomplete dropdown to appear and click the first option
            // This step is necessary to confirm the symbol selection
            WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p/span[text()='"+tickerSelect +"']")));
            firstOption.click();
         
            System.out.println("Selected '"+tickerSelect+"' from dropdown.");

            // 3. Select the 1W frame
            System.out.println("Waiting for '1W' button...");
            WebElement oneWeekButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oneWeekButtonXPath)));
            oneWeekButton.click();
            System.out.println("Clicked '1W' button.");
            Thread.sleep(5000);
            // 4. Download the .csv file
            System.out.println("Waiting for download button...");
            WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(downloadButtonId)));
            downloadButton.click();
            
            System.out.println("Clicked download button. File will be saved to '" + downloadPath + "'");

            // Add a small delay to allow the download to start
            Thread.sleep(5000); // 5 seconds

            System.out.println("Script finished successfully.");

        } catch (Exception e) {
            System.err.println("An error occurred during the script execution:");
            e.printStackTrace();
        } finally {
            // Always close the browser
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed.");
            }
        }
	}

}
