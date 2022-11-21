package pl.dpotyralski.javaintegrationtestintroduction;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static pl.dpotyralski.javaintegrationtestintroduction.SystemTestEnvironment.*;

class MoviesBlackBoxTests {

    @BeforeAll
    static void setup() {
        start();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldHandleMovieHappyScenario() {
        //given
        createMovieRequest("Matrix", "REGULAR");
        createMovieRequest("Spider-man", "NEW_RELEASE");

        //then
        // @formatter:off
        given()
            .baseUri(serviceUrl())
            .contentType(ContentType.JSON)
        .when()
            .get("api/v1/movies/")
        .then()
            .statusCode(200)
            .body("movies.size()", is(2))
            .body("movies.title", hasItems("Matrix", "Spider-man"));
        // @formatter:on
    }

    void createMovieRequest(String title, String type) {
        // @formatter:on
        given()
                .baseUri(serviceUrl())
                .contentType(ContentType.JSON)
                .body("""
                                {
                                    "title": "%s",
                                    "type": "%s"
                                }
                        """.formatted(title, type))
                .when()
                .post("/api/v1/movies");
        // @formatter:on
    }

}

