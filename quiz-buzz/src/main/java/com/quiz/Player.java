package com.quiz;

public class Player {
    String playerName;
    int points;

    public Player(String playerName, int points) {
        this.playerName = playerName;
        this.points = points;
    }

    public Player() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPoints() {
        return points;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
