package com.willbehn.mtg.model.cards;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardList implements Iterable<Card> {
    
    @JsonProperty("data")
    public List<Card> allCards;


    @Override
    public Iterator<Card> iterator(){
        return allCards.iterator();
    }

}
