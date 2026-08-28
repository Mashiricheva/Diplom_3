package page.object;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class FactoryDriver extends ExternalResource {
    private WebDriver driver;

    public WebDriver getDriver () {
        return driver;
    }
    public void initDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        switch (browser) {
            case "yandex":
                startYandexBrowser();
                break;
            case "chrome":
            default:
                startChromeBrowser();
                break;
        }
    }
    private void startChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // открыть браузер на весь экран
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // неявное ожидание
    }
    private void startYandexBrowser() {
        WebDriverManager.chromedriver().driverVersion("150.0.7871.1788").setup(); // для Яндекс Браузера подходит chromedriver
        ChromeOptions options = new ChromeOptions();
        String yandexBrowserPath = System.getProperty(
                "yandex.browser.path",
                "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe"
        );
        options.setBinary(yandexBrowserPath); // запуск именно Яндекса
        options.addArguments("--start-maximized"); // открыть браузер на весь экран

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // неявное ожидание
    }
    @Override
    protected void before() throws  Throwable {
        initDriver();
    }
    // Закрываем браузер
    @Override
    protected void after() {
        driver.quit();
    }
}
