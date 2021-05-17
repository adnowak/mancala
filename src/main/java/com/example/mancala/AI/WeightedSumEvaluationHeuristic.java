package com.example.mancala.AI;

import com.example.mancala.Models.Field;
import com.example.mancala.Models.Game;

public class WeightedSumEvaluationHeuristic extends BoardEvaluationHeuristic{
    private int scoreWeight;

    public WeightedSumEvaluationHeuristic(int scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    @Override
    public int getBoardEvaluation(Game game) {
        int player0Sum = 0;
        int player1Sum = 0;

        for (Field field: game.getPlayer0().getFields()) {
            player0Sum += field.getValue();
        }
        for (Field field: game.getPlayer1().getFields()) {
            player1Sum += field.getValue();
        }
        return scoreWeight*game.getScore()+player0Sum-player1Sum;
    }
}
