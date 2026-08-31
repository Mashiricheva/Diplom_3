package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    // Ссылка на форму входа со страницы восстановления пароля
    private final By loginLinkForgotForm = By.xpath(".//a[text()='Войти']");

    private WebDriver driver;
    public ForgotPasswordPage (WebDriver driver) {
        this.driver = driver;
    }
    @Step("Переход на страницу входа со страницы восстановления пароля")
    public void clickLoginLimkForgotForm () {
        driver.findElement(loginLinkForgotForm).click();
    }
}
