package com.example.mancala.Models;

public abstract class BoardElement {
    protected int value;

    public BoardElement(int value) {
        this.value = value;
    }

    public void put(){
        this.value++;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
