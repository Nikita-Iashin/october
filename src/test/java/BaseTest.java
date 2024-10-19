import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected RequestSpecification request;
    protected ResponseSpecification response;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/todos";
        request = RestAssured.given().log().all().contentType("application/json");
        response = RestAssured.given().log().all().response();
    }
}
