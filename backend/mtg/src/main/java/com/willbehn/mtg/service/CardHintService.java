package com.willbehn.mtg.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbehn.mtg.model.cards.Card;

@Service
public class CardHintService {
    private final CardService cardService;
    private final SetService setService;

    @Autowired
    public CardHintService(CardService cardService, SetService setService){
        this.cardService = cardService;
        this.setService = setService;
    }


    public void createHints(){
        try {
            Card card = cardService.getCard();
        } catch (IOException | InterruptedException e){
            //TODO
        }
        
    }

    
}
