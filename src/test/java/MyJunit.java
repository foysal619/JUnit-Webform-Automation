import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunit {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //implicit waiter
    }

    @Test
    @DisplayName("Check the form and upload file")
    public void submitForm() throws InterruptedException{

        //visit the website
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        // Accept the cookies
        WebElement cookie= driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookie.click();

        // Input the name
        driver.findElement(By.id("edit-name")).sendKeys("Foysal");

        // Input the phone number
        driver.findElement(By.id("edit-number")).sendKeys("01712345678");

        // Selection the age range
        driver.findElement(By.xpath("//label[@for='edit-agnew-20-30']")).click();

        // Enter the date
        driver.findElement(By.id("edit-date")).sendKeys("07/10/2023");

        // Enter the email
        driver.findElement(By.id("edit-email")).sendKeys("foysal@gmail.com");

        // Enter the message about myself
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I'm a automation testing learner.");


        // Upload the file
        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        uploadElement.sendKeys("C:\\Users\\Guest1\\Desktop\\roadtosdet-b5.png");

        // Wait for the submission process to complete
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Scroll down and select the checkbox
        Utils.doScroll(driver);
        WebElement checkBoxElement = driver.findElement(By.cssSelector("label[for='edit-age']"));
        boolean isDisplayed = checkBoxElement.isDisplayed();

        if (isDisplayed == true) {
            checkBoxElement.click();
        }

        // Find the submit button and click it
        WebElement submitButton = driver.findElement(By.id("edit-submit"));
        submitButton.click();

        // Wait for the submission process to complete
       try {
           Thread.sleep(5000);
        } catch (InterruptedException e) {
           e.printStackTrace();
       }

        // Find the success message element and get its text
        WebElement successMessage = driver.findElement(By.className("page-title"));
        String actualMessage = successMessage.getText();

        // Assert the expected message
        String expectedMessage = "Thank you for your submission!";
        Object Assert = actualMessage;
        Assert.equals(expectedMessage);

    }

        // Close the driver
        @AfterAll
        public void closeDriver(){
        driver.quit();
    }
}
