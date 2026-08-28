package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    // Поле Email в форме входа
    private final By emailField = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and @name=\"name\"]");
    // Поле пароля в форме входа
    private final By passwordField = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and @name=\"Пароль\"]");
    // Кнопка войти в форме входа
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    // Переход к форме регистрации
    private final By registrationFormTransit = By.xpath(".//a[@href ='/register']");
    // Переход к форме восстановления пароля
    private final By passwordRecoveryFormTransition = By.xpath(".//a[@href ='/forgot-password']");

    private WebDriver driver;
    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    // Заполняем поле Email
    public void enterEmailField (String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    // Заполняем поле с паролем
    public void enterPasswordField (String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    // нажимаем на кноаку Войти
    public void clickLoginButtonFormLogin () {
        driver.findElement(loginButton).click();
    }
    // Общий метод для входа
    public void login (String email, String password) {
        enterEmailField(email);
        enterPasswordField(password);
        clickLoginButtonFormLogin();
    }
    // Проверяем, что пользователя отправило на форму входа (после успешной регистрации)
    public boolean isLoginFormVisible() {
        try {
            return driver.findElement(loginButton).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // Переходим к форме регистрации
    public void clickRegistrationFormTransit () {
        driver.findElement(registrationFormTransit).click();
    }
    // Переходим к форме восстановления пароля
    public void clickPasswordRecoveryFormTransition () {
        driver.findElement(passwordRecoveryFormTransition).click();
    }

}
