package com.example.mancala.Exceptions;

public class AllFieldsEmptyException extends Exception{
    private int score;

    public AllFieldsEmptyException(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
