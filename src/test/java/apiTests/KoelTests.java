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

    @Test
    public void getPlaylist(){
        Response response = given()
                .spec(requestSpec).log()
                .headers()
                //.all()
                .when()
                .get("https://qa.koel.app/api/playlist")
                .then().statusCode(200).extract().response();
        String responsBody = response.asString();
        System.out.println("Response Body"+responsBody);
    }

    @Test
    public void verifyPlaylistName(){
        Response response = given()
                .spec(requestSpec).log().headers()
                .baseUri("https://qa.koel.app")
                .basePath("/api/playlist")
                .get()
                .then().statusCode(200).extract().response();
        JsonPath json = response.jsonPath();
        Playlist[] playlists = response.as(Playlist[].class);
        Rule[] rules = json.getObject("rules[0]", Rule[].class);
        InnerRule innerRule = rules[0].getRules()[0];
        System.out.println("Model: "+innerRule.getModel());

        Assert.assertEquals(playlists[0].getName(), "SmartPlaylist");

    }

    @Test
    public void likeOrUnlikeSong(){
        Response response = given().params("song", "f25b26bc2963e2cb5f4a70511037c0a1")
                .spec(requestSpec).log()
                .headers()
                .when()
                .post("https://qa.koel.app/api/interaction/like")
                .then().statusCode(200).extract().response();
        String responseBody = response.asString();
        System.out.println("Response Body"+responseBody);
    }
    @Test
    public void likeOrUnlikeInvalidSong(){
        Response response = given().params("song", "as85dsa6d85dfefd5d15hgdd64j4ftt")
                .spec(requestSpec).log()
                .headers()
                .when()
                .post("https://qa.koel.app/api/interaction/like")
                .then().statusCode(404).extract().response();
        String responseBody = response.asString();
        System.out.println("Response Body"+responseBody);
    }




//    @Test
//    public void getKoelMainPage() {
//        Response response = given().
//                baseUri("https://qa.koel.app")
//                .when()
//                .get()
//                .then().statusCode(200).extract().response();
////        int statusCode = response.getStatusCode();
////        Assert.assertEquals(statusCode, 201, "Incorrect status code returned");
//    }



}
