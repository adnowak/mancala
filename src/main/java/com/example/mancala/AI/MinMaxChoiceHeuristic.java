package com.example.mancala.AI;

import com.example.mancala.Exceptions.AllFieldsEmptyException;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;

public class MinMaxChoiceHeuristic extends ChoiceHeuristic{
    private static int choice;
    private int depth;

    public MinMaxChoiceHeuristic(int depth) {
        this.depth = depth;
    }

    @Override
    public int makeChoice(Game game, Player player) throws AllFieldsEmptyException {
        minmax(game, depth, player.isMaximizing(), player.getEvaluationHeuristic());
        return choice;
    }

    private int minmax(Game game, int depth, boolean maximizingPlayer, BoardEvaluationHeuristic evaluationHeuristic) throws AllFieldsEmptyException {
        if(depth == 0){
            return evaluationHeuristic.getBoardEvaluation(game);
        }
        int bestChoice;

        if(maximizingPlayer){
            int maxEval = -10000;
            if(game.getPlayer0().getNonemptyIndexes().size() == 0) throw new AllFieldsEmptyException(evaluationHeuristic.getBoardEvaluation(game));
            bestChoice = game.getPlayer0().getNonemptyIndexes().get(0);

            for (Integer fieldIndex: game.getPlayer0().getNonemptyIndexes()) {
                Game branch = new Game(game);
                branch.player0Play(fieldIndex);
                try {
                    int recentEval = minmax(branch, depth - 1, false, evaluationHeuristic);
                    if(recentEval > maxEval){
                        maxEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                }
                catch (AllFieldsEmptyException branchOver){
                    int recentEval = branchOver.getScore();
                    if(recentEval > maxEval){
                        maxEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                    continue;
                }
            }

            MinMaxChoiceHeuristic.choice = bestChoice;

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
                    int recentEval = minmax(branch, depth - 1, true, evaluationHeuristic);
                    if(recentEval < minEval){
                        minEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                }
                catch (AllFieldsEmptyException branchOver){
                    int recentEval = branchOver.getScore();
                    if(recentEval < minEval) {
                        minEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                    continue;
                }
            }
            MinMaxChoiceHeuristic.choice = bestChoice;

            return minEval;
        }
    }
}
