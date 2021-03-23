package e2e_tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class firstTest {
    static WebDriver driver;


    @BeforeClass
    public static void createDriverAndVisitPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        // instantiate the Chrome driver
        driver = new ChromeDriver();
        // tell the driver to navigate to the test page
        driver.get("http://localhost:3000/");
        //Maximise browser window
        driver.manage().window().maximize();
        //Adding wait
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testAddTodo() {

        // Finds the add todo input
        WebElement id1 = driver.findElement(By.id("add-todo"));

        // Inputs test todo message and submits
        id1.sendKeys("Test To Do 001");
        id1.sendKeys(Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // Gets the size of the todo list
        int totalTodos = driver.findElements(By.xpath("//*[@id=\"todo-list\"]/li")).size();
        // Finds the todo at the bottom of the list since it is the newest todo in the list
        String selectedTodo = driver.findElement(By.xpath("//*[@id=\'todo-list\']/li["+totalTodos+"]/div/label")).getText();

        // Tests to see if the test text sent shows up in the newest todo object
        assertEquals("Test To Do 001 is not found in the list", "Test To Do 001", selectedTodo);

        deleteAllTodos(totalTodos, driver);

    }

    public void deleteAllTodos(int num, WebDriver driver) {
        WebElement toDelete;
        WebElement test;
        // instantiate Selenium Actions
        Actions action = new Actions(driver);

        for (int i = num; i > 0; i--) {
            // Finds the todo at the top of the list
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            toDelete = driver.findElement(By.xpath("//*[@id=\'todo-list\']/li/div/label"));
            // hovers the mouse over todo
            action.moveToElement(toDelete).perform();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            test = driver.findElement(By.xpath("//*[@id=\'todo-list\']/li/div/button"));
            test.click();
        }

    }

    @AfterClass
    public static void closeBrowser() {
        // quit the driver
        driver.close();
        driver.quit();
    }

}
