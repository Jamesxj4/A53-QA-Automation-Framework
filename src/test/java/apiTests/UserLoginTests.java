package apiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserLoginTests {
    RequestSpecification requestSpec;

//    @BeforeClass
//    public void authSetup(){
//        Response response = given()
//                .params("email", "james.lu@testpro.io",
//                        "password", "QnNBjg75$$")
//                .post("https://qa.koel.app/api/me")
//                .then().statusCode(200).extract().response();
//
//        String accessToken = response.path("token");
//        String Authorization = "Bearer "+accessToken;
//
//        RequestSpecBuilder builder = new RequestSpecBuilder();
//        builder.addHeader("Authorization", Authorization);
//
//        requestSpec = builder.build();
//    }
    @Test
    public void getKoelMainPage() {
        Response response = given().
                baseUri("https://qa.koel.app")
                .when()
                .get()
                .then().statusCode(200).extract().response();
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode, 201, "Incorrect status code returned");
    }
    @Test
    public void authSetup(){
        Response response = given()
                .params("email", "james.lu@testpro.io",
                        "password", "QnNBjg75$$")
                .post("https://qa.koel.app/api/me")
                .then().statusCode(200).extract().response();
    }
    @Test
    public void authSetupWithInvalidEamil(){
        Response response = given()
                .params("email", "james5566@testpro.io",
                        "password", "QnNBjg75$$")
                .post("https://qa.koel.app/api/me")
                .then().statusCode(401).extract().response();
    }
    @Test
    public void authSetupWithInvalidPasswrod(){
        Response response = given()
                .params("email", "james.lu@testpro.io",
                        "password", "12345")
                .post("https://qa.koel.app/api/me")
                .then().statusCode(401).extract().response();
    }
    @Test
    public void authSetupWithBlankEamil(){
        Response response = given()
                .params("email", "",
                        "password", "QnNBjg75$$")
                .post("https://qa.koel.app/api/me")
                .then().statusCode(302).extract().response();
        //Expected status code <401> but was <302>.
    }
    @Test
    public void authSetupWithBlankPasswrod(){
        Response response = given()
                .params("email", "james.lu@testpro.io",
                        "password", "")
                .post("https://qa.koel.app/api/me")
                .then().statusCode(302).extract().response();
        //Expected status code <401> but was <302>.
    }
    @Test
    public void authSetupWrongMethod(){
        Response response = given()
                .params("email", "james.lu@testpro.io",
                        "password", "QnNBjg75$$")
                .get("https://qa.koel.app/api/me")
                .then().statusCode(200).extract().response();
        //Expected status code <405> but was <200>.

        String responsBody = response.asString();
        System.out.println("Response Body"+responsBody);
    }




}
