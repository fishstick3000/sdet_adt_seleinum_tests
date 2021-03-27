package e2e_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class TodoPageObject {

    private final WebDriver driver;
    Actions action;


    @FindBy(how = How.ID, using = "add-todo")
    public static WebElement addTodoInput;

    @FindBy(how = How.ID, using = "todo-list")
    public static WebElement todoList;

    @FindBy(xpath = "//*[@id=\'todo-list\']/li/div/label")
    public static WebElement firstTodoOnList;

    @FindBy(xpath = "//*[@id=\'todo-list\']/li/div/button")
    public static WebElement firstTodoOnListDeleteButton;


    public WebElement todoCompleteToggle;
    public WebElement deleteTodoButton;
    public WebElement selectedTodo;
    public int listSize;
    public int test;
    public String selectedTodoText;
    public String selectedTodoStatus;

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
        listSize = todoList.findElements(By.xpath("//*/li")).size();
        return listSize;
    }

    public void setTodoCompleteToggleByListPosition(int num) {
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        todoCompleteToggle = todoList.findElement(By.xpath("//*/li[" + num + "]/div/input"));
        todoCompleteToggle.click();
    }

    public String getTodoCompleteStatusByListPosition(int num) {
        selectedTodoStatus = todoList.findElement(By.xpath("//*[@id=\'todo-list\']/li[" + num + "]")).getAttribute("class");
        return selectedTodoStatus;
    }

    public String getTodoTextByListPosition(int num) {
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        selectedTodoText = todoList.findElement(By.xpath("//*/li[" + num + "]/div/label")).getText();
        return selectedTodoText;
    }

    public void deleteTodoByListPosition(int num) {
        action = new Actions(driver);
        selectedTodo = todoList.findElement(By.xpath("//*/li[" + num + "]/div/label"));
        action.moveToElement(selectedTodo).perform();
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        deleteTodoButton = todoList.findElement(By.xpath("//*/li[" + num + "]/div/button"));
        deleteTodoButton.click();
    }


    public void deleteAllTodos() {
        test = getTodoListSize();
        Actions action = new Actions(driver);
        for (int i = test; i > 0; i--) {
            // Finds the todo at the top of the list
            driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);//
            // hovers the mouse over todo
            action.moveToElement(firstTodoOnList).perform();
            driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);//
            firstTodoOnListDeleteButton.click();
        }
    }


}
