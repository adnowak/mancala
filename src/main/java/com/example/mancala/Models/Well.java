package com.example.mancala.Models;

public class Well extends BoardElement{
    public Well() {
        super(0);
    }

    public Well(Well base){
        super(base.getValue());
    }
}
