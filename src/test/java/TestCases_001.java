import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.*;
import utility.Log;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
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
        System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(driver).open();
    }

    @Test (priority = 1)
    public void searchForTopSellsProducts() throws Exception {
        Log.info("$$$$$$$$$$$$$$$$$$$$$               searchForTopSellsProducts        $$$$$$$$$$$$$$$$$$$$$$$$$");
        PhonePage phonePage = homePage.navigateToPhonePage();

        SmPhonePage smPhonePage = phonePage.navigateToSmartPhonePage();

        ProductsPage productsPage1 = smPhonePage.navigateToProductsPage(1);
        productsPage1.searchForTopSells();

        ProductsPage productsPage2 = smPhonePage.navigateToProductsPage(2);
        productsPage2.searchForTopSells();

        ProductsPage productsPage3 = smPhonePage.navigateToProductsPage(3);
        productsPage3.searchForTopSells();
    }
  @AfterClass
  public void afterMethod() {
	    Log.endTestCase(sTestCaseName);
	    driver.close();
  		}

}
