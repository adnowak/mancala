package com.example.mancala.AI;

import com.example.mancala.Models.BoardElement;
import com.example.mancala.Models.Game;

public abstract class BoardEvaluationHeuristic {
    public abstract int getBoardEvaluation(Game game);
}
