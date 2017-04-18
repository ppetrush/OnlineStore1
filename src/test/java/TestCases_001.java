import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.*;
import utility.Log;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCases_001{
    private WebDriver driver;
	private String sTestCaseName;
    private HomePage homePage;


    @BeforeClass(alwaysRun = true)
    public void setUp() {
        DOMConfigurator.configure("log4j.xml");
        sTestCaseName = this.toString();
        Log.startTestCase(sTestCaseName);
     // System.setProperty("webdriver.ie.driver","./src/testData/drivers/IEDriverServer.exe");
     // Log.info("New IE driver instantiated");
     // driver= new InternetExplorerDriver();
     // System.setProperty("webdriver.gecko.driver","./src/testData/drivers/geckodriver.exe");
     // System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
     // driver= new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(driver).open();
    }

//    @Test (priority = 1)
//    public void searchForTopSellsProducts1() throws Exception {
//        Log.info("$$$$$$$$$$$$$$$$$$$$$               searchForTopSellsProducts        $$$$$$$$$$$$$$$$$$$$$$$$$");
//        PhonePage phonePage = homePage.navigateToPhonePage();
//
//        SmPhonePage smPhonePage = phonePage.navigateToSmartPhonePage();
//
//        ProductsPage productsPage1 = smPhonePage.navigateToProductsPage(1);
//        productsPage1.searchForTopSells();
//
//        ProductsPage productsPage2 = smPhonePage.navigateToProductsPage(2);
//        productsPage2.searchForTopSells();
//
//        ProductsPage productsPage3 = smPhonePage.navigateToProductsPage(3);
//        productsPage3.searchForTopSells();
//    }
//
//    @Test(priority = 2)
//    public void searchForActionsProducts() throws Exception {
//        Log.info("$$$$$$$$$$$$$$$$$$$$$               searchForActionsProducts        $$$$$$$$$$$$$$$$$$$$$$$$$");
//        PhonePage phonePage = homePage.navigateToPhonePage();
//
//        SmPhonePage smPhonePage = phonePage.navigateToSmartPhonePage();
//
//        ProductsPage productsPage1 = smPhonePage.navigateToProductsPage(1);
//        productsPage1.searchForActions();
//
//        ProductsPage productsPage2 = smPhonePage.navigateToProductsPage(2);
//        productsPage2.searchForActions();
//
//        ProductsPage productsPage3 = smPhonePage.navigateToProductsPage(3);
//        productsPage3.searchForActions();
//    }
    @Test(priority = 2)
    public void searchForTopSellsProducts() throws Exception {
        Log.info("$$$$$$$$$$$$$$$$$$$$$               NEW        $$$$$$$$$$$$$$$$$$$$$$$$$");
        final By SEARCH_SMANDPHONE =  By.xpath("//li[@id='3361']/a[@name='fat_menu_link']");
        final By SEARCH_PHONE =  By.xpath("//ul[@id='menu_categories_left']/li[1]/h4/a");

        final By PAGE1 = By.xpath("//ul[@id='menu_categories_left']/li[1]/div/a[1]");
        final By PAGE2 = By.xpath("//li[@id='page2']/a");
        final By PAGE3 = By.xpath("//li[@id='page3']/a");

        final By TOP_SELLS_DESCRIP =  By.xpath("//div[@class='g-i-tile-i-box-desc' and ../div/div/div/i[@class='g-tag g-tag-icon-middle-popularity sprite']]" +
                "/div[@class='g-i-tile-i-title clearfix']/a");
        final By TOP_SELLS_PRICE =  By.xpath("//div[@class='g-i-tile-i-box-desc' and ../div/div/div/i[@class='g-tag g-tag-icon-middle-popularity sprite']]" +
                "/div[@class='inline']/div[@class='inline']/div[@name='price']/div[@class='g-price-uah']");

        AnyPage anyPage = homePage.navigateToPage(SEARCH_SMANDPHONE);

        AnyPage smPhonePage = homePage.navigateToPage(SEARCH_PHONE);

        AnyPage productsPage1 =  homePage.navigateToPage(PAGE1);
        productsPage1.searchForActions(TOP_SELLS_DESCRIP,TOP_SELLS_PRICE);

        AnyPage productsPage2 =  homePage.navigateToPage(PAGE2);
        productsPage2.searchForActions(TOP_SELLS_DESCRIP,TOP_SELLS_PRICE);

        AnyPage productsPage3 =  homePage.navigateToPage(PAGE3);
        productsPage3.searchForActions(TOP_SELLS_DESCRIP,TOP_SELLS_PRICE);
    }
  @AfterClass
  public void afterMethod() {
	    Log.endTestCase(sTestCaseName);
	    driver.close();
  		}

}
