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
        Hint oracleHint = createOracleHint(card);
        Hint nameHint = createNameHint(card);
        //Hint setHint = new Hint(HintType.SET_EXPANSION, set.iconUri);

        hints.addAll(List.of(manaHint, typeHint, statHint,keywordHint,rarityHint, oracleHint,nameHint,artHint));
    
        return hints;
    }

    private List<Hint> createSpellHints(Card card){
        log.info("Creating hints for artifact card");

        List<Hint> hints = new ArrayList<>();

        Hint manaHint = new Hint(HintType.MANA_COST, card.manaCost);
        Hint typeHint = new Hint(HintType.CARD_TYPE, card.typeLine);
        Hint oracleHint = createOracleHint(card);
        Hint rarityHint = new Hint(HintType.RARITY, card.rarity);
        Hint artHint = new Hint(HintType.ARTWORK, card.getArtCropImageUrl() + "&" + card.artist);
        Hint nameHint = createNameHint(card);
        //Hint releasedAtHint = new Hint(HintType.RELEASED_AT, card.releasedAt);
        
        hints.addAll(List.of(manaHint, typeHint, rarityHint, oracleHint,nameHint, artHint));

        return hints;

    }    

    private Hint createOracleHint(Card card){
        StringBuilder editedOracleText = new StringBuilder();
        Random random = new Random();
        String[] splitOracle = card.oracleText.split(" ");

        for (String word : splitOracle){
            if (card.name.toLowerCase().contains(word.toLowerCase())){
                editedOracleText.append(" " + " _ ".repeat(word.length()));
            } else if (random.nextInt(5)  == 0){
                editedOracleText.append(" " + " _ ".repeat(word.length()));
            } else editedOracleText.append(" " + word);
        }

        return new Hint(HintType.ORACLE_TEXT, editedOracleText.toString());
    }

    private Hint createNameHint(Card card){
        StringBuilder builder = new StringBuilder();
        String[] cardNameSplit = card.name.split("");
        int hintLength = card.name.length()/3;
        
        for (int i = 0; i < hintLength; i++){
            builder.append(cardNameSplit[i] + " ");
        }

        builder.append(" _ ".repeat(card.name.length()-hintLength));

        return new Hint(HintType.NAME, builder.toString());
    }
}
