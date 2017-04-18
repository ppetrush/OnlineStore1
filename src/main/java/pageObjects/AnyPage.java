package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Log;

import java.util.List;

public class AnyPage    {
    private WebDriver driver;

    protected AnyPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForActions(By descriprtion, By price)  throws Exception{
        List<WebElement> list_desc;
        List<WebElement> list_price;
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(descriprtion));
        try {
            list_desc = driver.findElements(descriprtion);
            list_price = driver.findElements(price);
            Log.info("There are "+list_desc.size()+" actions products, and "+list_price.size()+" prices on the - "+driver.getCurrentUrl());
            if (list_price.size()==0){
                Log.info(driver.getPageSource());
            }
        }
        catch (Exception e){
            Log.error("Can't find any actions products on the - "+driver.getCurrentUrl());
            throw(e);
        }
        for (int i=0;i<list_desc.size();i++) {
            Log.info("product#"+i+" - "+ list_desc.get(i).getText()+" ====> price - "+ list_price.get(i).getText());
        }
    }
}
