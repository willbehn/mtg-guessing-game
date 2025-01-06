package com.willbehn.mtg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willbehn.mtg.model.cards.Card;
import com.willbehn.mtg.model.cards.CardList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CardService {

    @Autowired
    private ObjectMapper objectMapper; 

    private final String apiUrl = "https://api.scryfall.com/cards/random?q=type%3Alegendary+type%3Acreature";

    public Card getCard() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(apiUrl))
                                        .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), Card.class);
    }


    //TODO remove after testing
    public CardList getCardList() throws IOException, InterruptedException {
        String apiUrl = "https://api.scryfall.com/cards/search?order=edhrec&dir=asc&game=paper&q=is%3Acommander";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(apiUrl))
                                        .build();

        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), CardList.class);
    }
}
