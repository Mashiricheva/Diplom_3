import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.*;

import static org.junit.Assert.assertTrue;

public class LoginTest extends FactoryDriver {
    private String email;
    private final String password = "password123";

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Before
    public void registerUser() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();                     // открыли главную
        mainPage.clickLoginButton();              // перешли на страницу входа

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationFormTransit(); // перешли на регистрацию

        RegisterPage registerPage = new RegisterPage(driver);
        email = "user" + System.currentTimeMillis() + "@yandex.ru";
        registerPage.register("Test User", email, password);

        // После регистрации откроется форма входа, возвращаемся на главную
        mainPage.openPage();
    }

    @Test
    @Step ("Вход по кнопке «Войти в аккаунт» на главной")

    public void loginMainPageButton () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.openPage();
        mainPage.clickLoginButton();

        loginPage.login(email,password);
        assertTrue("Вход должен быть успешным", mainPage.isLoginSuccessful());

    }

    @Test
    @Step("Вход через кнопку «Личный кабинет»")
    public void loginPersonalAccount () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.openPage();
        mainPage.clickPersonalAccountButton();

        loginPage.login(email,password);
        assertTrue("Вход должен быть успешным", mainPage.isLoginSuccessful());
    }

    @Test
    @Step("Вход через кнопку в форме регистрации")
    public void loginRegistrationForm () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.openPage();
        mainPage.clickLoginButton();
        loginPage.clickRegistrationFormTransit();
        registerPage.clickLoginLink();
        loginPage.login(email,password);
        assertTrue("Вход должен быть успешным", mainPage.isLoginSuccessful());

    }
    @Test
    @Step("Вход через кнопку в форме восстановления пароля")

    public void loginForgotPasswordForm () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        mainPage.openPage();
        mainPage.clickLoginButton();
        loginPage.clickPasswordRecoveryFormTransition();
        forgotPasswordPage.clickLoginLimkForgotForm();
        loginPage.login(email,password);
        assertTrue("Вход должен быть успешным", mainPage.isLoginSuccessful());
    }
}
