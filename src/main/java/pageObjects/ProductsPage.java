package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Log;

import java.util.List;


public class ProductsPage {
    private static final By TOP_SELLS_DESCRIP =  By.xpath("//div[@class='g-i-tile-i-box-desc' and ../div/div/div/i[@class='g-tag g-tag-icon-middle-popularity sprite']]" +
            "/div[@class='g-i-tile-i-title clearfix']/a");
    private static final By TOP_SELLS_PRICE =  By.xpath("//div[@class='g-i-tile-i-box-desc' and ../div/div/div/i[@class='g-tag g-tag-icon-middle-popularity sprite']]" +
            "/div[@class='inline']/div[@class='inline']/div[@name='price']/div[@class='g-price-uah']");
    public WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForTopSells()  throws Exception{
        List<WebElement> list_desc;
        List<WebElement> list_price;

        try {
            Thread.sleep(1000);
            list_desc = driver.findElements(TOP_SELLS_DESCRIP);
            list_price = driver.findElements(TOP_SELLS_PRICE);
                Log.info("There are " + list_desc.size() + " topsells products, and " + list_price.size() + " prices on the -  on the - " + driver.getCurrentUrl());
        }
        catch (Exception e){
            Log.error("Can't find any topsells products on the - "+driver.getCurrentUrl());
            throw(e);
        }
        for (int i=0;i<list_desc.size();i++) {
            Log.info("product#"+i+" - "+ list_desc.get(i).getText()+" ====> price - "+ list_price.get(i).getText());
        }
    }
}
