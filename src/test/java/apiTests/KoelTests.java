package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.InnerRule;
import models.Playlist;
import models.Rule;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

public class KoelTests {

    RequestSpecification requestSpec;

    @BeforeClass
    public void authSetup(){
        Response response = given()
                .params("email", "james.lu@testpro.io",
                        "password", "QnNBjg75$$")
                .post("https://qa.koel.app/api/me")
                .then().statusCode(200).extract().response();

        String accessToken = response.path("token");
        String Authorization = "Bearer "+accessToken;

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Authorization", Authorization);

        requestSpec = builder.build();
    }

/////likeOrUnlikeSong////////////////////////////////////////
    @Test
    public void likeOrUnlikeSong(){
        Response response = given().params("song", "f25b26bc2963e2cb5f4a70511037c0a1")
                .spec(requestSpec).log()
                .headers()
                .when()
                .post("https://qa.koel.app/api/interaction/like")
                .then().statusCode(200).extract().response();
        String responseBody = response.asString();
        System.out.println("Response Body: "+responseBody);
    }
    @Test
    public void likeOrUnlikeSongWithWrongMethod(){
        Response response = given().params("song", "f25b26bc2963e2cb5f4a70511037c0a1")
                .spec(requestSpec).log()
                .headers()
                .when()
                .get("https://qa.koel.app/api/interaction/like")
                .then().statusCode(405).extract().response();
        String responseBody = response.asString();
        System.out.println("Response Body: "+responseBody);
    }
    @Test
    public void likeOrUnlikeInvalidSong(){
        Response response = given().params("song", "as85dsa6d85dfefd5d15hgdd64j4ftt")
                .spec(requestSpec).log()
                .headers()
                .when()
                .post("https://qa.koel.app/api/interaction/like")
                .then().statusCode(404).extract().response();
        //Expected status code <404> but was <500>.
        String responseBody = response.asString();
        System.out.println("Response Body: "+responseBody);
    }

/////Like multiple songs////////////////////////////////////////
    @Test
    public void likeMultipleSongs(){
        String[] multipleSongs= {"f25b26bc2963e2cb5f4a70511037c0a1","30c13696a68d7794b5468ee3bc9a3fa6"};
        Response response = given()
//                .params("songs", "f25b26bc2963e2cb5f4a70511037c0a1",
//                        "songs", "30c13696a68d7794b5468ee3bc9a3fa6")
//                .params("songs", Arrays.toString(multipleSongs))
                .params("songs","[f25b26bc2963e2cb5f4a70511037c0a1]")
//                .params("songs", "{\"f25b26bc2963e2cb5f4a70511037c0a1\"}")
//                .params("songs", "f25b26bc2963e2cb5f4a70511037c0a1").params("songs", "30c13696a68d7794b5468ee3bc9a3fa6")
                .spec(requestSpec)
                .log()
                .headers()
                .when()
                .post("https://qa.koel.app/api/interaction/batch/like")
                .then().statusCode(200).extract().response();
        String responseBody = response.asString();
        System.out.println("Response Body: "+responseBody);
}

}
