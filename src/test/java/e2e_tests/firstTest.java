package e2e_tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class firstTest {
    static WebDriver driver;
    static TodoPageObject todoPage;

    @BeforeClass
    public static void createDriverAndVisitPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        // instantiate the Chrome driver
        driver = new ChromeDriver();
        todoPage = new TodoPageObject(driver);
    }

    @Test
    public void testAddTodo() {
        todoPage.addTodo("Test To Do 001");
        // Gets the size of the todo list
        int totalTodos = todoPage.getTodoListSize();
        // Finds the todo at the bottom of the list since it is the newest todo in the list
        String selectedTodoText = todoPage.getTodoTextByListPosition(totalTodos);
        // Tests to see if the test text sent shows up in the newest todo object
        assertEquals("Test To Do 001 is not found in the list", "Test To Do 001", selectedTodoText);
        todoPage.deleteAllTodos();
    }

    @AfterClass
    public static void closeBrowser() {
        // quit the driver
        driver.close();
        driver.quit();
    }

}
