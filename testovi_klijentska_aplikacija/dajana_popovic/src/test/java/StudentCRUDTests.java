import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CRUD testovi za studente")
public class StudentCRUDTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/#/view-student");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldCreateStudent() {
        addStudent("Ana Anic", "ana@test.com", "M-Tech");

        WebElement table = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("TABLE"))
        );

        String text = table.getText();
        assertTrue(text.contains("Ana Anic"));
        assertTrue(text.contains("ana@test.com"));
        assertTrue(text.contains("M-Tech"));
    }

    @Test
    void shouldReadStudents() {
        addStudent("Marko Markovic", "marko@test.com", "M-Tech");

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("TABLE")));
        String tableText = table.getText();

        assertTrue(tableText.contains("Marko Markovic"));
        assertTrue(tableText.contains("marko@test.com"));
        assertTrue(tableText.contains("M-Tech"));
    }

    @Test
    void shouldUpdateStudent() {

        addStudent("Jovan Jovanic", "jovan@test.com", "M-Tech");

        WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//td[contains(text(), 'Jovan Jovanic')]/parent::tr")));

        WebElement updateBtn = row.findElement(By.cssSelector("button.btn-info"));
        updateBtn.click();

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[formcontrolname='studentName']")));
        nameInput.clear();
        nameInput.sendKeys("Stanko Stankovic");

        WebElement emailInput = driver.findElement(
                By.cssSelector("input[formcontrolname='studentEmail']"));
        emailInput.clear();
        emailInput.sendKeys("stanko@test.com");

        new Select(driver.findElement(
                By.cssSelector("select[formcontrolname='studentBranch']")))
                .selectByVisibleText("MCA");

        driver.findElement(By.cssSelector(".modal-footer .btn-success")).click();

        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/app-root/div/app-student-list/div[2]/div/div/form/div[3]/button[2]")));
        closeBtn.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal.show")));

        WebElement viewStudentsBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/app-root/div/nav/ul/li[1]/a")));
        viewStudentsBtn.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("TABLE"), "Stanko Stankovic"));

        String tableText = driver.findElement(By.tagName("TABLE")).getText();
        assertTrue(tableText.contains("Stanko Stankovic"));
        assertFalse(tableText.contains("Jovan Jovanic"));
    }


    @Test
    void shouldDeleteStudent() {
        addStudent("Maja Majic", "ana@test.com", "M-Tech");

        WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//td[contains(text(), 'Maja Majic')]/parent::tr")));

        WebElement deleteBtn = row.findElement(By.tagName("I"));
        deleteBtn.click();

        try { Thread.sleep(3000); } catch (InterruptedException e) {}

        WebElement table = driver.findElement(By.tagName("TABLE"));
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(table, "Maja Majic")));
        assertFalse(table.getText().contains("Maja Majic"));
    }



    private void addStudent(String name, String email, String branch) {

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[routerlink='add-student']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[formcontrolname='studentName']")));

        driver.findElement(By.cssSelector(
                "input[formcontrolname='studentName']")).sendKeys(name);

        driver.findElement(By.cssSelector(
                "input[formcontrolname='studentEmail']")).sendKeys(email);

        new Select(driver.findElement(
                By.cssSelector("select[formcontrolname='studentBranch']")))
                .selectByVisibleText(branch);

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Student Added')]")));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[routerlink='view-student']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("TABLE")));
    }
}


