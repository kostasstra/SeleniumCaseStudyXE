package gr.xe.selenium.qaChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.NoSuchElementException;

public class SelectLandForSaleApplyPlotFilterSaveSearch {

    WebDriver driver;
    WebDriverWait wait ;

    String username = "kostasstra@yopmail.com";
    String password = "QaStratigosAutomation!1900";

    @BeforeClass
    public void initialize(){
        //Set the web driver binary path to the corresponding property
        String path = System.getProperty("user.dir");
        String chromeDriverPath = path + "/src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @Test(priority = 0, description = "Visit gr.xe.gr, select land for sale from the categories and perform a search")
    public void visitXeSelectLandForSaleFromCategoriesPerformSearch() {
        //We start the chromedriver
        driver = new ChromeDriver();
        //We define the implicit wait for this driver
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        //wait = new WebDriverWait(driver, 15);
        //We visit xe.gr
        driver.navigate().to("https://www.xe.gr/");
        driver.manage().window().maximize();

        System.out.println("Scenario - Select land for sale and save search ");
        System.out.println("Given that I am a user");
        System.out.println("And I open chrome browser");

        //The cookies banner obstructs our test here so we have to close it
        closeCookiesBannerIfPresent();
       // driver.findElement(By.cssSelector(".css-oygwkp")).click();
        //Select land for sale
        //Change css selector from poliseis-gis to poliseis-gis-oikopedon
        System.out.println("And I click on the land for sale ");
        driver.findElement(By.cssSelector("a[href*='poliseis-gis-oikopedon']")).click();
        //Perform the search
        //Change css selector from .buttonSave to input[class='button-property redesigned
        System.out.println("And I click on the search ");
        driver.findElement(By.cssSelector("input[class='button-property redesigned']")).click();

    }
        //Old Function
        //@Test(priority = 1, description = "Expand the filters and apply plot")
        //public void expandFiltersApplyPlot() {
        ////The cookies banner obstructs our test here so we have to close it
        //closeCookiesBannerIfPresent();
        ////Expand the filters tab
        //driver.findElement(By.cssSelector("div[data-toggle='expand_filters']")).click();
        ////Select the plot option
        //driver.findElement(By.cssSelector(".checkbox-filter-container label[id*='plot-checkbox']")).click();
        ////Apply the filter
        //driver.findElement(By.cssSelector(".buttons-container .submit-button")).click();
        //}

    @Test(priority = 1, description = "Save the previous search")
    public void saveSearch() {

        //Click to save the search
        System.out.println("And I click on the save search ");
        driver.findElement(By.cssSelector("[data-testid='save-search-btn']")).click();
        //Click to connect
        System.out.println("And I click on the login");
        driver.findElement(By.cssSelector("a[class='button-property external-link-button text-center']")).click();
        //Close cookies
        closeCookiesBannerIfPresent();
        //Wait for username to be visible and fill it in
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        driver.findElement(By.cssSelector("input#email")).sendKeys(username);
        //Fill in password
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        //Click to connect
        driver.findElement(By.cssSelector(".login_button span")).click();

        //Delete search
        deleteSearchIfdisplayed();

        //Click to back
        driver.navigate().back();
        //Click to back page
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='save-search-btn']")));
        driver.findElement(By.cssSelector("[data-testid='save-search-btn']")).click();
        //Deal with the save search pop-up

        saveSearchDoNothingIfAlreadySaved();
    }

    @AfterClass
    public void close(){
        //We close the driver
        driver.quit();
    }

    /*
    * Closes the cookies banner if it's present on the page
    */
    public void closeCookiesBannerIfPresent(){

    //        if(driver.findElements(By.cssSelector(".btn-disclaimer-ok")).size()>0)
    //            driver.findElement(By.cssSelector(".btn-disclaimer-ok")).click();
    //    }
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-oygwkp")));
        if(driver.findElements(By.cssSelector(".css-oygwkp")).size()>0)
           driver.findElement(By.cssSelector(".css-oygwkp")).click();
    }

    /*
     * Clicks to save search in the save search pop up and then closes it.
     * If no pop-up is present then it does nothing.
     */
    public void saveSearchDoNothingIfAlreadySaved(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.button-property")));

        //Change css selector from input.button-property to  button-property redesigned
        if(driver.findElements(By.cssSelector("input.button-property")).size()>0) {

            //Save the search
            driver.findElement(By.cssSelector("input.button-property")).click();
            //Close the success modal
          driver.findElement(By.cssSelector(".xe-modal-close .xe.xe-close")).click();
            System.out.println("Then search is saved");
        }else{
            System.out.println("Then search isn't saved");
        }
    }

    public void deleteSearchIfdisplayed(){

        //check if I have save search
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='user-info-label']")));
        driver.findElement(By.cssSelector("div[class='user-info-label']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='pu_img_container']")));
        driver.findElement(By.cssSelector("div[class='pu_img_container']")).click();


        try{
            while(true) {
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .ignoring(NoSuchElementException.class, InvalidSelectorException.class)
                        .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/fieldset/div/table/tbody/tr[3]/td[2]/a"))))
                       .getText();

                WebElement strvalue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/fieldset/div/table/tbody/tr[3]/td[2]/a"));

                String expected = "Πωλήσεις εκτάσεων γης";
                String actual = strvalue.getText();
                //System.out.println(actual);

                if(expected.equals(actual)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/fieldset/div/table/tbody/tr[3]/td[5]/a")).click();
                    driver.findElement(By.xpath("/html/body/div[7]/div/div[3]/button[2]")).click();
                    System.out.println("When search existed delete it");
                }
            }
        } catch (Exception ignored){
            System.out.println("Search doesn't exist");

        }
    }

}