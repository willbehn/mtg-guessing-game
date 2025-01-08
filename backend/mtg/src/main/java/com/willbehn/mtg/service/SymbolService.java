package com.willbehn.mtg.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willbehn.mtg.model.symbols.SymbolList;

@Service
public class SymbolService {

    @Autowired
    private ObjectMapper objectMapper;
    
    public SymbolList getSymbols() throws IOException, InterruptedException{
        String apiUrl = "https://api.scryfall.com/symbology";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(apiUrl))
                                        .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), SymbolList.class);
    }
}
