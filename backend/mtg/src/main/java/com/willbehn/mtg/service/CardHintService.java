package com.willbehn.mtg.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.willbehn.mtg.model.MtgSet;
import com.willbehn.mtg.model.cards.Card;
import com.willbehn.mtg.model.hints.CardHintResponse;
import com.willbehn.mtg.model.hints.Hint;
import com.willbehn.mtg.model.hints.HintType;

@Service
public class CardHintService {
    private final CardService cardService;
    private final SetService setService;
    private final SymbolService symbolService;

    private MtgSet set = null;

    private static final Logger log = LoggerFactory.getLogger(CardHintService.class);

    @Autowired
    public CardHintService(CardService cardService, SetService setService, SymbolService symbolService){
        this.cardService = cardService;
        this.setService = setService;
        this.symbolService = symbolService;
    }


    public CardHintResponse createHints() throws IOException, InterruptedException{
        Card card = null;

        Random random = new Random();
        card = cardService.getCardTest();
        set = setService.getSet(card.setCode);

            
        //symbols = symbolService.getSymbols();

        List<Hint> selectedHints = null;

        if (!card.typeLine.toLowerCase().contains("creature")){
            selectedHints = createSpellHints(card);
        } else {
            selectedHints = createCreatureHints(card);
        }
       
    
        return new CardHintResponse(card.name, card.getEdhrecUri(),card.getNormalImageUrl(), LocalDate.now(), selectedHints);
    }

    private List<Hint> createCreatureHints(Card card){
        log.info("Creating hints for creature card");

        List<Hint> hints = new ArrayList<>();

        Hint manaHint = new Hint(HintType.MANA_COST, card.manaCost);
        Hint keywordHint= new Hint(HintType.KEYWORDS, card.keywords.toString());
        Hint statHint = new Hint(HintType.POWER_THOUGHNESS, "{" + card.power + "}{" + card.toughness + "}");
        Hint artHint = new Hint(HintType.ARTWORK, card.getArtCropImageUrl() + "&" + card.artist);
        Hint typeHint = new Hint(HintType.CARD_TYPE, card.typeLine);
        Hint rarityHint = new Hint(HintType.RARITY, card.rarity);
        Hint releasedAtHint = new Hint(HintType.RELEASED_AT, card.releasedAt);
        Hint oracleHint = new Hint(HintType.ORACLE_TEXT, card.oracleText);
        Hint setHint = new Hint(HintType.SET_EXPANSION, set.iconUri);

        hints.addAll(List.of(manaHint, typeHint, keywordHint, releasedAtHint,rarityHint,artHint));
    
        return hints;
    }

    private List<Hint> createSpellHints(Card card){
        log.info("Creating hints for artifact card");

        List<Hint> hints = new ArrayList<>();

        Hint manaHint = new Hint(HintType.MANA_COST, card.manaCost);
        Hint typeHint = new Hint(HintType.CARD_TYPE, card.typeLine);
        Hint oracleHint = new Hint(HintType.ORACLE_TEXT, card.oracleText);
        Hint rarityHint = new Hint(HintType.RARITY, card.rarity);
        Hint artHint = new Hint(HintType.ARTWORK, card.getArtCropImageUrl() + "&" + card.artist);
        Hint releasedAtHint = new Hint(HintType.RELEASED_AT, card.releasedAt);
        
        hints.addAll(List.of(manaHint, typeHint, oracleHint, releasedAtHint,rarityHint,artHint));


        return hints;

    }    
}
