package com.willbehn.mtg.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.willbehn.mtg.model.hints.CardHintResponse;
import com.willbehn.mtg.service.CardHintService;

import org.springframework.web.bind.annotation.CrossOrigin;
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
    @CrossOrigin(origins = {"http://localhost:5173", "http://192.168.50.55:5173/"})
    public ResponseEntity<CardHintResponse> getDaily() {
        try {
            CardHintResponse response = hintService.createHints();
            return ResponseEntity.ok(response);

        } catch (IOException | InterruptedException e){
            return ResponseEntity.badRequest().build();
        }
        

    }
    
}
