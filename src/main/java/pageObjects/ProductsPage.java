package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductsPage {
    private static final By TOP_SELLS_DESCRIP =  By.xpath("//div[@class='g-i-tile-i-box-desc' and ../div/div/div/i[@class='g-tag g-tag-icon-middle-popularity sprite']]" +
            "/div[@class='g-i-tile-i-title clearfix']/a");
    private static final By TOP_SELLS_PRICE =  By.xpath("//div[@class='g-i-tile-i-box-desc' and ../div/div/div/i[@class='g-tag g-tag-icon-middle-popularity sprite']]" +
            "/div[@class='inline']/div[@class='inline']/div[@name='price']/div[@class='g-price-uah']");
    private static final By ACTIONS_DESCRIP =  By.xpath("//div[@class='g-i-tile-i-box-desc' and ../div/div/div/i[@class='g-tag g-tag-icon-middle-action sprite']]" +
            "/div[@class='g-i-tile-i-title clearfix']/a");
    private static final By ACTIONS_PRICE =  By.xpath("//div[@class='g-i-tile-i-box-desc' and ../div/div/div/i[@class='g-tag g-tag-icon-middle-action sprite']]" +
            "/div[@class='inline']/div[@class='inline']/div[@name='price']/div[1]");

    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getUrl(){
        String title = driver.getCurrentUrl();
        return title;
    }
    public Map<String, String> searchForTopSells()  throws Exception{
        List<WebElement> list_desc;
        List<WebElement> list_price;
        Map<String, String> map = new HashMap<>();
        try {
            Thread.sleep(2000);//there is the bug in Chrome from version 40+ in refresh page and link objects
            list_desc = driver.findElements(TOP_SELLS_DESCRIP);
            list_price = driver.findElements(TOP_SELLS_PRICE);
                Log.info("There are " + list_desc.size() + " topsells products, and " + list_price.size() + " prices on the " + driver.getCurrentUrl());

                for (int i=0;i<list_desc.size();i++) {
                map.put(list_desc.get(i).getText(),list_price.get(i).getText());
            }
            return map;
        }
        catch (Exception e){
            Log.error("Can't find any topsells products on the - "+driver.getCurrentUrl());
            throw(e);
        }

    }
}
