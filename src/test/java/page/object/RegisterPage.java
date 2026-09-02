package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    // Поле имя в форме регистрации
    private final By nameFieldRegisterForm = By.xpath(".//label[text()='Имя']/following-sibling::input");
    // Поле Email в форме регистрации
    private final By emailFieldRegistrationForm = By.xpath(".//label[text()='Email']/following-sibling::input");
    // Поле пароль в форме регистрации
    private final By passwordFieldRegistrationForm = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    // Кнопка регистрации в форме регистрации
    private final By registrationButton = By.xpath(".//button[text() ='Зарегистрироваться']");
    // Сообщение о некорректном пароле
    private final By errorMassage = By.xpath(".//p[text()='Некорректный пароль']");
    // Ссылка «Войти» — переход на страницу авторизации
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    private WebDriver driver;
    public RegisterPage (WebDriver driver) {
        this.driver = driver;
    }
    @Step ("Заполняем поле Имя")
    public void enterNameFieldRegisterForm (String name) {
        driver.findElement(nameFieldRegisterForm).sendKeys(name);
    }
    @Step ("Заполняем поле Email")
    public void enterEmailFieldRegistrationForm (String email) {
        driver.findElement(emailFieldRegistrationForm).sendKeys(email);
    }
    @Step ("Заполням поле Пароль")
    public void enterPasswordFieldRegistrationForm (String password) {
        driver.findElement(passwordFieldRegistrationForm).sendKeys(password);
    }
    @Step ("Нажимаем на кнопку Зарегистрироваться")
    public void clickRegistrationButton () {
        driver.findElement(registrationButton).click();
    }
    @Step ("Переходим на страницу авторизации")
    public void clickLoginLink () {
        driver.findElement(loginLink).click();
    }
    // Метод для регистрации
    public void register (String name,String email,String password) {
        enterNameFieldRegisterForm(name);
        enterEmailFieldRegistrationForm(email);
        enterPasswordFieldRegistrationForm(password);
        clickRegistrationButton();
    }
    @Step ("Проверяем наличие сообщения об ошибке")
    public boolean isPasswordErrorVisible () {
        try {
            return driver.findElement(errorMassage).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    @Step ("Получаем текст ошибки")
    public String getPasswordError () {
        return driver.findElement(errorMassage).getText();
    }

}
