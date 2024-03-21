package apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class KoelTests {

    @Test
    public void getKoelMainPage() {
        Response response = given().
                baseUri("https://qa.koel.app")
                .when()
                .get()
                .then().extract().response();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Incorrect status code returned");
    }
}
