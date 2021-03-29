package e2e_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestTest {
    static WebDriver driver;

    static TodoPageObject todoPage;

    @BeforeClass
    public static void createDriverAndVisitPage() throws IOException {
        //Set system properties for chromeDriver this is required since Selenium 3.0
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        // instantiate the Chrome driver
        driver = new ChromeDriver();
        todoPage = new TodoPageObject(driver);
    }

    @Test
    public void testCheckTodoAsComplete() {
        int list = todoPage.getTodoListSize() - 1;
        System.out.print(list);
        System.out.print(todoPage.getTodoCssTextDecorationByListPosition(list));
    }


    @AfterClass
    public static void closeBrowser() {
        // quit the driver
        driver.close();
        driver.quit();
    }
}
