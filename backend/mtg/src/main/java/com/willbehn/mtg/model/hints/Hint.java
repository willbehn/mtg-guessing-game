package com.willbehn.mtg.model.hints;

public class Hint {
    HintType type;
    String data;

    public Hint(HintType type, String data){
        this.type = type;
        this.data = data;
    }
}
