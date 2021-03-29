package e2e_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TodoPageObject {

    private final WebDriver driver;
    Actions action;


    @FindBy(how = How.ID, using = "add-todo")
    public static WebElement addTodoInput;

    @FindBy(how = How.ID, using = "todo-list")
    public static WebElement todoList;

    @FindBy(className = "todo")
    public static List<WebElement> listOfTodos;


    public WebElement todoCompleteToggle;
    public WebElement deleteTodoButton;
    public WebElement selectedTodo;
    public int listSize;
    public String selectedTodoText;
    public String selectedTodoStatus;
    public String selectedTodoAtt;

    public static WebElement getTodoList() {
        return todoList;
    }

    public TodoPageObject(WebDriver driver) {
        this.driver = driver;
        // tell the driver to navigate to the test page
        driver.get("http://localhost:3000/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        PageFactory.initElements(driver, this);
    }

    public void addTodo(String string) {
        // Finds the add todo input
        addTodoInput.sendKeys(string);
        // Inputs test todo message and submits
        addTodoInput.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
    }

    public int getTodoListSize() {
        listSize = listOfTodos.size();
        return listSize;
    }

    public void setTodoCompleteToggleByListPosition(int num) {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        todoCompleteToggle = listOfTodos.get(num).findElement(By.className("toggle"));
        todoCompleteToggle.click();
        driver.manage().timeouts().implicitlyWait(800, TimeUnit.MILLISECONDS);
    }

    public String getTodoCompleteStatusByListPosition(int num) {
        selectedTodoStatus = listOfTodos.get(num).getAttribute("class");
        return selectedTodoStatus;
    }

    public String getTodoTextByListPosition(int num) {
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        selectedTodoText = listOfTodos.get(num).getText();
        return selectedTodoText;
    }

    public String getTodoCssTextDecorationByListPosition(int num) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        selectedTodoAtt = listOfTodos.get(num).findElement(By.xpath("//*/div/label")).getCssValue("text-decoration");
        return selectedTodoAtt;
    }

    public void deleteTodoByListPosition(int num) {
        action = new Actions(driver);
        selectedTodo = listOfTodos.get(num);
        action.moveToElement(selectedTodo).perform();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        deleteTodoButton = listOfTodos.get(num).findElement(By.className("destroy"));
        deleteTodoButton.click();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
    }


    public void deleteAllTodos() {
        listSize = getTodoListSize();
        Actions action = new Actions(driver);
        for (int i = listSize; i > 0; i--) {
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            action.moveToElement(listOfTodos.get(i-1)).perform();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            listOfTodos.get(i-1).findElement(By.className("destroy")).click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        }
    }


}
