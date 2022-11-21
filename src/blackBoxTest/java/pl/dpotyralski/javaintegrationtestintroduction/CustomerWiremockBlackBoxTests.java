package pl.dpotyralski.javaintegrationtestintroduction;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static pl.dpotyralski.javaintegrationtestintroduction.SystemTestEnvironment.serviceUrl;
import static pl.dpotyralski.javaintegrationtestintroduction.SystemTestEnvironment.start;

class CustomerWiremockBlackBoxTests {

    @BeforeAll
    static void setup() {
        start();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldGetWireMockCustomer() {
        //then
        // @formatter:off
        given()
                .baseUri(serviceUrl())
                .contentType(ContentType.JSON)
                .when()
                .get("api/v1/customers/1")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("username", is("Mr. Bean"));
        // @formatter:on
    }


}

