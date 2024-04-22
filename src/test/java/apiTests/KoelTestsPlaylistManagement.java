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

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;

public class KoelTestsPlaylistManagement {
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
        if(responsBody.contains("id")){
            System.out.println("Response body contents check: id");
        }
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
    public void verifyPlaylistNameSol(){
        // Sending a request and obtaining a response
        Response response = given()
                .spec(requestSpec)
                .log().headers()
                .baseUri("https://qa.koel.app")
                .basePath("/api/playlist")
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Checking if playlists are present in the response
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        Assert.assertTrue(responseBody.contains("SmartPlaylist"), "No playlists found in the response.");

        // Extracting playlist names using JsonPath
        JsonPath json = response.jsonPath();
        ArrayList<String> playlistNames = json.get("name");
        System.out.println("Playlist Names: " + playlistNames);

        // Checking if the extracted playlist names contain the expected name
        boolean found = false;
        for (String playlistName : playlistNames) {
            if (playlistName.equals("SmartPlaylist")) {
                found = true;
                break;
            }
        }

        // Asserting that a matching playlist name is found
        System.out.println("Assert: Playlist with name SmartPlaylist found: " + found);
        Assert.assertTrue(found, "Playlist with name SmartPlaylist not found.");
    }

    //////////////////////////////////
    @Test
    public void createPlaylist(){
        Response response = given()
                .spec(requestSpec).log()
                .headers()
                //.all()
                .when()
                .params("name", "API_autoTest01")
                .post("https://qa.koel.app/api/playlist")
                .then().statusCode(200).extract().response();
        String responsBody = response.asString();
        System.out.println("Server response with the playlist name: "+responsBody);
        if(responsBody.contains("id")){
          System.out.println("Response body contents check: id");
        }
    }
    @Test
    public void createPlaylistBlankName(){
        Response response = given()
                .spec(requestSpec).log()
                .headers()
                //.all()
                .when()
                .params("name", "")
                .post("https://qa.koel.app/api/playlist")
                .then().statusCode(302).extract().response();
        //Expected status code <200> but was <302>.
        String responsBody = response.asString();
        System.out.println("Server response with the playlist name: "+responsBody);
        if(responsBody.contains("id")){
            System.out.println("Response body contents check: id");
        }
    }
    @Test
    public void createPlaylistWrongDataTypeInteger(){
        Response response = given()
                .spec(requestSpec).log()
                .headers()
                //.all()
                .when()
                .params("name", 12345)
                .post("https://qa.koel.app/api/playlist")
                .then().statusCode(200).extract().response();
        String responsBody = response.asString();
        System.out.println("Server response with the playlist name: "+responsBody);
        if(responsBody.contains("id")){
            System.out.println("Response body contents check: id");
        }
    }
}
