package com.willbehn.mtg.model.symbols;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolList implements Iterable<Symbol> {
    
    @JsonProperty("data")
    public List<Symbol> allCards;


    @Override
    public Iterator<Symbol> iterator(){
        return allCards.iterator();
    }

}
