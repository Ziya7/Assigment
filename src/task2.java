import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class task2 {
    public static void main(String[] args) throws InterruptedException {
//     1. Launch Chrome browser.

        System.setProperty("webdriver.chrome.driver", "/Users/ziya/Documents/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
//        2. Navigate to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

//        3. Login using username Tester and password test
        driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
        driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
        ///html/body/form/div[3]/input[3]
        driver.findElement(By.xpath("//form//input[@class='button']")).click();


//        4. Click on Order link

        driver.findElement(By.xpath("//a[text()='Order']")).click();

//        5. Enter a random product quantity between 1 and 100

        int quantity = (int) (Math.random() * 101);

        driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys( Keys.BACK_SPACE,String.valueOf(quantity),Keys.ENTER);

//        6. Click on Calculate and verify that the Total value is correct.
//                Price per unit is 100.  The discount of 8 % is applied to quantities of 10+.
//        So for example, if the quantity is 8, the Total should be 800.
//        If the quantity is 20, the Total should be 1840.
//        If the quantity is 77, the Total should be 7084. And so on.
        String actualVal = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value");
        String amount = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value");
        double actualAmnt = Double.parseDouble(amount);
//        System.out.println("actual " + actualAmnt);

        double expectedAmnt = calculate(quantity);
//        System.out.println("expected " + expectedAmnt);
        Assert.assertEquals(actualAmnt, expectedAmnt);

        //       7. Generate and enter random first name and last name.
//        8. Generate and Enter random street address
//        9. Generate and Enter random city
//        10. Generate and Enter random state
//        11. Generate and Enter a random 5 digit zip code

        Faker faker =new Faker();

        String zipCode= faker.address().zipCode().substring(0,5);



        String name=faker.name().fullName();
        String address=faker.address().streetAddress();
        String city=faker.address().city();
        String state=faker.address().state();

        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name=\"ctl00$MainContent$fmwOrder$TextBox2\"]")).sendKeys(address);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys(city);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(state);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zipCode);




        // 11. Select the card type randomly. On each run your script should select a random type.
        List<String> cardTypeList = Arrays.asList("ctl00_MainContent_fmwOrder_cardList_0",
                "ctl00_MainContent_fmwOrder_cardList_1", "ctl00_MainContent_fmwOrder_cardList_2");
        String randomCardType = cardTypeList.get(new Random().nextInt(cardTypeList.size()));
        driver.findElement(By.id(randomCardType)).click();
//        13. Generate and enter the random card number:
//        If Visa is selected, the card number should start with 4.
//        If MasterCard is selected, card number should start with 5.
//        If American Express is selected, card number should start with 3.
//        Card numbers should be 16 digits for Visa and MasterCard, 15 for American Express.

        String visaCard = String.valueOf(4000000000000000L + (long) (Math.random() * 100000000000000L)); // Visa 4
        String mastercard = String.valueOf(5000000000000000L + (long) (Math.random() * 100000000000000L)); // Mastercard 5
        String americanExpress = String.valueOf(300000000000000L + (long) (Math.random() * 10000000000000L)); // American Express 3
        String cardType = "";
        String randomCardNum = "";

        switch (randomCardType) {
            case "ctl00_MainContent_fmwOrder_cardList_0":
                cardType = "Visa";
                randomCardNum = visaCard;
                driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(randomCardNum);
                break;
            case "ctl00_MainContent_fmwOrder_cardList_1":
                cardType = "MasterCard";
                randomCardNum = mastercard;
                driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(randomCardNum);
                break;
            case "ctl00_MainContent_fmwOrder_cardList_2":
                cardType = "American Express";
                randomCardNum = americanExpress;
                driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(randomCardNum);
                break;
        }
        Thread.sleep(2000);
        // 14. Enter a valid expiration date (newer than the current date)
        String expDate = "08/22";
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys(expDate);

        driver.findElement(By.xpath("//a[.='Process']")).click();

        //        16. Verify that “New order has been successfully added” message appeared on the page.
        String actualMsg = driver.findElement(By.xpath("//div//strong")).getText();
        Assert.assertEquals(actualMsg, "New order has been successfully added.");

//        17. Log out of the application.
        driver.findElement(By.id("ctl00_logout")).click();

    }



    public static double calculate(int quantity) {

        double total = 0;
        if (quantity >= 10) {
            total = quantity * (100 * 0.92);
        } else if (quantity < 10) {
            total = quantity * 100;
        }

        System.out.println(total);

        return total;


    }
}

}
