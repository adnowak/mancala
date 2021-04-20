package com.example.mancala.Models;

public class Field extends BoardElement{
    public Field() {
        super(4);
    }

    public Field(Field base){
        super(base.getValue());
    }

    public int pull(){
        int oldValue = this.value;
        this.value = 0;
        return oldValue;
    }
}
