package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    // Кнопка "Войти в аккаунт" на главной странице
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    // Кнопка "Личный кабинет" на главной странице
    private final By personalAccountButton = By.xpath(".//a[@href='/account']");
    // Кнопка «Оформить заказ» — появляется после успешного входа (успешная регистрация)
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    // Вкладка «Булки» в конструкторе — раздел с булочками
    private final By bunsSection = By.xpath("//div[contains(@class, 'tab_tab')][.//span[text()='Булки']]");
    // Вкладка «Соусы» в конструкторе — раздел с соусами
    private final By saucesSection = By.xpath("//div[contains(@class, 'tab_tab')][.//span[text()='Соусы']]");
    // Вкладка «Начинки» в конструкторе — раздел с начинками
    private final By fillingsSection = By.xpath("//div[contains(@class, 'tab_tab')][.//span[text()='Начинки']]");
    // Заголовки разделов булки,соусы,начинки
    private final By bunsSectionHeader = By.xpath("//h2[text()='Булки']");
    private final By saucesSectionHeader = By.xpath("//h2[text()='Соусы']");
    private final By fillingsSectionHeader = By.xpath("//h2[text()='Начинки']");

    private WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    public void openPage () {
        driver.get("https://stellarburgers.education-services.ru/");
    }
    // Нажимаем на кнопку войти в аккаунт
    public void clickLoginButton () {
        driver.findElement(loginButton).click();
    }
    // Нажимаем на кнопку личный кабинет
    public void clickPersonalAccountButton () {
        driver.findElement(personalAccountButton).click();
    }
    // Нажимаем на раздел булочки
    public void clickBunsSection () {
        clickElementWithoutScroll(bunsSection);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunsSectionHeader));
    }
    // Проверяем, что раздел с булочками открылся
    public boolean isBunsSectionActive () {
        return driver.findElement(bunsSectionHeader).isDisplayed();
    }
    // Нажимаем на раздел с соусами
    public void clickSaucesSection () {
       driver.findElement(saucesSection).click();
       waitForHeader(saucesSectionHeader);
    }

    // Проверяем, что раздел с соусами открылся
    public boolean isSaucesSectionActive () {
        return driver.findElement(saucesSectionHeader).isDisplayed();
    }
    // Нажимаем на раздел с начинками
    public void clickFillingsSection () {
        driver.findElement(fillingsSection).click();
        waitForHeader(fillingsSectionHeader);
    }
    // Проверяем, что раздел с начинками открылся
    public boolean isFillingsSectionActive () {
        return driver.findElement(fillingsSectionHeader).isDisplayed();
    }
    // Проверяем успешную авторизацию
    public boolean isLoginSuccessful() {
        return !driver.findElements(orderButton).isEmpty();
    }
    // Метод для безопасного клика
    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        // Прокручиваем элемент в центр видимой области
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        // Ждём кликабельности
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
    private void waitForHeader(By headerLocator) {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        // Дополнительно прокручиваем к заголовку (чтобы он точно был в зоне видимости)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", header);
    }
    // Клик без прокрутки для булок
    private void clickElementWithoutScroll(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

}
