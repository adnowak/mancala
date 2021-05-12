package com.example.mancala.AI;

import com.example.mancala.Models.Game;
import org.springframework.web.bind.annotation.GetMapping;

public class BasicEvaluationHeuristic extends BoardEvaluationHeuristic{
    @Override
    public int getBoardEvaluation(Game game) {
        return game.getScore();
    }
}
