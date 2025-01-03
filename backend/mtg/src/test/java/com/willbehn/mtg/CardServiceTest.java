package com.willbehn.mtg;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.willbehn.mtg.model.cards.Card;
import com.willbehn.mtg.model.cards.CardList;
import com.willbehn.mtg.service.CardService;

@SpringBootTest
public class CardServiceTest {
    
    @Autowired
    CardService cardService;

    @Test
    public void testGetCard() throws IOException, InterruptedException {
        Card card = cardService.getCard();
        System.out.println("Card: " + card);
    }

    @Test
    public void testGetCardList() throws IOException, InterruptedException{
        CardList cards = cardService.getCardList();

        for (Card card : cards){
            System.out.println(card);
        }
    }
}
