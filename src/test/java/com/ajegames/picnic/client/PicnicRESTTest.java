package com.ajegames.picnic.client;

import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * This is for testing the REST interface to the Player resource.
 */
public class PicnicRESTTest {

    private static String endpoint = "/service/picnic";

    /**
     * Tests POST endpoint/player [playerName]
     */
    @Test
    public void testCreatePlayer() {

        String player1 = createPlayer("Ali Baba");
        String player2 = createPlayer("Bubba Gump");
        String player3 = createPlayer("Colin McCreavy");

        assert !player1.equals(player2);
        assert !player1.equals(player3);
        assert !player2.equals(player3);

        // TODO try to create players with names that could cause problems -- might assemble a set of security hacks
    }

    /**
     * Tests GET endpoint/player/{playerKey}
     */
    @Test
    public void testGetPlayer() {
        String playerName = "Zak Zepher";
        String playerKey = createPlayer(playerName);
        get(endpoint + "/player/" + playerKey).then().body("name", equalTo(playerName));
    }

    protected String createPlayer(String playerName) {
        String playerKey = given().param("playerName", playerName)
                .when().post(endpoint + "/player")
                .then().body("name", equalTo(playerName))
                .extract().path("key");
        // TODO tap into logger
        System.out.println("Created player with key: " + playerKey);
        return playerKey;
    }
}
