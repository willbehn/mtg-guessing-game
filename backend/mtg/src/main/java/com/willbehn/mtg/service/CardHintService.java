package com.willbehn.mtg.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbehn.mtg.model.MtgSet;
import com.willbehn.mtg.model.cards.Card;
import com.willbehn.mtg.model.hints.CardHintResponse;
import com.willbehn.mtg.model.hints.Hint;
import com.willbehn.mtg.model.hints.HintType;
import com.willbehn.mtg.model.symbols.SymbolList;

@Service
public class CardHintService {
    private final CardService cardService;
    private final SetService setService;
    private final SymbolService symbolService;

    @Autowired
    public CardHintService(CardService cardService, SetService setService, SymbolService symbolService){
        this.cardService = cardService;
        this.setService = setService;
        this.symbolService = symbolService;
    }


    public CardHintResponse createHints(){
        Card card = null;
        MtgSet set = null;
        SymbolList symbols = null;

        Random random = new Random();

        try {
            //card = cardService.getCardList().allCards.get(random.nextInt(175)); //TODO only for testing
            card = cardService.getCardTest();
            set = setService.getSet(card.setCode);

            //Mby remove symbol retrieving from backend
            symbols = symbolService.getSymbols();

        } catch (IOException | InterruptedException e){
            //TODO
        }

        Hint artHint = new Hint(HintType.ARTWORK, card.getArtCropImageUrl());
        Hint typeHint = new Hint(HintType.CARD_TYPE, card.typeLine);
        Hint setHint = new Hint(HintType.SET_EXPANSION, set.iconUri);
        Hint manaHint = new Hint(HintType.MANA_COST, card.manaCost);
        Hint oracleHint = new Hint(HintType.ORACLE_TEXT, card.oracleText);
        Hint statHInt = new Hint(HintType.POWER_THOUGHNESS, "{" + card.power + "}{" + card.toughness + "}");
        
        return new CardHintResponse(card.name, card.getEdhrecUri(),card.getNormalImageUrl(), LocalDate.now(), List.of(manaHint,typeHint,setHint,statHInt, oracleHint,artHint), symbols);
    }

    
}
