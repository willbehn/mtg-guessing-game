package com.willbehn.mtg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardHintService {
    private final CardService cardService;

    @Autowired
    public CardHintService(CardService cardService){
        this.cardService = cardService;
    }
}
