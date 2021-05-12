package com.example.mancala.Models;

import com.example.mancala.AI.BoardEvaluationHeuristic;
import com.example.mancala.AI.ChoiceHeuristic;
import com.example.mancala.Exceptions.AllFieldsEmptyException;

import java.util.ArrayList;

public class Player {
    private ArrayList<Field> fields;
    private Well well;
    private ChoiceHeuristic choiceHeuristic;
    private BoardEvaluationHeuristic evaluationHeuristic;
    private boolean isMaximizing;
    private int movesDone;

    public Player(ChoiceHeuristic choiceHeuristic, BoardEvaluationHeuristic evaluationHeuristic) {
        fields = new ArrayList<>();
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        well = new Well();
        this.choiceHeuristic = choiceHeuristic;
        this.evaluationHeuristic = evaluationHeuristic;
        this.movesDone = 0;
    }

    public Player(Player base){
        fields = new ArrayList<>();
        for (Field field: base.fields) {
            fields.add(new Field(field));
        }
        well = new Well(base.getWell());
        this.choiceHeuristic = base.choiceHeuristic;
        this.evaluationHeuristic = base.evaluationHeuristic;
        this.isMaximizing = base.isMaximizing;
        this.movesDone = base.movesDone;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public ArrayList<Integer> getNonemptyIndexes(){
        ArrayList<Integer> nonemptyIndexes = new ArrayList<>();
        for (int i=0; i<fields.size(); i++) {
            if(fields.get(i).getValue() > 0){
                nonemptyIndexes.add(i);
            }
        }

        return nonemptyIndexes;
    }

    public Well getWell() {
        return well;
    }

    public int makeChoice(Game game) throws AllFieldsEmptyException {
        movesDone++;
        return choiceHeuristic.makeChoice(game, this);
    }

    public ChoiceHeuristic getChoiceHeuristic() {
        return choiceHeuristic;
    }

    public BoardEvaluationHeuristic getEvaluationHeuristic() {
        return evaluationHeuristic;
    }

    public boolean isMaximizing() {
        return isMaximizing;
    }

    public void setMaximizing(boolean maximizing) {
        isMaximizing = maximizing;
    }

    public int getMovesDone() {
        return movesDone;
    }
}
