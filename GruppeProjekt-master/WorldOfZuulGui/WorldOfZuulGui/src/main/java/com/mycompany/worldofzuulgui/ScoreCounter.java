package com.mycompany.worldofzuulgui;

import DataLayer.ScoreLogger;

public class ScoreCounter {

    private int score;
    private ScoreLogger logger;

    public ScoreCounter(ScoreLogger logger) {
        this.score = 20;
        this.logger = logger;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public void decreaseScore(int amount) {
        score -= amount;
    }

    public void printScore() {
        System.out.println("Score: " + score);
    }

    public int getScore() {
        return score;
    }

    public ScoreLogger getLogger() {
        return logger;
    }

}
