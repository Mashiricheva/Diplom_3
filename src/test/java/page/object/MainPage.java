package page.object;

import io.qameta.allure.Step;
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
    private final By bunsSectionTab = By.xpath("//span[text()='Булки']/parent::div");
    // Вкладка «Соусы» в конструкторе — раздел с соусами
    private final By saucesSectionTab = By.xpath("//span[text()='Соусы']/parent::div");
    // Вкладка «Начинки» в конструкторе — раздел с начинками
    private final By fillingsSectionTab = By.xpath("//span[text()='Начинки']/parent::div");


    private WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    @Step("Открытие главной страницы")
    public void openPage () {
        driver.get("https://stellarburgers.education-services.ru/");
    }
    @Step("Клик по кнопке Войти в аккаунт")
    public void clickLoginButton () {
        driver.findElement(loginButton).click();
    }
    @Step("Клик по кнопке Личный кабинет")
    public void clickPersonalAccountButton () {
        driver.findElement(personalAccountButton).click();
    }
    @Step("Клик по разделу Булки")
    public void clickBunsSection () {
        clickTab(bunsSectionTab);
    }
    @Step("Проверка активности раздела Булки")
    public boolean isBunsSectionActive () {
        return isTabActive(bunsSectionTab);
    }
    @Step("Клик по разделу Соусы")
    public void clickSaucesSection () {
        clickTab(saucesSectionTab);
    }

        @Step("Проверка активности раздела Соусы")
        public boolean isSaucesSectionActive () {
            return isTabActive(saucesSectionTab);
        }
        @Step("Клик по разделу Начинки")
        public void clickFillingsSection () {
            clickTab(fillingsSectionTab);
        }
        @Step("Проверка активности раздела Начинки")
        public boolean isFillingsSectionActive () {
            return isTabActive(fillingsSectionTab);
        }
        @Step("Проверка успешного входа")
        public boolean isLoginSuccessful () {
            return !driver.findElements(orderButton).isEmpty();
        }

    private boolean isTabActive(By tabLocator) {
        try {
            wait.until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    private void clickTab(By tabLocator) {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(tabLocator));
        try {
            tab.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        }
        // Ждём активации вкладки
        wait.until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));
    }

}
