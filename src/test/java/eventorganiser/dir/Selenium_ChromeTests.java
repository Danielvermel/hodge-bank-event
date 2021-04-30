//package eventorganiser.dir;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.JavascriptExecutor;
//
//import java.util.concurrent.TimeUnit;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class Selenium_ChromeTests {
//
//    private WebDriver webDriver;
//
//    @Value("${local.server.port}")
//    private int port;
//    String username = "daniel8@gmail.com";
//    String password = "1234";
//
//
//    // Test - PageSource
//    @Test
//    public void test_a() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        // chrome doesnt work in the browser it opens in the background
//        // Running chrome without chrome
//        options.addArguments("--headless");
//        webDriver = new ChromeDriver(options);
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/index.html");
//
//
//        System.out.println(webDriver.getPageSource());
//        System.out.println(webDriver.findElement(By.id("sub")).getText());
//
//        assertTrue(webDriver.findElement(By.id("sub")).getText().contains("Sign in"));
//
//
//        System.out.println(webDriver.getPageSource());
//        webDriver.quit();
//    }
//
//    //Test - Register User
//    @Test
//    public void test_b() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        // chrome doesnt work in the browser it opens in the background
//        // Running chrome without chrome
//        options.addArguments("--headless");
//        webDriver = new ChromeDriver(options);
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/register");
//
//        WebDriverWait wait = new WebDriverWait(webDriver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("un")));
//        System.out.println(webDriver.getPageSource());
//        // Enter with a specific user
//        webDriver.findElement(By.id("un")).sendKeys(username);
//        webDriver.findElement(By.id("password")).sendKeys(password);
//        webDriver.findElement(By.id("cPassword")).sendKeys(password);
//        webDriver.findElement(By.id("firstName")).sendKeys("Daniel");
//        webDriver.findElement(By.id("lastName")).sendKeys("Leite");
//        webDriver.findElement(By.id("sub")).click();
//
//        System.out.println(webDriver.getPageSource());
//        assertTrue(webDriver.findElement(By.id("sub")).getText().contains("Sign in"));
//        webDriver.quit();
//    }
//
//    //Test Login User
//    @Test
//    public void test_c() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        // chrome doesnt work in the browser it opens in the background
//        // Running chrome without chrome
//        options.addArguments("--headless");
//        webDriver = new ChromeDriver(options);
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/login");
//
//
//        WebDriverWait wait = new WebDriverWait(webDriver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("un")));
//        // Enter with a specific user
//        webDriver.findElement(By.id("un")).sendKeys(username);
//        webDriver.findElement(By.id("pw")).sendKeys(password);
//        webDriver.findElement(By.id("sub")).click();
//
//        System.out.println(webDriver.getPageSource());
//        System.out.println(webDriver.findElement(By.id("feed")).getText());
//        assertTrue(webDriver.findElement(By.id("feed")).getText().contains("Events Feed"));
//
//
//        System.out.println(webDriver.getPageSource());
//        webDriver.quit();
//    }
//
//    // Test - Creating an Event
//    @Test
//    public void test_d() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        // chrome doesnt work in the browser it opens in the background
//        // Running chrome without chrome
//        options.addArguments("--headless");
//        webDriver = new ChromeDriver(options);
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/login");
//
//        WebDriverWait wait = new WebDriverWait(webDriver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("un")));
//        // Enter with a specific user
//        webDriver.findElement(By.id("un")).sendKeys(username);
//        webDriver.findElement(By.id("pw")).sendKeys(password);
//        webDriver.findElement(By.id("sub")).click();
//
//        // Enter in Events
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/addEvent");
//        System.out.println(webDriver.getPageSource());
//
//        webDriver.findElement(By.name("title")).sendKeys("Music fest II");
//        webDriver.findElement(By.id("eventStartDate")).sendKeys("2020-09-22");
//        webDriver.findElement(By.id("eventEndDate")).sendKeys("2020-09-22");
//        webDriver.findElement(By.id("eventStartTime")).sendKeys("20:00");
//        webDriver.findElement(By.id("eventEndTime")).sendKeys("24:00");
//        webDriver.findElement(By.id("eventLocSt1")).sendKeys("Llanbleddian Gardens");
//        webDriver.findElement(By.id("eventLocSt2")).sendKeys("Cathays");
//        webDriver.findElement(By.id("eventLocCity")).sendKeys("Cardiff");
//        webDriver.findElement(By.id("eventLocPost")).sendKeys("CF24 4AT");
//        webDriver.findElement(By.id("eventDesc")).sendKeys("Place to have fun");
//
//
//        webDriver.findElement(By.id("sub")).click();
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/feedPage");
//
//
//        System.out.println(webDriver.getPageSource());
//        assertTrue(webDriver.getPageSource().contains("Music fest II"));
//
//
//        System.out.println(webDriver.getPageSource());
//        webDriver.quit();
//    }
//
//
//
//    // Test - Delete Events
//    @Test
//    public void test_e() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        // chrome doesnt work in the browser it opens in the background
//        // Running chrome without chrome
//        options.addArguments("--headless");
//        webDriver = new ChromeDriver(options);
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/login");
//        WebDriverWait wait = new WebDriverWait(webDriver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("un")));
//        // Enter with a specific user
//        webDriver.findElement(By.id("un")).sendKeys(username);
//        webDriver.findElement(By.id("pw")).sendKeys(password);
//        webDriver.findElement(By.id("sub")).click();
//
//        System.out.println("---------------------------------------");
//        System.out.println(webDriver.getPageSource());
//        System.out.println("-------------------------------------------");
//
//
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/deleteEvent");
//        System.out.println(webDriver.getPageSource());
//        webDriver.findElement(By.id("delevent")).click();
//        //webDriver.findElement(By.id("deleventModel")).click();
//        System.out.println("------------------------------------------2345-");
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/feedPage");
//        System.out.println(webDriver.getPageSource());
//        assertFalse(webDriver.getPageSource().contains("Music fest II"));
//
//
//        System.out.println(webDriver.getPageSource());
//        webDriver.quit();
//    }
//
//    // Test - Logout
//    @Test
//    public void test_f() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        // chrome doesnt work in the browser it opens in the background
//        // Running chrome without chrome
//        options.addArguments("--headless");
//        webDriver = new ChromeDriver(options);
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/login");
//
//
//        WebDriverWait wait = new WebDriverWait(webDriver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("un")));
//        // Enter with a specific user
//        webDriver.findElement(By.id("un")).sendKeys(username);
//        webDriver.findElement(By.id("pw")).sendKeys(password);
//        webDriver.findElement(By.id("sub")).click();
//        System.out.println("-------------1111-----");
//        // Enter in Events
//        webDriver.get("http://localhost:" + Integer.toString(port) + "/logout");
//
//
//        System.out.println(webDriver.getPageSource());
//        assertTrue(webDriver.findElement(By.id("sub")).getText().contains("Sign in"));
//
//
//        System.out.println(webDriver.getPageSource());
//        webDriver.quit();
//    }
//
//
//
//}
//
//
//
//
