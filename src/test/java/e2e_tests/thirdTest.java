package e2e_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class thirdTest {
    static WebDriver driver;

    static TodoPageObject todoPage;

    String test;

    @BeforeClass
    public static void createDriverAndVisitPage() throws IOException {
        //Set system properties for chromeDriver this is required since Selenium 3.0
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        // instantiate the Chrome driver
        driver = new ChromeDriver();
        todoPage = new TodoPageObject(driver);
    }

    @Test
    public void testCheckTodoAsComplete() throws InterruptedException {
        todoPage.addTodo("Test To Do 003");
        int totalTodos = todoPage.getTodoListSize();
        todoPage.setTodoCompleteToggleByListPosition(totalTodos - 1);
        // checks the most recently created todo item to see if it has been successfully switched to todo completed
        assertEquals("Test To Do 001 is not completed", "todo completed", todoPage.getTodoCompleteStatusByListPosition(totalTodos - 1));
        // The sleep is necessary for css elements to finish transitioning otherwise pulls wrong text color
        Thread.sleep(500);
        test = todoPage.getTodoCssTextDecorationByListPosition(totalTodos - 1);
        // tests to make sure the the correct styling is applied to completed todos
        assertEquals("css styling is not correct", "line-through solid rgb(217, 217, 217)", test);


    }


    @AfterClass
    public static void closeBrowser() {
        // Clean up all todo after test.
        todoPage.deleteAllTodos();
        // quit the driver
        driver.close();
        driver.quit();
    }
}
