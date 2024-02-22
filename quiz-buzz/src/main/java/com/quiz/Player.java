package com.quiz;

public class Player {
    // A String that holds the player name
    String playerName;
    // A int that holds the player points
    int points;

    /**
     * Creating an object of Player class
     * @param playerName A String that holds the player name
     * @param points A int that holds the player points.
     */
    public Player(String playerName, int points) {
        this.playerName = playerName;
        this.points = points;
    }
    /**
     * Creating an object of Player class
     * @param playerName A String that holds the player name
     */
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player() {
    }

    /**
     * A method to get the player name
     * @return a String containing the name of the player
     */
    public String getPlayerName() {
        return playerName;
    }
    /**
     * A method to get the player points
     * @return a int containing the points scored by the player
     */
    public int getPoints() {
        return points;
    }

    /**
     * A method to set the name of a Player object
     * @param playerName A String that holds player name and it cannot be empty or null.
     * @throws NullPointerException if title is null or empty string
     */
    public void setPlayerName(String playerName)throws NullPointerException {
        if (playerName.trim().equals("") || playerName == null) {
            throw new NullPointerException("REQUIRED: Player Name can not be empty.");
        }
        this.playerName = playerName.trim();
    }
    /**
     * A method to set the project name
     * @param points A int that holds the player points, and it could default value.
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
