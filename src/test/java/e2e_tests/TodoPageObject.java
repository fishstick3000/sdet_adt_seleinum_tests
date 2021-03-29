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

    @FindBy(className = "view")
    public static WebElement firstTodoOnList;

    @FindBy(className = "destroy")
    public static WebElement firstTodoOnListDeleteButton;

    @FindBy(className = "todo")
    public static List<WebElement> listOfTodos;


    public WebElement todoCompleteToggle;
    public WebElement deleteTodoButton;
    public WebElement selectedTodo;
    public int listSize;
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
        listSize = listOfTodos.size();
        return listSize;
    }

    public void setTodoCompleteToggleByListPosition(int num) {
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        todoCompleteToggle = listOfTodos.get(num-1).findElement(By.className("toggle"));
        todoCompleteToggle.click();
    }

    public String getTodoCompleteStatusByListPosition(int num) {
        selectedTodoStatus = listOfTodos.get(num-1).getAttribute("class");
        return selectedTodoStatus;
    }

    public String getTodoTextByListPosition(int num) {
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        selectedTodoText = listOfTodos.get(num-1).getText();
        System.out.print(selectedTodoText);
        return selectedTodoText;
    }

    public void deleteTodoByListPosition(int num) {
        action = new Actions(driver);
        selectedTodo = listOfTodos.get(num-1);
        action.moveToElement(selectedTodo).perform();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        deleteTodoButton = listOfTodos.get(num-1).findElement(By.className("destroy"));
        deleteTodoButton.click();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
    }


    public void deleteAllTodos() {
        listSize = getTodoListSize();
        Actions action = new Actions(driver);
        for (int i = listSize; i > 0; i--) {
            driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);//
            action.moveToElement(firstTodoOnList).perform();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);//
            firstTodoOnListDeleteButton.click();
        }
    }

    public void printTest() {
//        System.out.print(listOfTodos.size());
//        System.out.print();
    }


}
