package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Log;

public class SmPhonePage {
    private final By PAGE1 = By.xpath("//ul[@id='menu_categories_left']/li[1]/div/a[1]");
    private final By PAGE2 = By.xpath("//li[@id='page2']/a");
    private final By PAGE3 = By.xpath("//li[@id='page3']/a");
    public WebDriver driver;

    public SmPhonePage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductsPage navigateToProductsPage(Integer page) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element;
        try {
            switch (page) {
                case 1:
                    element = wait.until(ExpectedConditions.
                            presenceOfElementLocated(PAGE1));
                    element.click();
                    Log.info("\"SmartPhones-Page1\" link is found on the Phone Page");
                    break;
                case 2:
                    element = wait.until(ExpectedConditions.
                            presenceOfElementLocated(PAGE2));
                    element.click();
                    Log.info("\"Page2\" link is found on the Phone Page");
                    break;
                case 3:
                    element = wait.until(ExpectedConditions.
                            presenceOfElementLocated(PAGE3));
                    element.click();
                    Log.info("\"Page3\" link is found on the Phone Page");
                    break;
            }
        } catch (Exception e) {
            Log.error("Link is NOT found on the SmartPhones Page");
            throw (e);
        }
        return new ProductsPage(driver);
    }
}
