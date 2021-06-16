package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class ApiTestCase {

    public static String API_ROOT = "http://localhost:3000";
    public static String END_POINT = "/api/books/";

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = API_ROOT;
    }

    @AfterTest
    public void tearDown() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .delete(END_POINT)
                .then()
                .extract()
                .response();
    }
}
