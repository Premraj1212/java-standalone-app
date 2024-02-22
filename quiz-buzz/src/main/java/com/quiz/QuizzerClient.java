package com.quiz;

import com.github.tomaslanger.chalk.Chalk;

import java.util.*;
import java.util.stream.Collectors;

public class QuizzerClient {

    public static void main(String[] args) {
        Scanner clientScan = new Scanner(System.in);
        System.out.println(Chalk.on("Welcome to the game !!!!!").inverse().bold().underline().bgCyan());
        System.out.println();
        System.out.println(Chalk.on("Are you Ready to begin?, Press  \"ENTER\" to begin").inverse());
        clientScan.nextLine();

        int numPlayers = getNumberOfPlayers(clientScan);
        List<Player> players = getPlayerNames(clientScan, numPlayers);

        int maxPoints = playGame(players);

        printResults(players, maxPoints);
    }

    static int getNumberOfPlayers(Scanner scanner) {
        System.out.println("Enter number of players:");
        return Integer.parseInt(scanner.nextLine());
    }

    static List<Player> getPlayerNames(Scanner scanner, int numPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter name of player: " + (i + 1));
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
        }
        return players;
    }

    private static int playGame(List<Player> players) {
        Random random = new Random();
        int pickNum = random.nextInt(100) + 1;
        int numberOfRetries = 2;

        for (Player player : players) {
            System.out.println("Hi, " + player.getPlayerName() + ", enter your guess between [1-100]: ");

            int points = 0;
            for (int i = 0; i < numberOfRetries; i++) {
                Scanner scanner = new Scanner(System.in);
                int guessNum = scanner.nextInt();
                if (guessNum == pickNum) {
                    player.setPoints(points + 1);
                    System.out.println("Gotcha! You made it!");
                    break;
                } else if (i < numberOfRetries - 1) {
                    System.out.println("You are left with " + (numberOfRetries - 1 - i) + " attempts");
                } else {
                    System.out.println("You played really great... better luck next time");
                }
            }
        }

        return players.stream()
                .mapToInt(Player::getPoints)
                .max()
                .orElse(0);
    }

    static void printResults(List<Player> players, int maxPoints) {
        System.out.println("Max points scored: " + maxPoints);
        if (maxPoints == 0) {
            System.out.println(Chalk.on("Unfortunately, there is no winner in this game :(").bgBlue());
        } else {
            String winner = players.stream()
                    .filter(player -> player.getPoints() == maxPoints)
                    .map(Player::getPlayerName)
                    .collect(Collectors.joining(", "));
            System.out.println(Chalk.on("The winner is " + winner + "!!!").inverse().bgMagenta());
        }
        System.out.println(Chalk.on("END of GAME!!!").bold().bgMagenta());
    }
}

