package gr.xe.selenium.qaChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class FilterGreaterThanFiveHundredThousand {
    WebDriver driver;
    private final By dropdownList = By.xpath("/html/body/main/div[1]/div/div[1]/div/div/div[2]/div[2]/form/div[1]/button/i");
    private final By sale = By.xpath("/html/body/main/div[1]/div/div[1]/div/div/div[2]/div[2]/form/div[1]/ul/li[2]/button");
    private final By search = By.xpath("/html/body/main/div[1]/div/div[1]/div/div/div[2]/div[2]/form/div[4]/input");
    private final By price = By.xpath("/html/body/main/div/div[1]/div/div[2]/div[1]/div/div/div[1]/button");
    private final By text = By.xpath("/html/body/main/div/div[1]/div/div[2]/div[1]/div/div/div[1]/div/div/form/div[1]/label/input");
    private final By PriceHouse = By.xpath("/html/body/main/div/div[1]/div/div[3]/div/div[2]/div/div/div[1]/div/div[2]/a/div[2]/div/span[1]");


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
    @Test(priority = 0, description = "Visit gr.xe.gr and click on the sale house from dropdown list")
    public void visitXeClickSaleHouse() {

        //We visit gr.xe.gr
        driver.navigate().to("https://www.xe.gr/");
        driver.manage().window().maximize();

        System.out.println("Scenario - Search of sale house and check if the results are Greater Than Five hundred Thousand");
        System.out.println("Given that I am a user");
        System.out.println("And I open chrome browser");
        //We close the cookies
        driver.findElement(By.cssSelector(".css-oygwkp")).click();
        //We click on the tab we want
        driver.findElement(By.cssSelector("#property-tab")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(dropdownList).click();
        driver.findElement(sale).click();
        System.out.println("And I select the sale from dropdown list");
        driver.findElement(search).click();
        System.out.println("And I click on the button search");
        driver.findElement(price).click();
        System.out.println("And I click on the price filter");

        //Compare numbers
        String prices="500000";
        WebElement element_enter = driver.findElement(text);
        element_enter.sendKeys(prices);
        System.out.println("When I press 'enter' on the keyboard");
        element_enter.sendKeys(Keys.ENTER);

        WebElement e = driver.findElement(PriceHouse);
        System.out.println("First search is " +e.getText());

        String str = e.getText();
        str = str.replace(" €", "");
        str = str.replace(".", "");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        int a = Integer.parseInt(str);

            if(a<500000){
                throw new ArithmeticException("Then the price is smaller than 500000.");
            }else {
                System.out.println("Then the price is greater than 500000.");
            }
    }



}
