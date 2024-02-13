package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class Lab2 {
    private WebDriver chromeDriver;


    private static final String baseUrl = "https://www.nmu.org.ua/ua/";


    //@BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        this.chromeDriver = new ChromeDriver(chromeOptions);
    }

    @Before
    public void preconditions() {
        chromeDriver.get(baseUrl);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        chromeDriver.quit();
    }

    @Test
    public void testHeaderExist() {
        WebElement header = chromeDriver.findElement(By.id("header"));
        Assert.assertNotNull(header);
    }

    @Test
    public void testClickOnForStudent() {
        WebElement forStudentButton = chromeDriver.findElement(By.xpath("/html/body/center/div[4]/div/div[1]/ul/li[4]/a"));
        Assert.assertNotNull(forStudentButton);
        forStudentButton.click();
        Assert.assertNotEquals(chromeDriver.getCurrentUrl(), baseUrl);


    }

    @Test
    public void testSearchFieldOnForStudentPage() {
        String studentPageUrl = "content/student_life/students/";
        chromeDriver.get(baseUrl + studentPageUrl);
        WebElement searchField = chromeDriver.findElement(By.tagName("input"));
        Assert.assertNotNull(searchField);
        System.out.println(String.format("Name attribute: %s", searchField.getAttribute("name")) +
                String.format("\nID attribute: %s", searchField.getAttribute("id")) +
                String.format("\nType attribute: %s", searchField.getAttribute("type")) +
                String.format("\nValue attribute: %s", searchField.getAttribute("value")) +
                String.format("\nPosititon: (%d;%d)", searchField.getLocation().x, searchField.getLocation().y) +
                String.format("\nSize: %d;%d", searchField.getSize().height, searchField.getSize().width)

        );

        String inputValue = "Need info";
        searchField.sendKeys(inputValue);
        Assert.assertEquals(searchField.getText(), inputValue);
        searchField.sendKeys(Keys.ENTER);
        Assert.assertNotEquals(chromeDriver.getCurrentUrl(), studentPageUrl);

    }


    @Test
    public void testSlider() {

    WebElement nextButton = chromeDriver.findElement (By.className ("next"));
    WebElement nextButtonByCss = chromeDriver.findElement(By.cssSelector("a.next"));
    Assert.assertEquals(nextButton, nextButtonByCss);

    WebElement previousButton = chromeDriver.findElement(By.className("prev"));
   for (int i = 0; i < 20; i++ ) {
       if (nextButton.getAttribute("class").contains("disabled")) {
           previousButton.click();
       Assert.assertTrue(previousButton.getAttribute("class").contains("disabled"));
       Assert.assertFalse(nextButton.getAttribute("class").contains("disabled"));
   }
      else {
      nextButton.click();

        Assert.assertTrue(nextButton.getAttribute( "class") .contains("disabled"));
        Assert.assertFalse(previousButton.getAttribute(  "class") .contains("disabled"));
}
    }
}
}