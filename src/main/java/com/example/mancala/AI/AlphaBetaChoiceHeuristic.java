package com.example.mancala.AI;

import com.example.mancala.Exceptions.AllFieldsEmptyException;
import com.example.mancala.Models.Field;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;

import java.util.ArrayList;
import java.util.Random;

public class AlphaBetaChoiceHeuristic extends ChoiceHeuristic{
    private static int choice;
    private int depth;

    public AlphaBetaChoiceHeuristic(int depth) {
        this.depth = depth;
    }

    @Override
    public int makeChoice(Game game, Player player) throws AllFieldsEmptyException {
        alphabeta(game, depth, -10000, 10000, player.isMaximizing(), player.getEvaluationHeuristic());
        return choice;
    }

    private int alphabeta(Game game, int depth, int alpha, int beta, boolean maximizingPlayer, BoardEvaluationHeuristic evaluationHeuristic) throws AllFieldsEmptyException {
        int bestChoice = 0;
        if(depth == 0){
            return evaluationHeuristic.getBoardEvaluation(game);
        }

        if(maximizingPlayer){
            int maxEval = -10000;
            if(game.getPlayer0().getNonemptyIndexes().size() == 0) throw new AllFieldsEmptyException(evaluationHeuristic.getBoardEvaluation(game));
            bestChoice = game.getPlayer0().getNonemptyIndexes().get(0);

            for (Integer fieldIndex: game.getPlayer0().getNonemptyIndexes()) {
                Game branch = new Game(game);
                branch.player0Play(fieldIndex);
                try {
                    int recentEval = alphabeta(branch, depth - 1, alpha, beta, false, evaluationHeuristic);
                    if(recentEval > maxEval){
                        maxEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                    if(recentEval > alpha) alpha = recentEval;
                    if(alpha>beta) break;
                }
                catch (AllFieldsEmptyException branchOver){
                    int recentEval = branchOver.getScore();
                    if(recentEval > maxEval){
                        maxEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                    if(recentEval > alpha) alpha = recentEval;
                    if(alpha>beta) break;
                    continue;
                }
            }
            AlphaBetaChoiceHeuristic.choice = bestChoice;
            return maxEval;
        }
        else {
            int minEval = 10000;
            if(game.getPlayer1().getNonemptyIndexes().size() == 0) throw new AllFieldsEmptyException(evaluationHeuristic.getBoardEvaluation(game));
            bestChoice = game.getPlayer1().getNonemptyIndexes().get(0);

            for (Integer fieldIndex: game.getPlayer1().getNonemptyIndexes()) {
                Game branch = new Game(game);
                branch.player1Play(fieldIndex);
                try{
                    int recentEval = alphabeta(branch, depth - 1, alpha, beta, true, evaluationHeuristic);
                    if(recentEval < minEval){
                        minEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                    if(recentEval < beta) beta = recentEval;
                    if(alpha>beta) break;
                }
                catch (AllFieldsEmptyException branchOver){
                    int recentEval = branchOver.getScore();
                    if(recentEval < minEval){
                        minEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                    if(recentEval < beta) beta = recentEval;
                    if(alpha>beta) break;
                    continue;
                }
            }
            AlphaBetaChoiceHeuristic.choice = bestChoice;
            return minEval;
        }
    }
}
