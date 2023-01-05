package gr.xe.selenium.qaChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ReturnedResultsForRentHouses {
    WebDriver driver;
    private final By btnSearch = By.xpath("/html/body/main/div[1]/div/div[1]/div/div/div[2]/div[2]/form/div[4]/input");
    private final By btnFirstSearch = By.xpath("/html/body/main/div/div[1]/div[1]/div[3]/div/div[2]/div/div/div[1]/div/div[1]/a/div/div/div/div/div[2]/div/div/img");

    @BeforeClass
    public void initialize(){
        //Set the web driver binary path to the corresponding property
        String path = System.getProperty("user.dir");
        String chromeDriverPath = path + "/src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //We start the chromedriver
        driver = new ChromeDriver();
    }
    @AfterClass
    public void close(){
        //We close the driver
        driver.quit();
    }
    /*
    * This is our test. It will just visit gr.xe.gr and click on the houses tab.
    * */
    @Test(priority = 0, description = "Visit gr.xe.gr and click on the rent house tab")
    public void visitXeClickRentHouse() {


        //We visit gr.xe.gr
        driver.navigate().to("https://www.xe.gr/");
        driver.manage().window().maximize();

        System.out.println("Scenario - Returned Results For Rent Houses");
        System.out.println("Given that I am a user");
        System.out.println("And I open chrome browser");

        driver.findElement(By.cssSelector(".css-oygwkp")).click();
        //We click on the tab we want
        driver.findElement(By.cssSelector("#property-tab")).click();
        System.out.println("And I click on the real estate");
        //We click on the search button
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(btnSearch).click();
        System.out.println("When I click on the search");

        try{
            WebElement element = driver.findElement(btnFirstSearch);

            if(isDisplayed(element) && isEnabled(element)){
                //We click on the first search
                element.click();
                System.out.println("Then I click on the first search");
            }
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

}




