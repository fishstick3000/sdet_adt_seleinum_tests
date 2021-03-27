package e2e_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class fourthTest {
    static WebDriver driver;
    static TodoPageObject todoPage;

    @BeforeClass
    public static void createDriverAndVisitPage() {
        //Set system properties for chromeDriver this is required since Selenium 3.0
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        // instantiate the Chrome driver
        driver = new ChromeDriver();
        todoPage = new TodoPageObject(driver);
    }

    @Test
    public void testTodoDeleteDoesNotReorderList() {
        todoPage.addTodo("Test To Do 014");
        todoPage.addTodo("Test To Do 024");
        todoPage.addTodo("Test To Do 034");
        // Gets the size of the todo list
        int totalTodos = todoPage.getTodoListSize();
        // Send the delete function the last li item position
        todoPage.deleteTodoByListPosition(totalTodos);
        int totalTodosAfterDelete = todoPage.getTodoListSize();
        // Finds the todo at
        String selectedTodo1 = todoPage.getTodoTextByListPosition(totalTodosAfterDelete);
        // Finds the todo at
        String selectedTodo2 = todoPage.getTodoTextByListPosition(totalTodosAfterDelete - 1);
        // Tests to see if the test text sent shows up in the newest todo object
        assertEquals("Test To Do 024 is not in the right place", "Test To Do 024", selectedTodo1);
        // Tests to see if the test text sent shows up in the newest todo object
        assertEquals("Test To Do 014 is not in the right place", "Test To Do 014", selectedTodo2);

        todoPage.deleteAllTodos();
    }

    @AfterClass
    public static void closeBrowser() {
        // quit the driver
        driver.close();
        driver.quit();
    }
}

