package com.willbehn.mtg;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.willbehn.mtg.model.Card;
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
}
