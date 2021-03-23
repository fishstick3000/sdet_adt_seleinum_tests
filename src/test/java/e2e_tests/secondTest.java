package e2e_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class secondTest {
    static WebDriver driver;
    private Actions action;
    private WebElement toDelete;
    private WebElement test;


    @BeforeClass
    public static void createDriverAndVisitPage() {
        //Set system properties for chromeDriver this is required since Selenium 3.0
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
    public void testDeleteTodo() {
        // instantiate Selenium Actions
        Actions action = new Actions(driver);

        // Finds the add todo input
        WebElement id1 = driver.findElement(By.id("add-todo"));

        // Inputs test todo message and submits
        id1.sendKeys("Test To Do 002");
        id1.sendKeys(Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // Gets the size of the todo list before todo is deleted
        int totalTodosBeforeDelete = driver.findElements(By.xpath("//*[@id=\"todo-list\"]/li")).size();

        // Finds the todo at the bottom of the list since it is the newest todo in the list
        WebElement id2 = driver.findElement(By.xpath("//*[@id=\'todo-list\']/li["+totalTodosBeforeDelete+"]/div/label"));

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // hovers the mouse over todo
        action.moveToElement(id2).perform();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // Finds the delete button reveled by mouse hover over the todo and clicks the button
        WebElement id3 = driver.findElement(By.xpath("//*[@id=\'todo-list\']/li["+totalTodosBeforeDelete+"]/div/button"));
        id3.click();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // Gets the size of the todo list after todo is deleted
        int totalTodosAfterDelete = driver.findElements(By.xpath("//*[@id=\"todo-list\"]/li")).size();

        //Sees if the the list of todo items shows the expected -1 list item
        assertEquals("Todo was not deleted", totalTodosBeforeDelete - 1, totalTodosAfterDelete);

        deleteAllTodos(totalTodosAfterDelete, driver);


    }

    public void deleteAllTodos(int num, WebDriver driver) {
        // instantiate Selenium Actions
        action = new Actions(driver);

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

        driver.close();
        driver.quit();
    }

}

