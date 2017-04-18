package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Log;


public class HomePage{
    private static final String HOME_PAGE_URL = "http://www.rozetka.com.ua";
    private static final By SEARCH_SMANDPHONE =  By.xpath("//li[@id='3361']/a[@name='fat_menu_link']");
    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public HomePage open() {
        driver.get(HOME_PAGE_URL);
        return this;
    }

    public PhonePage navigateToPhonePage() throws Exception{
        WebElement element = (new WebDriverWait(driver, 10))
       .until(ExpectedConditions.elementToBeClickable(SEARCH_SMANDPHONE));
        try {
            String title=driver.getTitle();
            element.click();
            Thread.sleep(1000);
            if (driver.getTitle().equals(title)){element.click();}
            Log.info("\"Phones & smartPhones\" link is found on the Main Page");
        }
        catch (Exception e){
            Log.error("\"Phones & smartPhones\" link is NOT found on the Main Page");
            throw(e);
        }
        return new PhonePage(driver);
    }
}
