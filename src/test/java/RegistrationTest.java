import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.FactoryDriver;
import page.object.LoginPage;
import page.object.MainPage;
import page.object.RegisterPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends FactoryDriver {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
    @Step ("Успешная регистрация нового пользователя")
    public void shouldRegistrationSuccessful () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.openPage();
        mainPage.clickLoginButton();
        loginPage.clickRegistrationFormTransit();

        RegisterPage registerPage = new RegisterPage(driver);
        String email = "test" + System.currentTimeMillis() + "@yandex.ru";
        registerPage.register("Астэрия",email,"password1234");
        assertTrue("После регистрации должна открыться форма входа", loginPage.isLoginFormVisible());

    }
    @Test
    @Step ("Ошибка при пароле короче 6 символов")
    public void shouldShowErrorForShortPassword() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.openPage();
        mainPage.clickLoginButton();
        loginPage.clickRegistrationFormTransit();

        RegisterPage registerPage = new RegisterPage(driver);
        String email = "test" + System.currentTimeMillis() + "@yandex.ru";
        registerPage.register("Астероид",email,"1234");
        assertTrue("Должно отобразиться сообщение об ошибке",registerPage.isPasswordErrorVisible());
        assertEquals("Некорректный пароль",registerPage.getPasswordError());

    }
}
