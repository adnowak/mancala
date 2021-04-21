package com.example.mancala.Models;

import com.example.mancala.AI.ChoiceHeuristic;
import com.example.mancala.Exceptions.AllFieldsEmptyException;

import java.util.ArrayList;

public class Player {
    private ArrayList<Field> fields;
    private Well well;
    private ChoiceHeuristic choiceHeuristic;
    private boolean isMaximizing;

    public Player(ChoiceHeuristic choiceHeuristic) {
        fields = new ArrayList<>();
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        well = new Well();
        this.choiceHeuristic = choiceHeuristic;
    }

    public Player(Player base){
        fields = new ArrayList<>();
        for (Field field: base.fields) {
            fields.add(new Field(field));
        }
        well = new Well(base.getWell());
        this.choiceHeuristic = base.choiceHeuristic;
        this.isMaximizing = base.isMaximizing;
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
        return choiceHeuristic.makeChoice(game, this);
    }

    public ChoiceHeuristic getChoiceHeuristic() {
        return choiceHeuristic;
    }

    public boolean isMaximizing() {
        return isMaximizing;
    }

    public void setMaximizing(boolean maximizing) {
        isMaximizing = maximizing;
    }
}
