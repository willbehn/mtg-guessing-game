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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public CardList getCardList() throws IOException, InterruptedException {
        //https://api.scryfall.com/cards/search?q=set%3Dltr
        //https://api.scryfall.com/cards/search?order=edhrec&dir=asc&game=paper&q=format%3Aedh

        String apiUrl = "https://api.scryfall.com/cards/search?q=is%3Acommander+type%3Acreature&order=edhrec&dir=asc&unique=cards&page=1";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(apiUrl))
                                        .build();

        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), CardList.class);
    }

    //For testing
    public Card getCardTest() throws IOException, InterruptedException {
        List<String> top100CommandersAPIPaths = Arrays.asList(
            "Atraxa%2C%20Praetors%27%20Voice",
            "The%20Ur-Dragon",
            "Yuriko%2C%20the%20Tiger%27s%20Shadow",
            "Krenko%2C%20Mob%20Boss",
            "Lathril%2C%20Blade%20of%20the%20Elves",
            "Sauron%2C%20the%20Dark%20Lord",
            "Kenrith%2C%20the%20Returned%20King",
            "Edgar%20Markov",
            "Miirym%2C%20Sentinel%20Wyrm",
            "Kaalia%20of%20the%20Vast",
            "Isshin%2C%20Two%20Heavens%20as%20One",
            "Gishath%2C%20Sun%27s%20Avatar",
            "Wilhelt%2C%20the%20Rotcleaver",
            "Nekusar%2C%20the%20Mindrazer",
            "Esika%2C%20God%20of%20the%20Tree",
            "Jodah%2C%20the%20Unifier",
            "Giada%2C%20Font%20of%20Hope",
            "Pantlaza%2C%20Sun-Favored",
            "Aragorn%2C%20the%20Uniter",
            "Shorikai%2C%20Genesis%20Engine",
            "Arcades%2C%20the%20Strategist",
            "Chatterfang%2C%20Squirrel%20General",
            "Animar%2C%20Soul%20of%20Elements",
            "Rin%20and%20Seri%2C%20Inseparable",
            "Muldrotha%2C%20the%20Gravetide",
            "Ghyrson%20Starn%2C%20Kelermorph",
            "Teysa%20Karlov",
            "Zhulodok%2C%20Void%20Gorger",
            "Korvold%2C%20Fae-Cursed%20King",
            "Frodo%2C%20Adventurous%20Hobbit%20%2F%2F%20Sam%2C%20Loyal%20Attendant",
            "Atla%20Palani%2C%20Nest%20Tender",
            "Meren%20of%20Clan%20Nel%20Toth",
            "Go-Shintai%20of%20Life%27s%20Origin",
            "Urtet%2C%20Remnant%20of%20Memnarch",
            "Atraxa%2C%20Grand%20Unifier",
            "Henzie%20%22Toolbox%22%20Torre",
            "Niv-Mizzet%2C%20Parun",
            "Sidar%20Jabari%20of%20Zhalfir",
            "Tom%20Bombadil",
            "K%27rrik%2C%20Son%20of%20Yawgmoth",
            "Breya%2C%20Etherium%20Shaper",
            "Eriette%20of%20the%20Charmed%20Apple",
            "Urza%2C%20Chief%20Artificer",
            "Omnath%2C%20Locus%20of%20Creation",
            "The%20Wise%20Mothman",
            "Omnath%2C%20Locus%20of%20All",
            "Hakbal%20of%20the%20Surging%20Soul",
            "Prosper%2C%20Tome-Bound",
            "Urza%2C%20Lord%20High%20Artificer",
            "Thalia%20and%20The%20Gitrog%20Monster",
            "Captain%20N%27ghathrod",
            "Queen%20Marchesa",
            "Oloro%2C%20Ageless%20Ascetic",
            "Kinnan%2C%20Bonder%20Prodigy",
            "Voja%2C%20Jaws%20of%20the%20Conclave",
            "Sisay%2C%20Weatherlight%20Captain",
            "Alela%2C%20Artful%20Provocateur",
            "Zaxara%2C%20the%20Exemplary",
            "Tivit%2C%20Seller%20of%20Secrets",
            "Ulalek%2C%20Fused%20Atrocity",
            "Magda%2C%20Brazen%20Outlaw",
            "Xyris%2C%20the%20Writhing%20Storm",
            "Sythis%2C%20Harvest%27s%20Hand",
            "Mr.%20House%2C%20President%20and%20CEO",
            "Zur%20the%20Enchanter",
            "The%20First%20Sliver",
            "Magus%20Lucea%20Kane",
            "Tovolar%2C%20Dire%20Overlord",
            "Be%27lakor%2C%20the%20Dark%20Master",
            "Lord%20of%20the%20Nazg%C3%BBl",
            "Najeela%2C%20the%20Blade-Blossom"
        );
        Random random = new Random();

        String cardName = top100CommandersAPIPaths.get(random.nextInt(top100CommandersAPIPaths.size()));
        String apiUrl = "https://api.scryfall.com/cards/named?exact=" + cardName;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(apiUrl))
                                        .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), Card.class);

    }
}
