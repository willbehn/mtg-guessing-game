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

@Service
public class CardHintService {
    private final CardService cardService;
    private final SetService setService;

    @Autowired
    public CardHintService(CardService cardService, SetService setService){
        this.cardService = cardService;
        this.setService = setService;
    }


    public CardHintResponse createHints(){
        Card card = null;
        MtgSet set = null;

        Random random = new Random();

        try {
            card = cardService.getCardList().allCards.get(random.nextInt(175)); //TODO only for testing
            set = setService.getSet(card.setCode);

        } catch (IOException | InterruptedException e){
            //TODO
        }

        Hint hint1 = new Hint(HintType.ARTWORK, card.getArtCropImageUrl());
        Hint hint2 = new Hint(HintType.CARD_TYPE, card.typeLine);
        Hint hint3 = new Hint(HintType.SET_EXPANSION, set.iconUri);
        Hint hint4 = new Hint(HintType.MANA_COST, card.manaCost);
        Hint hint5 = new Hint(HintType.ORACLE_TEXT, card.oracleText);
        Hint hint6 = new Hint(HintType.POWER_THOUGHNESS, "{" + card.power + "}{" + card.toughness + "}");
        
        return new CardHintResponse(card.name, card.getNormalImageUrl(), LocalDate.now(), List.of(hint1,hint2,hint3,hint4,hint5,hint6));
    }

    
}
