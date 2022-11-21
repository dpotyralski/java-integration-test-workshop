package pl.dpotyralski.javaintegrationtestintroduction;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=9090")
class _6_RestAssuredIntroIntegrationTest {

    @BeforeAll
    static void setup() {
        RestAssured.port = 9090;
    }

    @Test
    void shouldGetPointsFromMockedBean() {
        //then
        // @formatter:off
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("api/v1/movies/")
        .then()
            .statusCode(200)
            .body("movies.size()", is(4))
            .body("movies.title", hasItems("Matrix 11", "Spider Man", "Inception", "Out of Africa"));
        // @formatter:on
    }

    @Test
    void shouldGetOneById() {
        //then
        // @formatter:off
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("api/v1/movies/1")
        .then()
            .statusCode(200);
        // @formatter:on
    }

}
