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

public class fourthTest {
    static WebDriver driver;

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
    public void testTodoDeleteDoesNotReorderList() {
        // instantiate Selenium Actions
        Actions action = new Actions(driver);

        WebElement id1 = driver.findElement(By.id("add-todo"));

        id1.sendKeys("Test To Do 014");
        id1.sendKeys(Keys.ENTER);
        id1.sendKeys("Test To Do 024");
        id1.sendKeys(Keys.ENTER);
        id1.sendKeys("Test To Do 034");
        id1.sendKeys(Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // Finds the todo list before todo is deleted
        WebElement todoList = driver.findElement(By.xpath("//*[@id=\"todo-list\"]"));
        // Gets the size of the todo list before todo is deleted
        int totalTodos = todoList.findElements(By.xpath("//*[@id=\"todo-list\"]/li")).size();
        // Finds the todo at the bottom of the list since it is the newest todo in the list
        WebElement id2 = todoList.findElement(By.xpath("//*[@id=\'todo-list\']/li["+totalTodos+"]/div/label"));
        // hovers the mouse over todo
        action.moveToElement(id2).perform();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // Finds the delete button reveled by mouse hover over the todo and clicks the button
        WebElement id3 = driver.findElement(By.xpath("//*[@id=\'todo-list\']/li["+totalTodos+"]/div/button"));
        id3.click();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        int totalTodosafterDelete = todoList.findElements(By.xpath("//*[@id=\"todo-list\"]/li")).size();
        // Gets the size of the todo list



        // Finds the todo at
        String selectedTodo1 = todoList.findElement(By.xpath("//*[@id=\'todo-list\']/li["+ (totalTodosafterDelete) +"]/div/label")).getText();

        // Tests to see if the test text sent shows up in the newest todo object
        assertEquals("Test To Do 024 is not in the right place", "Test To Do 024", selectedTodo1);

        // Finds the todo at
        String selectedTodo2 = todoList.findElement(By.xpath("//*[@id=\'todo-list\']/li["+ (totalTodosafterDelete - 1) +"]/div/label")).getText();

        // Tests to see if the test text sent shows up in the newest todo object
        assertEquals("Test To Do 014 is not in the right place", "Test To Do 014", selectedTodo2);

        deleteAllTodos(totalTodosafterDelete, driver);
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

