import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutTodosTest extends BaseTest {

    @Test
    public void testUpdateTodo() {
        String updatedTodo = "{ \"id\": 1, \"text\": \"Updated Task\", \"completed\": true }";

        given()
                .body(updatedTodo)
                .when()
                .put("/1")
                .then()
                .statusCode(200)
                .body("text", equalTo("Updated Task"))
                .body("completed", equalTo(true));
    }

    @Test
    public void testUpdateNonExistentTodo() {
        String updatedTodo = "{ \"id\": 999, \"text\": \"Non-existent\", \"completed\": false }";

        given()
                .body(updatedTodo)
                .when()
                .put("/999")
                .then()
                .statusCode(404);
    }
}
