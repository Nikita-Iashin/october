import java.util.UUID;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetTodosTest extends BaseTest {

    @BeforeEach
    public void populateTodos() {
        // Dynamically generate unique IDs
        long id1 = System.currentTimeMillis();
        long id2 = System.currentTimeMillis() + 1;

        String newTodo1 = "{ \"id\": " + id1 + ", \"text\": \"Task 1\", \"completed\": false }";
        String newTodo2 = "{ \"id\": " + id2 + ", \"text\": \"Task 2\", \"completed\": true }";

        // Create TODOs with unique IDs
        request.body(newTodo1).post().then().statusCode(201);
        request.body(newTodo2).post().then().statusCode(201);
    }

    @Test
    public void testGetAllTodos() {
        Response response = request.get();
        response.then()
                .statusCode(200)
                .body("$", not(empty()));  // Assert the list is not empty
    }

    @Test
    public void testGetTodosWithOffsetAndLimit() {
        request.queryParam("offset", 1)
                .queryParam("limit", 5)
                .get()
                .then()
                .statusCode(200)
                .body("$.size()", lessThanOrEqualTo(5));  // Assert at most 5 items are returned
    }
}
