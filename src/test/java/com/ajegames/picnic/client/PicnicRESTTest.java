package com.ajegames.picnic.client;

import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * This is for testing the REST interface to the Player resource.
 */
public class PicnicRESTTest {

    private static String endpoint = "/service/picnic";

    @Test
    public void getPlayer() {
        String playerName = "BubbaGump";
        String playerKey = given().param("playerName", playerName)
                .when().post(endpoint + "/player")
                .then().body("name", equalTo(playerName))
                .extract().path("key");
        System.out.println("Created player with key: " + playerKey);

        get(endpoint + "/player/" + playerKey).then().body("name", equalTo(playerName));
    }
}
