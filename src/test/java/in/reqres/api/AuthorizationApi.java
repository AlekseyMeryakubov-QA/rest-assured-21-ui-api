package in.reqres.api;

import in.reqres.models.CredentialsModel;
import in.reqres.models.LoginResponseModel;
import io.qameta.allure.Step;

import static in.reqres.specs.LoginSpec.successLoginRequestSpec;
import static in.reqres.specs.LoginSpec.successLoginResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    @Step("Запрос на авторизацию")
    public LoginResponseModel login(CredentialsModel credentials) {
        return given(successLoginRequestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(successLoginResponseSpec)
                .extract().as(LoginResponseModel.class);
    }
}