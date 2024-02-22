package com.quiz;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizzerClientTest {

    @Test
    public void testGetNumberOfPlayers() {
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertEquals(2, QuizzerClient.getNumberOfPlayers(scanner));
    }

    @Test
    public void testGetPlayerNames() {
        String input = "Player1\nPlayer2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        List<Player> players = QuizzerClient.getPlayerNames(scanner, 2);
        assertEquals(2, players.size());
        assertEquals("Player1", players.get(0).getPlayerName());
        assertEquals("Player2", players.get(1).getPlayerName());
    }
}
