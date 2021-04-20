package com.example.mancala.AI;

import com.example.mancala.Exceptions.AllFieldsEmptyException;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;

public abstract class ChoiceHeuristic {
    public abstract int makeChoice(Game game, Player player) throws AllFieldsEmptyException;
}
