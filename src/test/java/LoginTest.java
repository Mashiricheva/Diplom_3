import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginTest  {

    private UserApi userApi;
    private String email;
    private String password;
    private String accessToken;

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Before
    public void registerUser() {
        userApi = new UserApi();
        email = "user" + System.currentTimeMillis() + "@yandex.ru";
        password = "password123";

        User user = new User(email, password, "Test");
        Response response = userApi.register(user);
        accessToken = response.jsonPath().getString("accessToken");
        assertNotNull("Токен должен быть получен", accessToken);

    }
    @After
    public void deleteUserViaApi() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    @Test
    //"Вход по кнопке «Войти в аккаунт» на главной"

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
    //"Вход через кнопку «Личный кабинет»"
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
    //"Вход через кнопку в форме регистрации"
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
    //"Вход через кнопку в форме восстановления пароля"

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
