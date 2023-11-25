import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

public class UserEndPoint {
    public Response createUser(User user) {
        return  given()
                .body(user)
                .when()
                .post(Config.CREATE_USER)
                .then().extract().response();
    }


    public Response deleteUser(String userName) {
        return given()
                .pathParam("userName", userName)
                .when()
                .delete(Config.DELETE_USER)
                .then().extract().response();
    }

    public Response getUser(String userName) {
        return given()
                .pathParam("userName", userName)
                .when()
                .get(Config.GET_USER)
                .then().extract().response();
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(Config.PETSTORE_BASE_URL)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }
}
