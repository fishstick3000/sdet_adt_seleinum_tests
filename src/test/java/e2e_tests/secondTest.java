package e2e_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class secondTest {
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
    public void testDeleteTodo() {
        // Adds test todo
        todoPage.addTodo("Test To Do 002");//
        int totalTodosBeforeDelete = todoPage.getTodoListSize();
        // Sends the delete function the last item on the list since it is the newest one created
        todoPage.deleteTodoByListPosition(totalTodosBeforeDelete - 1);
        // Gets the size of the todo list after todo is deleted
        int totalTodosAfterDelete = todoPage.getTodoListSize();
        //Sees if the the list of todo items shows the expected -1 list item
        assertEquals("Todo was not deleted", totalTodosBeforeDelete - 1, totalTodosAfterDelete);



    }


    @AfterClass
    public static void closeBrowser() {
        // Clean up all todo after test.
        todoPage.deleteAllTodos();
        driver.close();
        driver.quit();
    }

}

