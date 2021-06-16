package tests;

import base.ApiTestCase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.glassfish.gmbal.Description;
import org.testng.annotations.Test;

public class ApiTest extends ApiTestCase {

    @Description("Verify that the API starts with an empty store")
    @Test
    public void verifyThatItStartsWithAnEmptyStore() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        Assert.assertNull(response.getBody());
    }

    @Description("Verify that author is required field")
    @Test
    public void verifyThatAuthorIsRequiredField() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .params("title","testTitle")
                .when()
                .put(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .response();
        Assert.assertEquals("Field 'author' is required", response.getBody().jsonPath().getString("error"));
    }

    @Description("Verify that title is required field")
    @Test
    public void verifyThatTitleIsRequiredField() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .params("author","testAuthor")
                .when()
                .put(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .response();
        Assert.assertEquals("Field 'title' is required", response.getBody().jsonPath().getString("error"));
    }

    @Description("Verify that author can not be empty")
    @Test
    public void verifyThatAuthorCannotBeEmpty() {
        Response response = RestAssured
                .given()
                .params("author", "")
                .params("title", "testTitle")
                .when()
                .put(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .response();
        Assert.assertEquals("Field 'author' cannot be empty", response.getBody().jsonPath().getString("error"));
    }

    @Description("Verify that author can not be empty")
    @Test
    public void verifyThatTitleCannotBeEmpty() {
        Response response = RestAssured
                .given()
                .params("author", "testAuthor")
                .params("title", "")
                .when()
                .put(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .response();
        Assert.assertEquals("Field 'title' cannot be empty", response.getBody().jsonPath().getString("error"));
    }

    @Description("Verify that the id field is read−only")
    @Test
    public void verifyThatTheIdFieldIsReadOnly() {
        Response response = RestAssured
                .given()
                .params("id", "1")
                .params("author", "testAuthor")
                .params("title", "testTitle")
                .when()
                .put(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .response();
    }

    @Description("Verify that you can create a new book via PUT")
    @Test
    public void  verifyThatCreateNewBook() {
        Response response = RestAssured
                .given()
                .params("author", "John Smith")
                .params("title", "SRE 101")
                .when()
                .put(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        Assert.assertEquals(1, response.getBody().jsonPath().getInt("id"));
        Assert.assertEquals("John Smith", response.getBody().jsonPath().getString("author"));
        Assert.assertEquals("SRE 101", response.getBody().jsonPath().getString("title"));
    }

    @Description("Verify that you cannot create a duplicate book")
    @Test
    public void verifyThatCannotCreateDuplicateBook() {
        Response response = RestAssured
                .given()
                .params("author", "John Smith")
                .params("title", "SRE 101")
                .when()
                .put(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        Assert.assertEquals(1, response.getBody().jsonPath().getInt("id"));
        Assert.assertEquals("John Smith", response.getBody().jsonPath().getString("author"));
        Assert.assertEquals("SRE 101", response.getBody().jsonPath().getString("title"));

        Response duplicatedResponse = RestAssured
                .given()
                .params("author", "John Smith")
                .params("title", "SRE 101")
                .when()
                .put(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .response();
        Assert.assertEquals(" Another book with similar \n" +
                "title and author already exists.", duplicatedResponse.getBody().jsonPath().getString("error"));
    }
}
