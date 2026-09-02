package page.object;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api";
    private static final String REGISTER = "/auth/register";
    private static final String LOGIN = "/auth/login";
    private static final String USER = "/auth/user";

    public Response register(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(BASE_URL + REGISTER);
    }

    public Response login(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(BASE_URL + LOGIN);
    }

    public Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + USER);
    }
}
