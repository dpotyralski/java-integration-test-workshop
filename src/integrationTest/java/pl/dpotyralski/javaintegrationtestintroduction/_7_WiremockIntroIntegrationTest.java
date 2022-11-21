package pl.dpotyralski.javaintegrationtestintroduction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

//@WireMockTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=8081")
@ActiveProfiles("external")
class _7_WiremockIntroIntegrationTest {

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance().options(wireMockConfig().port(8083)).build();

    @BeforeAll
    static void setup() {
        RestAssured.port = 8081;
    }

    @Test
    void shouldGetOneById() throws JsonProcessingException {
        //given
        stubTopGunMovie();

        //then
        // @formatter:off
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("api/v1/customers/1")
        .then()
            .statusCode(200)
        .body("id", is(128))
        .body("username", is("Jon Snow"));
        // @formatter:on
    }

    private void stubTopGunMovie() {
        wireMockServer.stubFor(get(WireMock.urlPathEqualTo("/api/external-catalog/customers/1"))
                .willReturn(ok().
                        withHeader("Content-Type", "application/json")
                        .withBody("""
                                {"id":128,"username":"Jon Snow", "bonusPoints":  9}
                                """)));
    }

}
