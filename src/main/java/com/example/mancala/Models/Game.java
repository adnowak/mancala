package com.example.mancala.Models;

import com.example.mancala.Exceptions.AllFieldsEmptyException;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Player player0;
    private Player player1;
    private boolean isStarted;
    private boolean randomFirstMove;

    private static Game instance;

    public Game(Player player0, Player player1, boolean randomFirstMove) {
        this.player0 = player0;
        this.player0.setMaximizing(true);
        this.player1 = player1;
        this.player1.setMaximizing(false);
        this.randomFirstMove = randomFirstMove;
    }

    public Game(Game base){
        this.player0 = new Player(base.getPlayer0());
        this.player1 = new Player(base.getPlayer1());
        this.randomFirstMove = base.randomFirstMove;
    }

    public static Game getRestartedGame(Game base){
        Player player0 = new Player(base.getPlayer0().getChoiceHeuristic());
        player0.setMaximizing(true);
        Player player1 = new Player(base.getPlayer1().getChoiceHeuristic());
        player1.setMaximizing(false);
        boolean randomFirstMove = base.randomFirstMove;

        return new Game(player0, player1, randomFirstMove);
    }

    public void playField(int fieldIndex) {
        int value = player0.getFields().get(fieldIndex).pull();

        ArrayList<BoardElement> elements = new ArrayList<>();

        elements.addAll(player0.getFields());
        elements.add(player0.getWell());
        elements.addAll(player1.getFields());

        boolean capture = false;
        if (value !=0 && (value+fieldIndex) % elements.size() < 6 && elements.get((fieldIndex + value) % elements.size()).getValue() == 0 && elements.get(12-((fieldIndex + value) % elements.size())).getValue() != 0){
            capture = true;
        }

        for(int elementsIndex = 0; elementsIndex<value; elementsIndex++){
            elements.get((fieldIndex + elementsIndex + 1)%elements.size()).put();
        }

        if(capture){
            player0.getWell().setValue(player0.getWell().getValue() + elements.get(12-((fieldIndex + value) % elements.size())).getValue() + 1);
            elements.get((fieldIndex + value) % elements.size()).setValue(0);
            elements.get(12-((fieldIndex + value) % elements.size())).setValue(0);
        }

        try {
            if((value+fieldIndex)%elements.size() != 6 && value != 0){
                makeAIChoice();
            }

            ArrayList<Field> nonemptyFields = new ArrayList<>();
            for (Field field: player0.getFields()) {
                if(field.getValue() > 0){
                    nonemptyFields.add(field);
                }
            }

            if(nonemptyFields.size()==0){
                finishWithNoPossibleMove();
            }
        } catch (AllFieldsEmptyException e) {
            finishWithNoPossibleMove();
        }
    }

    private void finishWithNoPossibleMove(){
        for (Field field: player0.getFields()) {
            player0.getWell().setValue(player0.getWell().getValue() + field.getValue());
            field.setValue(0);
        }

        for (Field field: player1.getFields()) {
            player1.getWell().setValue(player1.getWell().getValue() + field.getValue());
            field.setValue(0);
        }

        System.out.println("Game over");
    }

    private void makeAIChoice() throws AllFieldsEmptyException {
        ArrayList<BoardElement> elements = new ArrayList<>();

        int chosenIndex = player1.makeChoice(this);
        int value = player1.getFields().get(chosenIndex).pull();

        elements.addAll(player1.getFields());
        elements.add(player1.getWell());
        elements.addAll(player0.getFields());

        boolean capture = false;
        if (value !=0 && (value+chosenIndex) % elements.size() < 6 && elements.get((chosenIndex + value) % elements.size()).getValue() == 0 && elements.get(12-((chosenIndex + value) % elements.size())).getValue() != 0){
            capture = true;
        }

        for(int elementsIndex = 0; elementsIndex<value; elementsIndex++){
            elements.get((chosenIndex + elementsIndex + 1)%11).put();
        }

        if(capture){
            player1.getWell().setValue(player1.getWell().getValue() + elements.get(12-((chosenIndex + value) % elements.size())).getValue() + 1);
            elements.get((chosenIndex + value) % elements.size()).setValue(0);
            elements.get(12-((chosenIndex + value) % elements.size())).setValue(0);
        }

        if((value+chosenIndex)%elements.size() == 6){
            makeAIChoice();
        }
    }

    public int getScore(){
        return player0.getWell().getValue() - player1.getWell().getValue();
    }

    public void player0Play(int fieldIndex){
        int value = player0.getFields().get(fieldIndex).pull();

        ArrayList<BoardElement> elements = new ArrayList<>();

        elements.addAll(player0.getFields());
        elements.add(player0.getWell());
        elements.addAll(player1.getFields());

        boolean capture = false;
        if (value % elements.size() < 6 && elements.get((fieldIndex + value) % elements.size()).getValue() == 0 && elements.get(12-((fieldIndex + value) % elements.size())).getValue() != 0){
            capture = true;
        }

        for(int elementsIndex = 0; elementsIndex<value; elementsIndex++){
            elements.get((fieldIndex + elementsIndex + 1)%elements.size()).put();
        }

        if(capture){
            player0.getWell().setValue(player0.getWell().getValue() + elements.get(12-((fieldIndex + value) % elements.size())).getValue() + 1);
            elements.get((fieldIndex + value) % elements.size()).setValue(0);
            elements.get(12-((fieldIndex + value) % elements.size())).setValue(0);
        }
    }

    public void player1Play(int fieldIndex){
        ArrayList<BoardElement> elements = new ArrayList<>();

        int value = player1.getFields().get(fieldIndex).pull();

        elements.addAll(player1.getFields());
        elements.add(player1.getWell());
        elements.addAll(player0.getFields());

        boolean capture = false;
        if (value % elements.size() > 7 && elements.get((fieldIndex + value) % elements.size()).getValue() == 0 && elements.get(12-((fieldIndex + value) % elements.size())).getValue() != 0){
            capture = true;
        }

        for(int elementsIndex = 0; elementsIndex<value; elementsIndex++){
            elements.get((fieldIndex + elementsIndex + 1)%11).put();
        }

        if(capture){
            player1.getWell().setValue(player1.getWell().getValue() + elements.get(12-((fieldIndex + value) % elements.size())).getValue() + 1);
            elements.get((fieldIndex + value) % elements.size()).setValue(0);
            elements.get(12-((fieldIndex + value) % elements.size())).setValue(0);
        }
    }

    public Player getPlayer0() {
        return player0;
    }

    public Player getPlayer1() {
        return player1;
    }

    public static void setInstance(Game game){
        instance = game;
    }

    public static Game getInstance() {
        return instance;
    }

    public boolean isFinished(){
        return (player0.getNonemptyIndexes().size() == 0) && (player0.getNonemptyIndexes().size() == 0);
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isRandomFirstMove() {
        return randomFirstMove;
    }
}
