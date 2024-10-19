import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteTodosTest extends BaseTest {

    @Test
    public void testDeleteTodoWithAuthorization() {
        given()
                .header("Authorization", "Basic YWRtaW46YWRtaW4=") // base64 encoded admin:admin
                .when()
                .delete("/1")
                .then()
                .statusCode(204); // No content after deletion
    }

    @Test
    public void testDeleteWithoutAuthorization() {
        given()
                .when()
                .delete("/1")
                .then()
                .statusCode(401); // Unauthorized request
    }
}
