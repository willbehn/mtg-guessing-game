package com.willbehn.mtg.model.hints;

import java.util.ArrayList;
import java.util.List;

public class HintList {
    String cardName;
    List<Hint> hints;

    public HintList(String cardName, List<Hint> hints){
        this.cardName = cardName;
        this.hints = new ArrayList<>(hints);
    }

}
