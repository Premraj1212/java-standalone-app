package com.quiz;

import com.github.tomaslanger.chalk.Chalk;

import java.util.*;
import java.util.stream.Collectors;

public class QuizzerClient {

    public static void main(String[] args) {
        System.out.println(Chalk.on("Welcome to the game !!!!!").inverse().bold().underline().bgCyan());

        Scanner clientScan = new Scanner(System.in);
        System.out.println();
        System.out.println(Chalk.on("Are you Ready to begin?, Press  \"ENTER\" to begin").inverse());
        clientScan.nextLine();

        List<Player> playerLst = new ArrayList<>();

        System.out.println("Enter number of player want to play:");

        int numPlayers = Integer.parseInt(clientScan.nextLine());

        for (int i=0; i<numPlayers;i++){
            Player player = new Player();
            System.out.println("Enter name of player: "+ (i+1));
            String playerName =  clientScan.nextLine();
            player.setPlayerName(playerName);
            playerLst.add(player);
        }

        Random random = new Random();
        int pickNum = random.nextInt(100)+1;


        int numberOfRetries = 2;
        for(Player player : playerLst){
            int points = 0;
            System.out.println("Hi, "+ player.getPlayerName() +" Enter your guess between [1-100]: ");



            for (int i=0; i<numberOfRetries; i++){

                int guessNum =  clientScan.nextInt();
                if(guessNum == pickNum){
                    player.setPoints(points+1);
                    System.out.println("Gottcha You made it !!!!!!!!");
                    break;
                }else if (i<numberOfRetries-1){
                    System.out.println("You are left with "+(numberOfRetries-1)+" Attempt");
                }else {
                    System.out.println("You played really great.... better luck next time");
                }

            }

        }

        int maxPoints = playerLst.stream()
                        .max(Comparator.comparing(Player::getPoints).reversed())
                .map(a -> a.getPoints())
                        .get();

        System.out.println("max points scored "+maxPoints);

        if(maxPoints ==0){
            System.out.println();
            System.out.println();
            System.out.println(Chalk.on("Unfortunately there is no winner in this game :(").bgBlue());
        } else {
            String winner = playerLst.stream()
                    .filter(a-> a.getPoints() == maxPoints)
                    .map(Player::getPlayerName)
                    .collect(Collectors.joining(", "));
            System.out.println();
            System.out.println();
            System.out.println(Chalk.on("The winner is "+ winner +" !!!").inverse().bgMagenta());
        }


        System.out.println();
        System.out.println();
        System.out.println(Chalk.on("END of GAME!!!").bold().bgMagenta());

    }
}
