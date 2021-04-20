package com.example.mancala.AI;

import com.example.mancala.Exceptions.AllFieldsEmptyException;
import com.example.mancala.Models.Field;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;

import java.util.ArrayList;
import java.util.Random;

public class MinMaxChoiceHeuristic extends ChoiceHeuristic{
    private static int choice;
    private int depth;

    public MinMaxChoiceHeuristic(int depth) {
        this.depth = depth;
    }

    @Override
    public int makeChoice(Game game, Player player) throws AllFieldsEmptyException {
        minmax(game, depth, player.isMaximizing());
        return choice;
    }

    private int minmax(Game game, int depth, boolean maximizingPlayer) throws AllFieldsEmptyException {
        int bestChoice = 0;
        if(depth == 0){
            return game.getScore();
        }

        if(maximizingPlayer){
            int maxEval = -1000;
            if(game.getPlayer0().getNonemptyIndexes().size() == 0) throw new AllFieldsEmptyException();
            for (Integer fieldIndex: game.getPlayer0().getNonemptyIndexes()) {
                Game branch = new Game(game);
                branch.player0Play(fieldIndex);
                try {
                    int recentEval = minmax(branch, depth - 1, false);
                    if(recentEval > maxEval){
                        maxEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                }
                catch (AllFieldsEmptyException branchOver){
                    continue;
                }
            }
            MinMaxChoiceHeuristic.choice = bestChoice;
            return maxEval;
        }
        else {
            int minEval = 1000;
            if(game.getPlayer1().getNonemptyIndexes().size() == 0) throw new AllFieldsEmptyException();
            for (Integer fieldIndex: game.getPlayer1().getNonemptyIndexes()) {
                Game branch = new Game(game);
                branch.player1Play(fieldIndex);
                try{
                    int recentEval = minmax(branch, depth - 1, true);
                    if(recentEval < minEval){
                        minEval = recentEval;
                        bestChoice = fieldIndex;
                    }
                }
                catch (AllFieldsEmptyException branchOver){
                    continue;
                }
            }
            MinMaxChoiceHeuristic.choice = bestChoice;
            return minEval;
        }
    }
}
