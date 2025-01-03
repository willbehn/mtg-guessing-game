package com.willbehn.mtg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.willbehn.mtg.model.hints.CardHintResponse;
import com.willbehn.mtg.service.CardHintService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/")
public class CardHintController {
    
    private final CardHintService hintService;

    @Autowired
    public CardHintController(CardHintService hintService){
        this.hintService = hintService;
    }

    @GetMapping("test")
    public CardHintResponse getDaily() {
        return hintService.createHints();
    }
    
}
