package com.example.mancala.AI;

import com.example.mancala.Exceptions.AllFieldsEmptyException;
import com.example.mancala.Models.Field;
import com.example.mancala.Models.Game;
import com.example.mancala.Models.Player;

import java.util.ArrayList;
import java.util.Random;

public class RandomChoiceHeuristic extends ChoiceHeuristic{
    @Override
    public int makeChoice(Game game, Player player) throws AllFieldsEmptyException {
        ArrayList<Field> nonemptyFields = new ArrayList<>();
        for (Field field: player.getFields()) {
            if(field.getValue() > 0){
                nonemptyFields.add(field);
            }
        }

        if(nonemptyFields.size()==0){
            throw new AllFieldsEmptyException(game.getScore());
        }

        int index = new Random().nextInt(nonemptyFields.size());
        for(int i=0; i<player.getFields().size(); i++){
            if(player.getFields().get(i).getValue() > 0){
                if(index == 0){
                    return i;
                }
                index--;
            }
        }
        return 0;
    }
}
