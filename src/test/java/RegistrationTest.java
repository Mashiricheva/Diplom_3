import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest  {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    private UserApi userApi;
    private String registeredEmail;
    private String registeredPassword;


    @Before
    public void setUpApi() {
        userApi = new UserApi();
    }
    @Test
    //"Успешная регистрация нового пользователя"
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
    //"Ошибка при пароле короче 6 символов"
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
    @After
    public void deleteRegisteredUser() {
        // Удаляем пользователя, если он был успешно создан в тесте регистрации
        if (registeredEmail != null && registeredPassword != null) {
            // Логинимся, чтобы получить accessToken
            User user = new User(registeredEmail, registeredPassword, "Астэрия");
            Response loginResponse = userApi.login(user);
            String token = loginResponse.jsonPath().getString("accessToken");
            if (token != null) {
                userApi.deleteUser(token);
            }
        }
    }
}
