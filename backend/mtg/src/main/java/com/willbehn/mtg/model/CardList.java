package com.willbehn.mtg.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardList {
    
    @JsonIgnoreProperties("data")
    public List<Card> allCards;
}
