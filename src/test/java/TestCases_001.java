import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.*;
import utility.Log;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class TestCases_001{
    private WebDriver driver;
	private String sTestCaseName;
    private HomePage homePage;


    @BeforeClass(alwaysRun = true)
    public void setUp()  throws Exception{
        DOMConfigurator.configure("log4j.xml");
        appModules.JavaToMySql.clearTable("products");
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

    @Test (priority = 1)//search  for topsells product
    public void searchForTopSellsProducts() throws Exception {
        Log.info("-------------------              searchForTopSellsProducts        ----------------------");
        PhonePage phonePage = homePage.navigateToPhonePage();

        SmPhonePage smPhonePage = phonePage.navigateToSmartPhonePage();

        ProductsPage productsPage1 = smPhonePage.navigateToProductsPage("Page1");
        appModules.JavaToMySql.storeToDb(productsPage1.searchForTopSells(),"topsells","page1",productsPage1.getUrl());


        ProductsPage productsPage2 = smPhonePage.navigateToProductsPage("Page2");
        appModules.JavaToMySql.storeToDb(productsPage2.searchForTopSells(),"topsells","page2",productsPage2.getUrl());

        ProductsPage productsPage3 = smPhonePage.navigateToProductsPage("Page3");
        appModules.JavaToMySql.storeToDb(productsPage3.searchForTopSells(),"topsells","page3",productsPage3.getUrl());
    }

  @AfterClass
  public void afterMethod() throws SQLException {
	    Log.endTestCase();
	    driver.close();
        appModules.JavaToMySql.loadDataToLog();
  		}

}
