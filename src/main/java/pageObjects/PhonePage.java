package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Log;

public class PhonePage{
    private static final By SEARCH_PHONE =  By.xpath("//ul[@id='menu_categories_left']/li[1]/h4/a");
    private WebDriver driver;

    public PhonePage(WebDriver driver) {
        this.driver = driver;
    }

    public SmPhonePage navigateToSmartPhonePage()  throws Exception{
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(SEARCH_PHONE));
        try {
            element.click();
            Log.info("\"Phones\" link is found on the Phone Page");
        }
        catch (Exception e){
            Log.error("\"Phones\" link is NOT found on the Phone Page");
            throw(e);
        }
        return new SmPhonePage(driver);
    }

}
