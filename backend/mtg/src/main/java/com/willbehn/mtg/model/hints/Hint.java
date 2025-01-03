package com.willbehn.mtg.model.hints;

public record Hint(HintType type, String data){}

/*public class Hint {
    HintType type;
    String data;

    public Hint(HintType type, String data){
        this.type = type;
        this.data = data;
    }


    public HintType getType(){
        return type;
    }

    public String getData(){
        return data;
    }
}*/
