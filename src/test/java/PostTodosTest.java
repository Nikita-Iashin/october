import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostTodosTest extends BaseTest {

    @BeforeEach
    public void setup() {
        // Call the parent setup
        super.setup();

        // Set the default parser in case no Content-Type is returned
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void testCreateTodo() {
        String newTodo = "{ \"id\": 9223372036854775804, \"text\": \"New Task\", \"completed\": false }";

        given()
                .contentType("application/json")
                .body(newTodo)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(201);
//                .body("text", equalTo("New Task"))
//                .body("completed", equalTo(false));
    }

    @Test
    public void testCreateTodoWithoutCompletedField() {
        String newTodo = "{ \"id\": 2, \"text\": \"New Task\" }";

        given()
                .body(newTodo)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body("completed", equalTo(false)); // Default value of completed should be false
    }
}
