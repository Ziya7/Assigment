import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class task1 {

    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "/Users/ziya/Documents/drivers/chromedriver");


        WebDriver driver = new ChromeDriver();

        //  1. Navigate to http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php
        driver.get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");

        //3. Verify the title is "Welcome to Duotify!"


        String title = driver.getTitle();
        String expectedTitle = "Welcome to Duotify!";

        if (title.equals(expectedTitle)) {
            System.out.println("Title name correct!");
        } else {
            System.out.println("It is not correct!");
        }

        //  5. Fill out the form with the required info
        String s = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPGTSTIVWXYZ";
        String username = "";
        for (int i = 0; i < 5; i++) {
            username = username + s.charAt((int) (Math.random() * s.length()));
            ;
        }

        Thread.sleep(1000);

        String firstName = "James";
        String lastName = "Bond";
        String password = "Bond12345";


        driver.findElement(By.id("hideLogin")).click();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.name("email")).sendKeys(username + "@gmail.com");
        driver.findElement(By.name("email2")).sendKeys(username + "@gmail.com");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("password2")).sendKeys(password);

        // 6. Click on Sign up
        Thread.sleep(2000);
        driver.findElement(By.name("registerButton")).click();

        //7. Once logged in to the application, verify that the URL is:
        //http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?

        String currentUrl = driver.getCurrentUrl();

        String expectedUrl = "http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?";


        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Test passed: " + currentUrl);
        } else {
            System.out.println("Test failed. The current url was " + currentUrl);
        }


        //8. Click on the username on the left navigation bar and then click logout.

        driver.findElement(By.id("nameFirstAndLast")).click();

        Thread.sleep(2000);

        driver.findElement(By.id("rafael")).click(); // logout

        //  9. Verify that you are logged out by verifying the URL is:
        //http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php

        String currentUrl2 = driver.getCurrentUrl();

        String expectedUrl2 = "http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?";


        if (currentUrl.equals(expectedUrl2)) {
            System.out.println("Test passed: " + currentUrl2);
        } else {
            System.out.println("Test failed. The current url was " + currentUrl2);
        }

        Thread.sleep(2000);

        //10. Login using the same username and password when you signed up.

        driver.findElement(By.id("loginUsername")).sendKeys(username);
        driver.findElement(By.id("loginPassword")).sendKeys(password);
        driver.findElement(By.name("loginButton")).click();


        Thread.sleep(2000);


        //11.Verify successful login by verifying that the home page contains the text "You Might Also Like".
        System.out.println(driver.getPageSource().contains("You Might Also Like"));


    }
}
