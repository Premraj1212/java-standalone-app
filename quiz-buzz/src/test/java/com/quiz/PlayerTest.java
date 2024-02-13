package com.quiz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * This class is created for unit testing for model class, i.e., Player (Player.java)
 *
 * @author  Premraj
 * @version 1.0
 * @since   2024-01-15
 **/
public class PlayerTest {

    static final Logger log = getLogger(lookup().lookupClass());
    Player player;
    String playerName = "Vengai";
    int points;

    /**
     * This method will execute before executing any Test.
     * This method will initialize the task object with void test parameters.
     */
    @BeforeEach
    void setup() {
        try {
            player = new Player(playerName,points);
        } catch (Exception e) {
            log.debug("There are few errors in the testing of Task class");
            log.debug("Following message could be helpful to identify the cause:");
            log.debug(e.getMessage());
        }
    }

    /**
     * This method will validate the player.getPlayerName() method for valid parameter
     */
    @Test
    void testValidPlayerName() {
        log.debug("Testing getPlayerName() method in {}", player.getPlayerName());

        // verify
        assertEquals(playerName,player.getPlayerName());
    }

    /**
     * This method will validate the player.setPlayerName() method for an empty string "" as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testEmptyTitle() {
        boolean success=false;

        try {
            player.setPlayerName("");
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        Assertions.assertTrue(success);
    }
    /**
     * This method will validate the player.setPlayerName() method for trimming an empty string "     " as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testTrimEmptyTitle() {
        boolean success=false;

        try {
            player.setPlayerName("           ");
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        assertEquals(true,success);
    }
    /**
     * This method will validate the player.setPlayerName() method for null as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testNullPlayerName() {
        boolean success=false;

        try {
            player.setPlayerName(null);
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        assertEquals(true,success);
    }
}
